package com.pushpm.utils;


import com.alibaba.excel.EasyExcel;
import com.pushpm.model.TlCorpRecvExcelData;

import java.util.List;

public class ExcelUtils {

    /**
     *
     * @param filePath
     * @param fileName
     * @param excelClass
     * @param list
     */
    private  static void excelCreate(String filePath, String fileName,  String sheetName, Object excelClass, List<Object> list){
        String fileAbsoPath = filePath+"/"+fileName;
        EasyExcel.write(fileAbsoPath, excelClass.getClass())
                .sheet()
                .doWrite(list);
    }
}
