package sample.poi.enums;

import lombok.Getter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

public enum FontStyle {
    MS_Gothic("ＭＳ ゴシック");

    @Getter
    private final String fontName;

    private FontStyle(String fontName) {
        this.fontName = fontName;
    }

    public void setFont(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        XSSFFont xssfFont = workbook.createFont();
        xssfFont.setFontName(this.fontName);
        cellStyle.setFont(xssfFont);
    }
}
