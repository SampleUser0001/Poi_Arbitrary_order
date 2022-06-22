package sample.poi.model.style;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.apache.poi.xssf.usermodel.XSSFCellStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorderStyleModel {
    private BorderDetailStyleModel top = new BorderDetailStyleModel();
    private BorderDetailStyleModel left = new BorderDetailStyleModel();
    private BorderDetailStyleModel right = new BorderDetailStyleModel();
    private BorderDetailStyleModel bottom = new BorderDetailStyleModel();

    public void setBorder(XSSFCellStyle style) {
        style.setBorderTop(top.getType());
        style.setBorderLeft(left.getType());
        style.setBorderRight(right.getType());
        style.setBorderBottom(bottom.getType());
    }
}
