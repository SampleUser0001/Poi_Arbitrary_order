package sample.poi.model.disp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnInfoModel implements Serializable, Comparator<ColumnInfoModel> {
    
    @JsonProperty("names")
    private List<ColumnDispNameModel> dispName;

    private String caption;

    @JsonProperty("visible")
    private boolean isVisible;

    @Override
    public int compare(ColumnInfoModel order1, ColumnInfoModel order2) {
        for(int i = 0; i < order1.dispName.size(); i++) {
            if( order1.dispName.get(i).getOrder() != order2.dispName.get(i).getOrder()){
                return order1.dispName.get(i).getOrder() - order2.dispName.get(i).getOrder();
            } 
        }
        return 0;
    }
}
