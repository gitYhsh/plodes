package com.xlcxx.web.controller.baseController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xlcxx.config.auth.damain.AuthonUserDetails;
import com.xlcxx.plodes.system.domian.User;
import com.xlcxx.utils.QueryRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
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
	/**获取当前登陆用户**/
	protected User getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		User user = new User();
		if(principal instanceof AuthonUserDetails) {
			AuthonUserDetails userDetails = (AuthonUserDetails) principal;
			user.setUserId(userDetails.getUserid());
			user.setPassword(userDetails.getPassword());
			user.setUsername(userDetails.getUsername());
			user.setNickname(userDetails.getNickname());
			user.setDeptId(Long.valueOf(userDetails.getDeptid()));
			Collection<GrantedAuthority> list = userDetails.getAuthorities();
			List<Object> listall = Arrays.asList(list.toArray());
			List<String> allAuth = new ArrayList<>();
			for (Object ibj : listall) {
				allAuth.add(ibj.toString());
			}
			user.setAllAuthority(allAuth);
		}
		return user;

	}

}
