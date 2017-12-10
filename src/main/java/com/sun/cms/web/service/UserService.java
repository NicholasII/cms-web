package com.sun.cms.web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.GroupDao;
import com.sun.cms.web.dao.RoleDao;
import com.sun.cms.web.dao.UserDao;
import com.sun.cms.web.dao.UserGroupDao;
import com.sun.cms.web.dao.UserRoleDao;
import com.sun.cms.web.dto.GroupDto;
import com.sun.cms.web.dto.RoleDto;
import com.sun.cms.web.dto.UserDto;
import com.sun.cms.web.dto.UserGroupDto;
import com.sun.cms.web.dto.UserRoleDto;

@Service
public class UserService extends BaseService<UserDao, UserDto>{
	@Autowired
	UserDao userDao;
	@Autowired
	UserGroupDao userGroupDao;
	@Autowired
	GroupDao groupDao;
	@Autowired
	UserRoleDao userRoleDao;
	@Autowired
	RoleDao roleDao;
	/**
	 * 用户属于的分组
	 * @param userDto
	 * @return
	 */
	public List<GroupDto> belongGroups(UserDto userDto){
		UserGroupDto userGroupDto = new UserGroupDto();
		userGroupDto.setUserid(userDto.getUserId());
		List<UserGroupDto> userGroupDtos = userGroupDao.select(userGroupDto);
		if (userGroupDtos!=null) {
			List<GroupDto> groupDtos = new ArrayList<>();
			for (int i = 0; i < userGroupDtos.size(); i++) {
				GroupDto dto = new GroupDto();
				dto.setGroupId(userGroupDtos.get(i).getGroupid());
				List<GroupDto> templist =   groupDao.select(dto);
				if (templist!=null && templist.size()>0) {
					groupDtos.add(templist.get(0));
				}				
			}
			return groupDtos;
		}
		return null;
	}
	/**
	 * 用户拥有的角色
	 * @param userDto
	 * @return
	 */
	public List<RoleDto> havingRoles(UserDto userDto){
		UserRoleDto userRoleDto = new UserRoleDto();
		userRoleDto.setUserId(userDto.getUserId());
		List<UserRoleDto> userRoleDtos = userRoleDao.select(userRoleDto);
		if (userRoleDtos!=null) {
			List<RoleDto> roleDtos = new ArrayList<>();
			for (int i = 0; i < userRoleDtos.size(); i++) {
				RoleDto dto = new RoleDto();
				dto.setRoleId(userRoleDtos.get(i).getRoleId());
				List<RoleDto> templist =  roleDao.select(dto);
				if (templist!=null && templist.size()>0) {
					roleDtos.add(templist.get(0));
				}				
			}
			return roleDtos;
		}
		return null;
	}
	
	/**
	 * 新增用户
	 */
	public boolean createUser(UserDto dto,String[] roles,String[] groups){
		boolean result = true;
		try {
			boolean success = insert(dto);
			if (success) {
				for (String group : groups) {
					UserGroupDto userGroupDto = new UserGroupDto();
					userGroupDto.setGroupid(group);
					userGroupDto.setUserid(dto.getUserId());
					userGroupDao.insert(userGroupDto);
				}
				for (String role : roles) {
					UserRoleDto  userRoleDto = new UserRoleDto();
					userRoleDto.setRoleId(role);
					userRoleDto.setUserId(dto.getUserId());
					userRoleDao.insert(userRoleDto);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return result;
	}
}
