package sample.poi.model.disp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

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
}