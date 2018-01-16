package com.sun.cms.web.service.channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
		Map<String, GroupChannelDto> map = new HashMap<>();
		List<String> groupIds = userGroupDao.havingGroups(userid);
		if (groupIds!=null && groupIds.size()>0) {
			/*StringBuilder builder = new StringBuilder();

			for (int i = 0; i < groupIds.size(); i++) {
				builder.append(groupIds.get(i));
				if (i!=groupIds.size()-1) {
					builder.append(",");
				}
			}
			String groups = builder.toString();*/
			for (int i = 0; i < groupIds.size(); i++) {
				GroupChannelDto groupChannelDto = new GroupChannelDto();
				groupChannelDto.setGroupid(groupIds.get(i));
				List<GroupChannelDto> list= groupChannelDao.select(groupChannelDto);
				if (list!=null) {
					if (list.size()>0) {
						for (GroupChannelDto groupChannelDto2 : list) {
							map.put(String.valueOf(groupChannelDto2.getChannelid()), groupChannelDto2);
						}
					}
				}
			}
			
			Set<String> channelids = map.keySet();
			Iterator<String> iterator = channelids.iterator();
			while (iterator.hasNext()) {
				String channelid = iterator.next();
				GroupChannelDto groupChannelDto = map.get(channelid);
				ChannelSimpleTree t = new ChannelSimpleTree();
				t.setId(groupChannelDto.getChannelid());
				t.setName(groupChannelDto.getChannelname());
				t.setPid(groupChannelDto.getChannelpid());
				tree.add(t);
			}
		
			/*for (GroupChannelDto groupChannelDto : channels) {
				ChannelSimpleTree t = new ChannelSimpleTree();
				t.setId(groupChannelDto.getChannelid());
				t.setName(groupChannelDto.getChannelname());
				t.setPid(groupChannelDto.getChannelpid());
				tree.add(t);
			}*/
		}
		return tree;
		
	}
}
