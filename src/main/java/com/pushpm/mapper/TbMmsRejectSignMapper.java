package com.pushpm.mapper;

import com.pushpm.model.TbMmsRejectSign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbMmsRejectSignMapper {

    List<TbMmsRejectSign> getAll();
}
