package sample.poi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import sample.poi.model.disp.HeaderModel;
import sample.poi.model.disp.ColumnInfoModel;
import sample.poi.model.data.DataModel;
import sample.poi.util.Util;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import java.io.IOException;
import java.text.ParseException;

/**
 * Apache POI サンプル
 */
public class ExportTsv {
    
    private Logger logger = LogManager.getLogger();

    public void exec(String[] args) throws IOException , ParseException {
        final String delimiter = "\t";
        
        HeaderModel headerModel = Util.getHeader("sample01.json");
        List<DataModel> dataList = Util.getDatas();

        try( BufferedWriter writer = Files.newBufferedWriter(
                Paths.get(System.getProperty("user.dir"), "export", String.format("sample_%s.tsv", Util.now())),
                Charset.forName("UTF-8"),
                StandardOpenOption.CREATE)) {
                
            writer.write(headerModel.getHeaderToString(delimiter));
            List<ColumnInfoModel> orderdColumnInfoList = headerModel.getHeaderListOrderByColumnOrder();
            for(DataModel dataModel : dataList) {
                List<Object> valueList = new ArrayList<Object>();
                for(ColumnInfoModel columnInfoModel : orderdColumnInfoList) {
                    if(columnInfoModel.isVisible()) {
                        valueList.add(dataModel.get(columnInfoModel.getCaption()));
                    }
                }
                writer.write(
                    valueList.stream()
                             .map(obj -> obj.toString())
                             .collect(Collectors.joining(delimiter))
                    );
                writer.write("\r\n");
            }
        }
    }
    
    public static void main( String[] args ) throws IOException , ParseException {
        new ExportTsv().exec(args);
    }
}
