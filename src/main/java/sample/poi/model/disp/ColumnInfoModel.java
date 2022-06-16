package sample.poi.model.disp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInfoModel implements Serializable, Comparator<ColumnInfoModel> {
    
    @JsonProperty("name")
    private ColumnDispNameModel dispName;

    private String caption;

    @JsonProperty("visible")
    private boolean isVisible;

    @JsonProperty("column_order")
    private int columnOrder;
    
    @Override
    public int compare(ColumnInfoModel order1, ColumnInfoModel order2) {
        return order2.columnOrder - order1.columnOrder;
    }
}