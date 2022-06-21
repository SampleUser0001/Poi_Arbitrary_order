package sample.poi.enums;

import lombok.Getter;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public enum DateFormatStyle {
    None(""){
        @Override
        public void setDataFormat(XSSFWorkbook workbook, XSSFCellStyle cellStyle){
            // 日付項目ではない。何もしない。
        }
    },
    Date_YYYYMMDD("yyyy/mm/dd"){
        @Override
        public void setDataFormat(XSSFWorkbook workbook, XSSFCellStyle cellStyle){
            this._setDataFormat(workbook, cellStyle);
        }
    },
    Date_HMM("hmm"){
        @Override
        public void setDataFormat(XSSFWorkbook workbook, XSSFCellStyle cellStyle){
            this._setDataFormat(workbook, cellStyle);
        }
    };

    @Getter
    private String format;
    private DateFormatStyle(String format) {
        this.format = format;
    }

    public abstract void setDataFormat(XSSFWorkbook workbook, XSSFCellStyle cellStyle);
    protected void _setDataFormat(XSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        short style = workbook.getCreationHelper()
                                .createDataFormat()
                                .getFormat(this.format);
        cellStyle.setDataFormat(style);
    }
}
