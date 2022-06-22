package sample.poi.enums.style;

import lombok.Getter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public enum FontStyle {
    None("") {
        @Override
        public void setFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {}
    },
    MS_Gothic("ＭＳ ゴシック"){
        @Override
        public void setFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
            super._setFont(workbook, cellStyle);
        }
    };

    @Getter
    private final String fontName;

    private FontStyle(String fontName) {
        this.fontName = fontName;
    }

    public abstract void setFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle);
    private void _setFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setFontName(this.fontName);
        cellStyle.setFont(xssfFont);
    }

}
