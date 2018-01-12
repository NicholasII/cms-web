package com.sun.cms.web.service.channel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.web.dao.UserGroupDao;
import com.sun.cms.web.dao.channel.GroupChannelDao;
import com.sun.cms.web.dto.channel.ChannelSimpleTree;
import com.sun.cms.web.dto.channel.GroupChannelDto;

@Service
public class UserChannelService{
	@Autowired
	UserGroupDao userGroupDao;
	@Autowired
	GroupChannelDao groupChannelDao;
	
	public List<ChannelSimpleTree> havingChannels(String userid){
		List<ChannelSimpleTree> tree = new ArrayList<>(); 
		List<String> groupIds = userGroupDao.havingGroups(userid);
		if (groupIds!=null && groupIds.size()>0) {
			StringBuilder builder = new StringBuilder();

			for (int i = 0; i < groupIds.size(); i++) {
				builder.append(groupIds.get(i));
				if (i!=groupIds.size()-1) {
					builder.append(",");
				}
			}
			String groups = builder.toString();
			List<GroupChannelDto> channels= groupChannelDao.havingChannels(groups);
			
			for (GroupChannelDto groupChannelDto : channels) {
				ChannelSimpleTree t = new ChannelSimpleTree();
				t.setId(groupChannelDto.getChannelid());
				t.setName(groupChannelDto.getChannelname());
				t.setPid(groupChannelDto.getChannelpid());
				tree.add(t);
			}
		}
		return tree;
		
	}
}
