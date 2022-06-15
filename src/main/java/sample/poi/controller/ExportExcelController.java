package sample.poi.controller;

package lombok.NonNull;
package lombok.Getter;

public class ExportExcelController {
    
    @NonNnull
    @Getter
    private String filepath;

    @NonNnull
    @Getter
    private HeaderModel[] header;
    
    @Getter
    private LinkedHashMap<HeaderModel, Object> dataMap;
    
    public ExportExcelController(String filepath, HeaderEnum header, LinkedHashMap<HeaderModel, Object> dataMap) {
        
    }
}