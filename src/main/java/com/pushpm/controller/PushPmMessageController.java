package com.pushpm.controller;

import com.alibaba.fastjson.JSONObject;
import com.pushpm.mapper.TbMmsRejectSignMapper;
import com.pushpm.mapper.TlCorpRecvMsgMapper;
import com.pushpm.model.PageInfo;
import com.pushpm.model.TlCorpRecvMsg;
import com.pushpm.model.TlCorpRecvMsgEnity;
import com.pushpm.service.FileImportServiceHandle;
import com.pushpm.utils.EncrypAesUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PushPmMessageController {
    private static final Logger _logger = LoggerFactory.getLogger(PushPmMessageController.class);

    private SimpleDateFormat sim = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    final Base64 decoder = new Base64();
    private String strKey = "wappush@2021";

    @Resource
    private TlCorpRecvMsgMapper tlCorpRecvMsgMapper;

    @Resource
    private FileImportServiceHandle fileImportServiceHandle;

    @GetMapping("/pushPmApi")
    @ResponseBody
    private Map<String,Object> pushPmApi(HttpServletRequest request, HttpServletResponse resp, String rows, String page){
       _logger.info(rows+";"+page);
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> resultMap = new HashMap<>();
        return getListTlCorpRecvMsg(request, resultMap, _logger, tlCorpRecvMsgMapper,pageInfo);
    }

    static Map<String,Object> getListTlCorpRecvMsg(HttpServletRequest request, Map<String, Object> resultMap, Logger logger, TlCorpRecvMsgMapper tlCorpRecvMsgMapper, PageInfo pageInfo) {
        int startIndex = (pageInfo.getCurrentPages()-1)*pageInfo.getPagesSize();
        int endIndex = pageInfo.getCurrentPages()*pageInfo.getPagesSize();
        List<TlCorpRecvMsg> ntls = new ArrayList<TlCorpRecvMsg>();
        String did =  request.getParameter("did");
        String msgPort = request.getParameter("msgPort");
        String mobile = request.getParameter("mobile");
        String tplId = request.getParameter("tplId");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        logger.info(mobile+";"+startTime);
        try {
            String enMobile = EncrypAesUtils.enMsgToStr(mobile);
            logger.info("加密后的字符串:"+enMobile);
            TlCorpRecvMsgEnity tre = new TlCorpRecvMsgEnity(null, did, msgPort, enMobile, null, tplId, startTime, endTime, null);
            _logger.info(tre.toString());
            int count = tlCorpRecvMsgMapper.getAllByParameterCount(tre);
            List<TlCorpRecvMsg> tls = tlCorpRecvMsgMapper.getAllByLimit(tre,startIndex,endIndex);
            logger.info("获取到的数据:"+tls.size());
            if(count>0){
                for(TlCorpRecvMsg tr:tls){
                    TlCorpRecvMsg temp = tr;
                    temp.setMSISDN(EncrypAesUtils.deMsgToStr(temp.getMSISDN()));
                    temp.setMSG_CONTENT(EncrypAesUtils.deMsgToStr(temp.getMSG_CONTENT()));
                    ntls.add(temp);
                }
            }
            resultMap.put("total",count);
            resultMap.put("data",ntls);
            return resultMap;
        }catch (Exception e){
            resultMap.put("status","false");
            resultMap.put("data","");
            logger.error("加密失败"+e.getMessage());
        }
        return resultMap;
    }

    @GetMapping("/fileImport")
    @ResponseBody
    public Map fileImport(HttpServletRequest request, HttpServletResponse resp){
        String filePath = request.getParameter("file-path");
        String selectParams = request.getParameter("selectParamsArrays");
        String fileType = request.getParameter("fileType");
        String queryParams = request.getParameter("queryParams");
        _logger.info("selectParams"+selectParams);
        TlCorpRecvMsgEnity t = null;
        try {
            t = toGetTlCorpRecvMsg(request.getParameter("queryParams"));
        }catch (Exception e){
             e.printStackTrace();
             _logger.error(e.getMessage());
        }
        if(t != null){
               fileImportServiceHandle.handle(t,selectParams,fileType,filePath);
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("result","success");
        return  result;
    }

    public TlCorpRecvMsgEnity toGetTlCorpRecvMsg(String jsonStr) throws Exception {
        JSONObject jsonObject= JSONObject.parseObject(jsonStr);
        String mobile = jsonObject.getString("mobile");
        if(!mobile.equals("")){
            mobile = EncrypAesUtils.enMsgToStr(mobile);
        }
        TlCorpRecvMsgEnity tre = new TlCorpRecvMsgEnity(null, jsonObject.getString("did"), null, mobile, null, jsonObject.getString("tplId"), jsonObject.getString("startTime"), jsonObject.getString("endTime"), null);
        return tre;
    }
}
