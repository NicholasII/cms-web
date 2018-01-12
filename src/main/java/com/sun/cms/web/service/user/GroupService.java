package com.sun.cms.web.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.GroupDao;
import com.sun.cms.web.dao.UserDao;
import com.sun.cms.web.dao.UserGroupDao;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserGroupDto;

@Service
public class GroupService extends BaseService<GroupDao, GroupDto>{
	
	@Autowired
	GroupDao groupDao;
	@Autowired
	UserGroupDao userGroupDao;
	@Autowired
	UserDao userDao;
	
	public GroupDto select(GroupDto dto){
		try {
			List<GroupDto> groupDtos = groupDao.select(dto);
			if (groupDtos.size()>0) {
				return groupDtos.get(0);
			}
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		return null;
	}
	
	public List<UserDto> members(UserGroupDto userGroupDto){
		List<UserDto> users = new ArrayList<>();
		List<UserGroupDto> userGroupDtos = userGroupDao.select(userGroupDto);
		for (UserGroupDto dto : userGroupDtos) {
			UserDto userDto = new UserDto();
			userDto.setUserId(dto.getUserid());
			List<UserDto> userDtos = userDao.select(userDto);
			if (userDtos.size()>0) {
				users.add(userDtos.get(0));
			}
		}
		return users;
	}
}
