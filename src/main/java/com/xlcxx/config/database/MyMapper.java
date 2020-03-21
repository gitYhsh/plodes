package com.xlcxx.config.database;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:16
 * version 2.0
 * 方法说明
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
