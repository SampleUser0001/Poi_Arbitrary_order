package sample.poi.model.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import sample.poi.model.data.CommonModel;
import sample.poi.model.data.Group01Model;
import sample.poi.model.data.Group02Model;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataModel {
    
    private CommonModel common;
    private Group01Model group01;
    private Group02Model group02;
}
