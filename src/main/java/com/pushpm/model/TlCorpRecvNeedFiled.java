package com.pushpm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TlCorpRecvNeedFiled {
    private String did;
    private String msgPort;
    private String msgContent;
    private String msgTplId;
    private Date recordTime;
    private String mobile;
    private String result;
}
