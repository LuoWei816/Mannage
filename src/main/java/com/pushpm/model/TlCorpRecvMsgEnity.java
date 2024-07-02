package com.pushpm.model;

import java.util.Date;

public class TlCorpRecvMsgEnity {

    private String tableName;
    private String did;
    private String msgPort;
    private String mobile;
    private String msgContent;
    private String tplId;
    private String startTime;
    private String endTime;
    private String other;

    public TlCorpRecvMsgEnity(String tableName, String did, String msgPort, String mobile, String msgContent, String tplId, String startTime, String endTime, String other) {
        this.tableName = tableName;
        this.did = did;
        this.msgPort = msgPort;
        this.mobile = mobile;
        this.msgContent = msgContent;
        this.tplId = tplId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.other = other;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getMsgPort() {
        return msgPort;
    }

    public void setMsgPort(String msgPort) {
        this.msgPort = msgPort;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getTplId() {
        return tplId;
    }

    public void setTplId(String tplId) {
        this.tplId = tplId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "TlCorpRecvMsgEnity{" +
                "tableName='" + tableName + '\'' +
                ", did='" + did + '\'' +
                ", msgPort='" + msgPort + '\'' +
                ", mobile='" + mobile + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", tplId='" + tplId + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", other='" + other + '\'' +
                '}';
    }
}
