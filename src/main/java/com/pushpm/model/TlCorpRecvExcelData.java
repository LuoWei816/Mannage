package com.pushpm.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TlCorpRecvExcelData {

    @ExcelProperty("平台ID")
    private String did;
    @ExcelProperty("模板ID")
    private String tplId;
    @ExcelProperty("mobile")
    private String mobile;
    @ExcelProperty("content")
    private String content;
    @ExcelProperty("recordTime")
    private String recordTime;
    @ExcelProperty("result")
    private String result;
}
