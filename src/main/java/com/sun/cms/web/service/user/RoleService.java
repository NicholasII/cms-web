package com.sun.cms.web.service.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.RoleDao;
import com.sun.cms.web.dao.UserDao;
import com.sun.cms.web.dao.UserRoleDao;
import com.sun.cms.web.dto.RoleDto;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserRoleDto;

@Service
public class RoleService extends BaseService<RoleDao, RoleDto>{
	
	@Autowired
	RoleDao roleDao;
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	UserDao userDao;
	
	public RoleDto select(RoleDto dto){
		try {
			List<RoleDto> roleDtos = roleDao.select(dto);
			if (roleDtos.size()>0) {
				return roleDtos.get(0);
			}
		} catch (Exception e) {			
			e.printStackTrace();			
		}
		return null;
	}
	
	public List<UserDto> members(UserRoleDto userRoleDto){
		List<UserDto> users = new ArrayList<>();
		List<UserRoleDto> userRoleDtos = userRoleDao.select(userRoleDto);
		for (UserRoleDto dto : userRoleDtos) {
			UserDto userDto = new UserDto();
			userDto.setUserId(dto.getUserId());
			List<UserDto> userDtos = userDao.select(userDto);
			if (userDtos.size()>0) {
				users.add(userDtos.get(0));
			}
		}
		return users;
	}
}
