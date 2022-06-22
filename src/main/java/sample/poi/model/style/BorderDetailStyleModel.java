package sample.poi.model.style;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.BorderStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
class BorderDetailStyleModel {
    private short type = BorderStyle.BORDER_NONE;
}
