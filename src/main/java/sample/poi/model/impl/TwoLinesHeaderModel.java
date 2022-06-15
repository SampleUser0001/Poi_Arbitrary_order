package sample.poi.model.impl;

import lombok.Data;
import sample.poi.model.HeaderModel;

@Data
public class TwoLinesHeaderModel implements HeaderModel {
    private String second;
    
    public TwoLinesHeaderModel() {}
    public TwoLinesHeaderModel(String first, String second) {
        this.header = first;
        this.second = second;
    }
}