package com.xlcxx.web.controller.baseController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlcxx.utils.QueryRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 11:26
 * version 2.0
 * 方法说明
 */
public class BaseController {
	/**分页数据**/
	private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
		Map<String, Object> rspData = new HashMap<>();
		rspData.put("data", pageInfo.getList());
		rspData.put("count", pageInfo.getTotal());
		rspData.put("code", 0);
		rspData.put("msg", "");
		return rspData;
	}
	/**分页方法**/
	protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return getDataTable(pageInfo);
	}
	/**排序分页**/
	protected Map<String, Object> selectByPageNumSizeOrderBy(QueryRequest request, Supplier<?> s) {
		PageHelper.startPage(request.getPageNum(), request.getPageSize(),request.getSort()+" "+request.getSortOrder());
		PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
		PageHelper.clearPage();
		return getDataTable(pageInfo);
	}
}
