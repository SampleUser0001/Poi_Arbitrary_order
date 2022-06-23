package sample.poi.model.style;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.BorderStyle;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderDetailStyleModel implements Serializable {
    private BorderStyle type = BorderStyle.NONE;
}
