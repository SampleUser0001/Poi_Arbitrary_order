package sample.poi.model.style;

import lombok.Data;
import lombok.NoArgsConstructor;

import sample.poi.enums.FontStyle;

@Data
@NoArgsConstructor
public class StyleModel {
    private FontStyle font = null;
}
