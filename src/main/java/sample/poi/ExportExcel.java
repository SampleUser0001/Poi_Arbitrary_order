package sample.poi;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;

import sample.poi.model.data.DataModel;
import sample.poi.model.disp.HeaderModel;
import sample.poi.model.disp.ColumnInfoModel;
import sample.poi.model.style.StyleModel;
import sample.poi.factory.StyleFactory;
import sample.poi.enums.FontStyle;

import sample.poi.util.Util;

import java.io.IOException;
import java.text.ParseException;

public class ExportExcel {
    private Logger logger = LogManager.getLogger();

    private static final String DELIMITER = "\t";
    /** 行番を出力するかどうか */
    private static final boolean LINE_NUMBER = true;

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private StyleFactory styleFactory;

    public void exec(String[] args) throws IOException, ParseException {

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

                    StyleModel style = new StyleModel();
                    style.setFont(FontStyle.MS_Gothic);

                    logger.info(
                        "line:{}, column:{}, value:{}, style:{}",
                        lineCounter,
                        columnIndex,
                        columnInfoList.get(i).getDispName().get(lineCounter).getName(),
                        style);

                    XSSFCell cell = row.createCell(columnIndex);
                    cell.setCellStyle(this.styleFactory.create(style));
                    cell.setCellValue(columnInfoList.get(i).getDispName().get(lineCounter).getName());
                }
            }

            // データ部生成
            List<DataModel> dataList = Util.getDatas();
            for(int i=0 ; i < dataList.size() ; i++ , lineCounter++){
                XSSFRow row = this.sheet.createRow(lineCounter);
                for(int j=0 ; j < columnInfoList.size() ; j++) {
                    final int columnIndex = j + COLUMN_BASE;

                    StyleModel style = new StyleModel();
                    style.setFont(FontStyle.MS_Gothic);

                    XSSFCell cell = row.createCell(columnIndex);
                    cell.setCellStyle(this.styleFactory.create(style));
                    dataList.get(i).setCellValue(cell, columnInfoList.get(j).getCaption());
                }
            }
 
            // ファイル書き込み
            workbook.write(fos);
 
            fos.close();
            workbook.close();
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
