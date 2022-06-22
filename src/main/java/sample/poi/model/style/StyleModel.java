package sample.poi.model.style;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import sample.poi.enums.style.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StyleModel {
    private FontStyle fontStyle = FontStyle.MS_Gothic;
    private DateFormatStyle dateStyle = DateFormatStyle.None;
    private BorderStyleModel borderStyle = new BorderStyleModel();
}
