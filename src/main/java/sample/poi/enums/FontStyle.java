package sample.poi.enums;

import lombok.Getter;

public enum FontStyle {
    MS_Gothic("ＭＳ ゴシック");

    @Getter
    private final String fontName;

    private FontStyle(String fontName) {
        this.fontName = fontName;
    }
}
