package sample.poi.factory;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;

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

            model.getFontStyle().setFont(this.workbook, cellStyle);
            model.getDateStyle().setDataFormat(this.workbook, cellStyle);
            model.getBorderStyle().setBorder(cellStyle);
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setFillForegroundColor(model.getCellColor());

            this.styleMemoMap.put(model, cellStyle);
        }

        return this.styleMemoMap.get(model);
    }
}
