package com.sun.cms.web.service.user;

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
	/**
	 * 更新用户
	 * @author dongqun
	 * 2018年1月17日上午11:22:15
	 * @param dto
	 * @param roles
	 * @param groups
	 * @return
	 */
	public boolean updateUser(UserDto user, String[] roles, String[] groups) {
		boolean result = true;
		try {
			update(user);
			updateRole(user, roles);
			updateGroup(user, groups);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}
	/**
	 * 更新用户的角色信息
	 * @author dongqun
	 * 2018年1月17日下午5:33:11
	 * @param user
	 * @param roles
	 * @return
	 */
	private boolean updateRole(UserDto user, String[] roles){
		try {
			UserRoleDto userRoleDto = new UserRoleDto();
			userRoleDto.setUserId(user.getUserId());
			List<UserRoleDto> roleDtos = userRoleDao.select(userRoleDto);
			for (UserRoleDto dto : roleDtos) {
				userRoleDao.delete(dto);
			}
			for (int i = 0; i < roles.length; i++) {
				UserRoleDto roleDto = new UserRoleDto();
				roleDto.setUserId(user.getUserId());
				roleDto.setRoleId(roles[i]);
				userRoleDao.insert(roleDto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 更新用户的分组信息
	 * @author dongqun
	 * 2018年1月17日下午6:07:42
	 * @param user
	 * @param groups
	 * @return
	 */
	private boolean updateGroup(UserDto user, String[] groups){
		try {
			UserGroupDto userGroupDto = new UserGroupDto();
			userGroupDto.setUserid(user.getUserId());
			List<UserGroupDto> groupDtos = userGroupDao.select(userGroupDto);
			for (UserGroupDto dto : groupDtos) {
				userGroupDao.delete(dto);
			}
			for (int i = 0; i < groups.length; i++) {
				UserGroupDto groupDto = new UserGroupDto();
				groupDto.setUserid(user.getUserId());
				groupDto.setGroupid(groups[i]);
				userGroupDao.insert(groupDto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
