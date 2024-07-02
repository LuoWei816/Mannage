package com.pushpm.controller;

import com.alibaba.excel.EasyExcel;
import com.pushpm.mapper.TlCorpRecvMsgMapper;
import com.pushpm.model.TlCorpRecvExcelData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushPmMessageControllerTest2 {
    private static final Logger _logger = LoggerFactory.getLogger(PushPmMessageControllerTest2.class);

    @Test
    public void test(){
        List<TlCorpRecvExcelData> list = new ArrayList<TlCorpRecvExcelData>();
        TlCorpRecvExcelData trd = new TlCorpRecvExcelData();
        trd.setDid("1044");
        trd.setTplId("10440108");
        trd.setMobile("15881677315");
        list.add(trd);
        String fileName = "D:\\Documents\\Desktop\\excelTest.xlsx";
        EasyExcel.write(fileName, TlCorpRecvExcelData.class)
                .sheet("接收列表")
                .doWrite(list);
    }
}