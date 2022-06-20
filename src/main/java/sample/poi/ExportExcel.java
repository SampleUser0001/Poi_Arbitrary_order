package sample.poi;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.io.FileOutputStream;

import java.util.List;
import sample.poi.model.data.DataModel;
import sample.poi.model.disp.HeaderModel;

import sample.poi.util.Util;

import java.io.IOException;
import java.text.ParseException;

public class ExportExcel {
    private Logger logger = LogManager.getLogger();

    private static final String DELIMITER = "\t";
    private static final boolean LINE_NUMBER = true;

    private static final String FONT_NAME = "ＭＳ ゴシック";

    public void exec(String[] args) throws IOException, ParseException {
        HeaderModel headerModel = Util.getHeader("sample01.json");
        List<DataModel> dataList = Util.getDatas();

        XSSFWorkbook workbook = new XSSFWorkbook();
        try(FileOutputStream fos = new FileOutputStream(this.getExportPath());) {
            XSSFSheet sheet = workbook.createSheet();
            // int lineCounter = 0;
            // final int COLUMN_BASE = LINE_NUMBER ? 0 : 1;
            
            // // ヘッダ生成
            // for(ColumnInfoModel column : headerModel.getHeaderListOrderByColumnOrder()) {

                
            //     if(headerModel.getHeaderLine == 2){
            //         // 2行目を生成する。
            //     }
            // }

            // lineCounter += headerModel.getHeaderLine();
            // // データ部生成

            // // シート -> 行 -> セルの生成
 
            // // セルの書式の生成
            // XSSFCellStyle cellStyle = workbook.createCellStyle();
            // XSSFFont font = workbook.createFont();
            // font.setFontName("ＭＳ ゴシック");
            // cellStyle.setFont(font);
            // cell.setCellStyle(cellStyle);
 
            // // セルに書き込み
            // cell.setCellValue("Hello World!\tHello World2!");
 
            // // ファイル書き込み
            // workbook.write(fos);
 
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

    public static void main(String[] args) throws IOException, ParseException {
        new ExportExcel().exec(args);
    }
}
