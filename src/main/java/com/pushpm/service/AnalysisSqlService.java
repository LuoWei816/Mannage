package com.pushpm.service;

import java.util.Date;

public class AnalysisSqlService {
    public void handleSql(String sqlStr){
        if(!sqlStr.isEmpty()){
            if(sqlStr.indexOf("WHERE".toLowerCase())>0){
                String factor = sqlStr.substring(sqlStr.indexOf("WHERE".toLowerCase()),sqlStr.length());
                if(factor.indexOf("did")>0){
                }
            }
        }
    }

    class  queryEntity{
        private String tableName;
        private String did;
        private String msgPort;
        private String msgContent;
        private String tplId;
        private Date startTime;
        private String endTime;
        private String other;

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

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
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
    }
}
