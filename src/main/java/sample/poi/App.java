package sample.poi;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.io.FileOutputStream;

import java.util.Date;
import sample.poi.util.Util;

/**
 * Apache POI サンプル
 */
public class App {
    
    private Logger logger = LogManager.getLogger();

    public void exec(String[] args) {
        FileOutputStream fos = null;
        XSSFWorkbook workbook = null;
 
        try {
 
            // ワークブック→シート→行→セルの生成
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
 
            // セルの書式の生成
            XSSFCellStyle cellStyle = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setFontName("ＭＳ ゴシック");
            cellStyle.setFont(font);
            cell.setCellStyle(cellStyle);
 
            // セルに書き込み
            cell.setCellValue("Hello World!\tHello World2!");
 
            String exportPath
                = Paths.get(
                    System.getProperty("user.dir") ,
                    "export",
                    String.format("test_%s.xlsx", Util.now() )).toString();
            logger.info("exportPath : {}", exportPath);
 
            // ファイル書き込み
            fos = new FileOutputStream(exportPath);
            workbook.write(fos);
 
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main( String[] args ) {
        new App().exec(args);
    }
}
