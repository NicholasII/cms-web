package com.sun.cms.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.cms.dao.BaseDao;
import com.sun.cms.web.dto.UserGroupDto;

public interface UserGroupDao extends BaseDao<UserGroupDto>{
	
	List<String> havingGroups(@Param("userid")String userid);
	
}