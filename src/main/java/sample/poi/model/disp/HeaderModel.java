package sample.poi.model.disp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Comparator;
import java.lang.StringBuffer;
import java.io.Serializable;
import org.apache.commons.lang3.SerializationUtils;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    public Stream<ColumnInfoModel> getHeaderListOrderByColumnOrder() {
        // TODO データのディープコピーについて確認
        return this.headerList.stream()
                              .map(column -> SerializationUtils.clone(column))
                              .sorted(Comparator.comparing(ColumnInfoModel::getColumnOrder));
    }
    
    public String getHeaderToString(String delimiter, boolean isExistLineNumber) {
        StringBuffer buf = new StringBuffer();
        for(int i=0 ; i<this.headerLine ; i++){
            final int line = i;
            buf.append(isExistLineNumber ? "\t" : "")
               .append(this.getHeaderListOrderByColumnOrder()
                           .filter(model -> model.isVisible())
                           .map(model -> model.getDispName().get(line).getName())
                           .collect(Collectors.joining(delimiter)))
               .append("\r\n");
        }
        return buf.toString();
    }
}