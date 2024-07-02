package com.pushpm.controller;

import com.pushpm.mapper.TbMmsRejectSignMapper;
import com.pushpm.mapper.TlCorpRecvMsgMapper;
import com.pushpm.model.TbMmsRejectSign;
import com.pushpm.model.TlCorpRecvMsg;
import com.pushpm.model.TlCorpRecvMsgEnity;
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
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Controller
public class MessagesRecvHandle {
    private static final Logger _logger = LoggerFactory.getLogger(MessagesRecvHandle.class);
    private SimpleDateFormat sim = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
    final Base64 decoder = new Base64();
    private String strKey = "wappush@2021";
    @Resource
    private TbMmsRejectSignMapper tbMmsRejectSignMapper;

    @Resource
    private TlCorpRecvMsgMapper tlCorpRecvMsgMapper;

    @GetMapping("/recv")
    @ResponseBody
    public Map doGet(HttpServletRequest request, HttpServletResponse resp){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        return resultMap;
    }

    @PostMapping("/recv")
    @ResponseBody
    public String doPost(){
       _logger.info("post recv ok");
       return "ok";
    }
}
