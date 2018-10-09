package com.szcti.lcloud.readingt.service.impl;

import com.szcti.lcloud.readingt.entity.ReadingAudio;
import com.szcti.lcloud.readingt.mapper.ReadingAudioMapper;
import com.szcti.lcloud.readingt.service.ReadingAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by linzj on 2018/9/10
 */
@Service
public class ReadingAudioServiceImpl  implements ReadingAudioService {

    @Autowired
    private ReadingAudioMapper readingAudioMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Map<String,Object>> queryAudioList() {
        return readingAudioMapper.queryAudioList();
    }
}
