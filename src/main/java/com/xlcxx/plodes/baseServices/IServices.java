package com.xlcxx.plodes.baseServices;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 10:44
 * version 2.0
 * 方法说明  实现类统一接口
 */
@Service
public interface IServices<T> {

	List<T> selectAll();

	T selectByKey(Object key);

	int save(T entity);

	int delete(Object key);

	int batchDelete(List<String> list, String property, Class<T> clazz);

	int batchUpdate(List<String> list,String property,Class<T> clazz,T entity);

	int updateAll(T entity);


	int updateNotNull(T entity);

	List<T> selectByExample(Object example);

}
