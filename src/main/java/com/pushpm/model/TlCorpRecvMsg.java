package com.pushpm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TlCorpRecvMsg {
    private String DID;
    private String MSG_PORT;
    private String MSG_CONTENT;
    private String MSG_TPL_ID;
    private Date RECORD_TIME;
    private String MSISDN;
    private String RESULT;
}
