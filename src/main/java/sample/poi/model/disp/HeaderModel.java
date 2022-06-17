package sample.poi.model.disp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Collections;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeaderModel implements Serializable {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("format_id")
    private String formatId;

    @JsonProperty("header_line")
    private int headerLine;

    @JsonProperty("headers")
    private List<ColumnInfoModel> headerList;
    
    public List<ColumnInfoModel> getHeaderListOrderByColumnOrder() {
        // TODO データのディープコピーについて確認
        List<ColumnInfoModel> workList
            = this.headerList.stream()
                             .map(column -> SerializationUtils.clone(column))
                             .collect(Collectors.toList());
        Collections.sort(workList, new ColumnInfoModel());
        return workList;
    }
    
    public String getHeaderToString(String delimiter) {
        List<ColumnInfoModel> orderedList = this.getHeaderListOrderByColumnOrder();
        String first = orderedList.stream()
                                  .filter(model -> model.isVisible())
                                  .map(model -> model.getDispName().getFirst())
                                  .collect(Collectors.joining(delimiter)) + "\r\n";
        String second = "";
        if (this.headerLine == 2) {
            second = orderedList.stream()
                                .filter(model -> model.isVisible())
                                .map(model -> model.getDispName().getSecond())
                                .collect(Collectors.joining(delimiter)) + "\r\n";
        }
        return first + second;
    }
}