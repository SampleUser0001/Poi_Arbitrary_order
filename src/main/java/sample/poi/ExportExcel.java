package sample.poi;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SerializationUtils;

import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;

import sample.poi.model.data.DataModel;
import sample.poi.model.data.DataModel.CaptionEnum;
import sample.poi.model.disp.HeaderModel;
import sample.poi.model.disp.ColumnInfoModel;
import sample.poi.model.style.StyleModel;
import sample.poi.factory.StyleFactory;
import sample.poi.enums.style.*;

import sample.poi.util.Util;

import java.io.IOException;
import java.text.ParseException;

public class ExportExcel {
    private Logger logger = LogManager.getLogger();

    private static final String DELIMITER = "\t";
    /** 行番を出力するかどうか */
    private static final boolean LINE_NUMBER = true;

    private static final StyleModel DEFAULT_STYLE;
    static {
        DEFAULT_STYLE = new StyleModel();
        DEFAULT_STYLE.setFontStyle(FontStyle.MS_Gothic);
        DEFAULT_STYLE.setBorderStyle(DefaultBorderStyle.THIN.getBorderStyle());
    }

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private StyleFactory styleFactory;

    public void exec(String[] args) throws IOException, ParseException {
        logger.info("start");

        try(FileOutputStream fos = new FileOutputStream(this.getExportPath());) {

            int lineCounter = 0;
            final int COLUMN_BASE = LINE_NUMBER ? 1 : 0;
            
            // ヘッダ生成
            HeaderModel headerModel = Util.getHeader("sample01.json");
            List<ColumnInfoModel> columnInfoList
                = headerModel.getHeaderListOrderByColumnOrder()
                             .collect(Collectors.toList());
            for(;lineCounter < headerModel.getHeaderLine();lineCounter++){
                XSSFRow row = this.sheet.createRow(lineCounter);
                for(int i=0 ; i < columnInfoList.size() ; i++) {
                    final int columnIndex = i + COLUMN_BASE;
                    if(lineCounter == 0) {
                        this.sheet.setColumnHidden(columnIndex, !columnInfoList.get(i).isVisible());
                    }

                    logger.debug(
                        "line:{}, column:{}, value:{}, style:{}",
                        lineCounter,
                        columnIndex,
                        columnInfoList.get(i).getDispName().get(lineCounter).getName(),
                        DEFAULT_STYLE);

                    StyleModel styleModel = SerializationUtils.clone(DEFAULT_STYLE);
                    styleModel.setCellColor(
                        columnInfoList.get(i).isVisible()
                        ? IndexedColors.LIGHT_GREEN.getIndex()
                        : IndexedColors.GREY_40_PERCENT.getIndex() );

                    XSSFCell cell = row.createCell(columnIndex);
                    cell.setCellStyle(this.styleFactory.create(styleModel));
                    cell.setCellValue(columnInfoList.get(i).getDispName().get(lineCounter).getName());
                }
            }

            // ヘッダセルの結合
            this.mergeHeader(headerModel, lineCounter - headerModel.getHeaderLine(), COLUMN_BASE);

            // ウィンドウ枠の固定
            this.sheet.createFreezePane(COLUMN_BASE, lineCounter);

            // データ部生成
            List<DataModel> dataList = Util.getDatas();
            for(int i=0 ; i < dataList.size() ; i++ , lineCounter++){
                XSSFRow row = this.sheet.createRow(lineCounter);
                // 行番号を書く
                if(LINE_NUMBER){
                    XSSFCell cell = row.createCell(0);
                    cell.setCellValue(i+1);
                    cell.setCellStyle(this.styleFactory.create(DEFAULT_STYLE));
                }

                for(int j=0 ; j < columnInfoList.size() ; j++) {
                    final int columnIndex = j + COLUMN_BASE;

                    // StyleModel style = new StyleModel();
                    // style.setFont(FontStyle.MS_Gothic);

                    XSSFCell cell = row.createCell(columnIndex);
                    CaptionEnum captionEnum = CaptionEnum.valueOf(columnInfoList.get(j).getCaption());

                    cell.setCellStyle(this.styleFactory.create(captionEnum.getStyle()));
                    captionEnum.setCellValue(cell, dataList.get(i));
                }
            }
 
            // 列の幅調整を行う。
            this.autoSizeColumn(0, columnInfoList.size() + COLUMN_BASE , true);

            // ファイル書き込み
            workbook.write(fos);
 
            fos.close();
            workbook.close();
        }
        logger.info("finish");

    }

    public void mergeHeader(HeaderModel headerModel, int lineBase, int columnBase) {
        List<ColumnInfoModel> columnList = headerModel.getHeaderListOrderByColumnOrder()
                                                      .collect(Collectors.toList());
        // 縦が同じ値の場合は結合(ヘッダ行が2行ある時のみ)
        if(headerModel.getHeaderLine() >= 2){
            for(int columnIndex = 0 ; columnIndex < columnList.size() ; columnIndex++){
                
                if(StringUtils.equals(
                    columnList.get(columnIndex).getDispName().get(0).getName(),
                    columnList.get(columnIndex).getDispName().get(1).getName())){

                    this.sheet.addMergedRegion(
                        new CellRangeAddress(lineBase, lineBase + 1, columnBase + columnIndex , columnBase + columnIndex )
                    );
                }
            }
        }

        // 1行目のみ、横が同じ値の場合は結合
        boolean isMerge = false;
        int mergeStart = 0;
        for(int columnIndex = 1; columnIndex < columnList.size() ; columnIndex++){
            
            logger.debug(
                "mergeStart : {},{} ; columnIndex : {},{} ",
                mergeStart, columnList.get(mergeStart).getDispName().get(0).getName(),
                columnIndex, columnList.get(columnIndex).getDispName().get(0).getName());

            // columnIndex自体とその左にあるヘッダが同じかどうか
            if(StringUtils.equals(
                columnList.get(mergeStart).getDispName().get(0).getName(),
                columnList.get(columnIndex).getDispName().get(0).getName())){

                isMerge = true;
            } else {
                if(isMerge) {
                    logger.debug("merge; mergeStart : {} , columnIndex : {}", mergeStart, columnIndex -1);
                    this.sheet.addMergedRegion(
                        new CellRangeAddress(lineBase, lineBase, columnBase + mergeStart, columnBase + columnIndex-1 )
                    );
                }

                isMerge = false;
                mergeStart = columnIndex;
            }
        }
        // 最終行を結合する場合の処理
        if(isMerge) {
            logger.debug("merge; mergeStart : {} , columnIndex : {}", mergeStart, columnList.size()-1);
            this.sheet.addMergedRegion(
                new CellRangeAddress(lineBase, lineBase, columnBase + mergeStart, columnBase + columnList.size()-1 )
            );
        }

    }

    public void autoSizeColumn(int start, int finish, boolean isAutoSizeColumn){
        for(int i=start ; i<=finish ; i++){
            this.sheet.autoSizeColumn(i, isAutoSizeColumn);
        }
    }

    public String getExportPath() {
        String exportPath
            = Paths.get(
                System.getProperty("user.dir") ,
                "export",
                String.format("test_%s.xlsx", Util.now() )).toString();
        logger.info("exportPath : {}", exportPath);
        return exportPath;
    }

    public ExportExcel() {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet();
        this.styleFactory = new StyleFactory(workbook);
    }

    public static void main(String[] args) throws IOException, ParseException {
        new ExportExcel().exec(args);
    }
}
