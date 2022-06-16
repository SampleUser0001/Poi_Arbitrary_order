package sample.poi.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonModel {
    private int id;
    private String value;
    private Date from;
    private Date to;
}
