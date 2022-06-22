package sample.poi.model.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import sample.poi.model.data.CommonModel;
import sample.poi.model.data.Group01Model;
import sample.poi.model.data.Group02Model;

import sample.poi.model.style.StyleModel;
import sample.poi.model.style.BorderStyleModel;
import sample.poi.model.style.BorderDetailStyleModel;
import sample.poi.enums.style.*;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.ss.usermodel.BorderStyle;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataModel {
    
    private CommonModel common;
    private Group01Model group01;
    private Group02Model group02;
    private Group03Model group03;
    
    public void setCellValue(XSSFCell cell, String caption) throws IllegalArgumentException {
        CaptionEnum.valueOf(caption).setCellValue(cell, this);
    }

    public Object get(String caption) throws IllegalArgumentException {
        return CaptionEnum.valueOf(caption).get(this);
    }

    public enum CaptionEnum {
        Column01(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) { 
            @Override
            Integer get(DataModel model) { return model.getCommon().getId(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getCommon().getId()); }
        } ,
        Column02(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) { 
            @Override 
            String get(DataModel model) { return model.getCommon().getValue(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getCommon().getValue()); }
        } ,
        From(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.Date_YYYYMMDD, DefaultBorderStyle.THIN.getBorderStyle())) { 
            @Override
            Date get(DataModel model) { return model.getCommon().getFrom(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getCommon().getFrom()); }
            
        } ,
        To(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.Date_YYYYMMDD, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            Date get(DataModel model) { return model.getCommon().getTo(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getCommon().getTo()); }
            
        } ,
        Group01_value01(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup01().getValue1(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup01().getValue1()); }
            
        } ,
        Group01_value02(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup01().getValue2(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup01().getValue2()); }
            
        } ,
        Group02_value01(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup02().getValue1(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup02().getValue1()); }
            
        } ,
        Group02_value02(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup02().getValue2(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup02().getValue2()); }
            
        },
        Group03_value01(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue1(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue1()); }
        },
        Group03_value02(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue2(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue2()); }
        },
        Group03_value03(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue3(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue3()); }
        },
        Group03_value04(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue4(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue4()); }
        },
        Group03_value05(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue5(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue5()); }
        },
        Group03_value06(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue6(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue6()); }
        },
        Group03_value07(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue7(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue7()); }
        },
        Group03_value08(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue8(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue8()); }
        },
        Group03_value09(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
            @Override
            String get(DataModel model) { return model.getGroup03().getValue9(); }
            @Override
            public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue9()); }
        },
        Group03_value10(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
                @Override
                String get(DataModel model) { return model.getGroup03().getValue10(); }
                @Override
                public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue10()); }
        },
        Group03_value11(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
                @Override
                String get(DataModel model) { return model.getGroup03().getValue11(); }
                @Override
                public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue11()); }
        },
        Group03_value12(new StyleModel(FontStyle.MS_Gothic, DateFormatStyle.None, DefaultBorderStyle.THIN.getBorderStyle())) {
                @Override
                String get(DataModel model) { return model.getGroup03().getValue12(); }
                @Override
                public void setCellValue(XSSFCell cell, DataModel model) { cell.setCellValue(model.getGroup03().getValue12()); }
        };

        @Getter
        private StyleModel style;

        private CaptionEnum(StyleModel style) {
            this.style = style;
        }

        /** tsv用。実際のプログラムには不要。 */
        abstract Object get(DataModel model);

        /** 渡されたcellに値を書き込む。 */
        public abstract void setCellValue(XSSFCell cell, DataModel model);
    }
}
