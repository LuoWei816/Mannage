package com.pushpm.mapper;

import com.pushpm.model.PageInfo;
import com.pushpm.model.TlCorpRecvMsg;
import com.pushpm.model.TlCorpRecvMsgEnity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface TlCorpRecvMsgMapper {

     int getCount();

     List<TlCorpRecvMsg> getAll(@Param("tlCorpRecvMsgEnity") TlCorpRecvMsgEnity tn);

     int getAllByParameterCount(TlCorpRecvMsgEnity tn);

     List<TlCorpRecvMsg> getAllByLimit(@Param("tlCorpRecvMsgEnity") TlCorpRecvMsgEnity tn, @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);
}
