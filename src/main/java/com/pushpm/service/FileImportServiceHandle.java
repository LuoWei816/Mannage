package com.pushpm.service;

import com.pushpm.controller.MessagesRecvHandle;
import com.pushpm.mapper.TlCorpRecvMsgMapper;
import com.pushpm.model.TlCorpRecvMsg;
import com.pushpm.model.TlCorpRecvMsgEnity;
import com.pushpm.utils.EncrypAesUtils;
import com.pushpm.utils.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileImportServiceHandle {

    private static final Logger _logger = LoggerFactory.getLogger(FileImportServiceHandle.class);

    private static String spe = "|";

    private static int bufLen = 2048;

    @Resource
    private TlCorpRecvMsgMapper tlCorpRecvMsgMapper;

    private SimpleDateFormat sim = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

    @Async("taskExecutor")
    public void handle(TlCorpRecvMsgEnity te,String needFiled, String fileType, String filePath){
        List<TlCorpRecvMsg> listTCR = tlCorpRecvMsgMapper.getAll(te);
        _logger.info("查询完毕，共获取:"+listTCR.size()+"条记录");
        if(listTCR.size()>0){
            List<Map<String,String>> listMap = new ArrayList<Map<String,String>>();
            for(TlCorpRecvMsg t:listTCR){
                Map<String,String> map = new HashMap<String,String>();
                if(needFiled.contains("did")){
                       map.put("did",t.getDID());
                }
                if(needFiled.contains("tplId")){
                       map.put("tplId",t.getMSG_TPL_ID());
                }
                if(needFiled.contains("mobile")){
                    try {
                        map.put("mobile", EncrypAesUtils.deMsgToStr(t.getMSISDN()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(needFiled.contains("msgPort")){
                       map.put("msgPort",t.getMSG_PORT());
                }
                if(needFiled.contains("recordTime")){
                        map.put("recordTime",sim.format(t.getRECORD_TIME()));
                }
                if(needFiled.contains("content")){
                    try {
                        map.put("content", EncrypAesUtils.deMsgToStr(t.getMSG_CONTENT()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(needFiled.contains("result")){
                        map.put("result",t.getRESULT());
                }
                listMap.add(map);
            }
            _logger.info("map:"+listMap.size()+filePath+";"+needFiled);
            String[] arrayFiled = needFiled.split(",");
            if(StringUtils.equals(fileType,"txt")){
                _logger.info("开始遍历生产文件");
                for(Map<String,String> m:listMap){
                  StringBuilder sb = new StringBuilder();
                  for(int i=0;i<arrayFiled.length;i++) {
                      sb.append(m.get(arrayFiled[i]));
                      sb.append(spe);
                  }
                    sb.append(System.getProperty("line.separator"));
                  _logger.info(sb.toString());
                    try {
                        FileUtils.newFile(filePath, "test.txt", sb.toString(),
                                bufLen, true);
                    } catch (IOException e) {
                        e.printStackTrace();
                        _logger.info("文件生成异常"+e.getMessage());
                    }
                }
            }else if(StringUtils.equals(fileType,"excel")){

            }else{

            }
        }

    }

    private void txtFileCreate(List<Map> listMap){

    }
}
