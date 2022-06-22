package sample.poi.enums.style;

import lombok.Getter;

import org.apache.poi.ss.usermodel.BorderStyle;

import sample.poi.model.style.BorderStyleModel;
import sample.poi.model.style.BorderDetailStyleModel;

public enum DefaultBorderStyle {
    THIN(new BorderStyleModel(
            new BorderDetailStyleModel(BorderStyle.THIN),
            new BorderDetailStyleModel(BorderStyle.THIN),
            new BorderDetailStyleModel(BorderStyle.THIN),
            new BorderDetailStyleModel(BorderStyle.THIN)
        )
    );

    @Getter
    private BorderStyleModel borderStyle;
    private DefaultBorderStyle(BorderStyleModel borderStyle) {
        this.borderStyle = borderStyle;
    }
}
