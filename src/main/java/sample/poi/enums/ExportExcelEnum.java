package sample.poi.enums;

import lombok.Getter;
import sample.poi.model.HeaderModel;

public enum ExportExcelEnum {
    REPORT01(),
    REPORT02(),
    REPORT03();

    @Getter
    private HeaderModel[] defaultHeader;
    
    private ExportExcelEnum(HeaderModel[] defaultHeader) {
        this.defaultHeader = defaultHeader;
    }
    
}