package sample.poi.model.impl;

import lombok.Data;
import sample.poi.model.HeaderModel;

@Data
public class OneLineHeaderModel implements HeaderModel {
    public OneLineHeaderModel() {}
    public OneLineHeaderModel(String header) {
        this.header = header;
    }
}