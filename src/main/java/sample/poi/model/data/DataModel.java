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
    
    public Object get(String caption) throws IllegalArgumentException {
        return CaptionEnum.valueOf(caption).get(this);
    }

    private enum CaptionEnum {
        Column01 { 
            @Override
            Object get(DataModel model) { return model.getCommon().getId(); }
        } ,
        Column02 { 
            @Override 
            Object get(DataModel model) { return model.getCommon().getValue(); }
        } ,
        From { 
            @Override
            Object get(DataModel model) { return model.getCommon().getFrom(); }
        } ,
        To {
            @Override
            Object get(DataModel model) { return model.getCommon().getTo(); }
        } ,
        Group01_value01 {
            @Override
            Object get(DataModel model) { return model.getGroup01().getValue1(); }
        } ,
        Group01_value02 {
            @Override
            Object get(DataModel model) { return model.getGroup01().getValue2(); }
        } ,
        Group02_value01 {
            @Override
            Object get(DataModel model) { return model.getGroup02().getValue1(); }
        } ,
        Group02_value02 {
            @Override
            Object get(DataModel model) { return model.getGroup02().getValue2(); }
        };
        
        abstract Object get(DataModel model);
    }
}
