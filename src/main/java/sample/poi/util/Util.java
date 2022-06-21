package sample.poi.util;

import sample.poi.model.disp.HeaderModel;
import sample.poi.model.data.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Util {
    
    public static HeaderModel getHeader(String filename) throws IOException , JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HeaderModel headerModel
            = mapper.readValue(
                Files.lines(
                        Paths.get(
                            System.getProperty("user.dir"), 
                            "src", "main", "resources", 
                            "header", filename))
                     .map(Object::toString)
                     .collect(Collectors.joining()),
                new TypeReference<HeaderModel>(){}
        );
        return headerModel;        
    }
    
    public static List<DataModel> getDatas() throws ParseException {
        // TODO データは増やす。
        List<DataModel> list = new ArrayList<DataModel>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        for(int index = 1; index<10000; index++) {
            list.add(new DataModel(
                new CommonModel(index, "aaa", format.parse("2022-06-01"), format.parse("2022-06-02")),
                new Group01Model(String.format("g01_value1_%d",index) , String.format("g01_value2_%d",index)),
                new Group02Model(String.format("g02_value1_%d",index) , String.format("g02_value2_%d",index))
            ));
    
        }
        return list;
    }
    
    public static String now() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return format.format(new Date());
    }
}