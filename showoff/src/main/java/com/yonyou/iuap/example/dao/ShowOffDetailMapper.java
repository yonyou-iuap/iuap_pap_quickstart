package com.yonyou.iuap.example.dao;

import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericExMapper;
import com.yonyou.iuap.example.entity.ShowOffDetail;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;

@MyBatisRepository
public interface ShowOffDetailMapper extends GenericExMapper<ShowOffDetail> {
}
