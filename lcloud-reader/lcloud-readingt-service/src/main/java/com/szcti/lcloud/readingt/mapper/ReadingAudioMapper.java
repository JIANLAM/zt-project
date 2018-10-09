package com.szcti.lcloud.readingt.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by linzj on 2018/9/10
 */
@Mapper
public interface ReadingAudioMapper{

    List<Map<String,Object>> queryAudioList();
}
