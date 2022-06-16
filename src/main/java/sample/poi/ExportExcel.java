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
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import sample.poi.model.data.*;

import java.io.IOException;
import java.text.ParseException;

/**
 * Apache POI サンプル
 */
public class ExportExcel {
    
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
            cell.setCellValue("Hello World!");
 
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String exportPath
                = Paths.get(
                    System.getProperty("user.dir") ,
                    "export",
                    String.format("test_%s.xlsx", format.format(new Date()))).toString();
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
    
    public List<DataModel> createDataModel() throws ParseException {
        // TODO データは増やす。
        List<DataModel> list = new ArrayList<DataModel>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        int index = 1;
        list.add(new DataModel(
            new CommonModel(index++, "aaa", format.parse("2022-06-01"), format.parse("2022-06-02")),
            new Group01Model("g01_value1_01", "g01_value2_01"),
            new Group02Model("g02_value1_01", "g02_value2_01")
        ));
        list.add(new DataModel(
            new CommonModel(index++, "bbb", format.parse("2022-06-03"), format.parse("2022-06-04")),
            new Group01Model("g01_value1_02", "g01_value2_02"),
            new Group02Model("g02_value1_02", "g02_value2_02")
        ));
        return list;
    }
    
    public static void main( String[] args ) {
        
    }
}
