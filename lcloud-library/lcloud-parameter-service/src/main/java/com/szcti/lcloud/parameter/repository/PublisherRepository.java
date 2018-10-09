package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.Publisher;
import com.szcti.lcloud.parameter.entity.PublisherCriteria;
import com.szcti.lcloud.parameter.entity.vo.PublisherVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface PublisherRepository {
    int countByExample(PublisherCriteria example);

    int deleteByExample(PublisherCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Publisher record);

    int insertSelective(Publisher record);

    List<Publisher> selectByExample(PublisherCriteria example);

    Publisher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Publisher record, @Param("example") PublisherCriteria example);

    int updateByExample(@Param("record") Publisher record, @Param("example") PublisherCriteria example);

    int updateByPrimaryKeySelective(Publisher record);

    int updateByPrimaryKey(Publisher record);

    List<PublisherVO> queryPage(PublisherVO publisherVO);
}