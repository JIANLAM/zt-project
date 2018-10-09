package com.szcti.lcloud.readingt.service;

import com.szcti.lcloud.readingt.entity.ReadingAudio;

import java.util.List;
import java.util.Map;

/**
 * Created by linzj on 2018/9/10
 */
public interface ReadingAudioService{

    //查询资源
    List<Map<String,Object>> queryAudioList();

}
