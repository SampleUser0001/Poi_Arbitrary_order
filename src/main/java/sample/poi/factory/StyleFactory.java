package sample.poi.factory;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.Map;
import java.util.HashMap;

import sample.poi.model.style.StyleModel;

public class StyleFactory {
    private XSSFWorkbook workbook;
    private Map<StyleModel, XSSFCellStyle> styleMemoMap;

    public StyleFactory(XSSFWorkbook workbook) {
        this.workbook = workbook;
        this.styleMemoMap = new HashMap<StyleModel, XSSFCellStyle>();
    }

    public XSSFCellStyle create(StyleModel model) {
        if(!this.styleMemoMap.containsKey(model)) {
            XSSFCellStyle cellStyle = this.workbook.createCellStyle();

            XSSFFont font = workbook.createFont();
            font.setFontName(model.getFont().getFontName());
            cellStyle.setFont(font);

            this.styleMemoMap.put(model, cellStyle);
        }

        return this.styleMemoMap.get(model);
    }
}
