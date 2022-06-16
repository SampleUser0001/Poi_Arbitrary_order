package sample.poi;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.nio.file.Files;
import sample.poi.model.disp.HeaderModel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * ヘッダ情報の読み込み
 */
public class ReadJson {
    
    private Logger logger = LogManager.getLogger();

    public void exec(String[] args) throws IOException, JsonProcessingException {
        logger.info("start.");
        try {
            logger.info(this.createHeaderModel(this.importJson()));
        } catch (IOException e) {
            logger.error(e);
        }
        logger.info("finish.");
    }
    
    public String importJson() throws IOException {
        return Files.lines(Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "header", "sample02.json"))
                    .map(Object::toString)
                    .collect(Collectors.joining());
    }
    
    public HeaderModel createHeaderModel(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HeaderModel headerModel
            = mapper.readValue(
                json,
                new TypeReference<HeaderModel>(){}
        );
        return headerModel;
    }
    
    public static void main( String[] args ) throws IOException, JsonProcessingException {
        new ReadJson().exec(args);
    }
}
