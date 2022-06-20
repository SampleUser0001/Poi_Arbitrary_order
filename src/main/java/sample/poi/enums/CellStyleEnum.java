package sample.poi.enums;

import java.util.Date;

public enum CellStyleEnum {
    INTEGER(Integer.class ),
    STRING(String.class),
    DATE(Date.class);
    
    private Class clazz;
    private CellStyleEnum(Class clazz) {
        this.clazz = clazz;
    }
}