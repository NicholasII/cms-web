package com.sun.cms.web.service.syetem;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.dao.indexpic.IndexPicDao;
import com.sun.cms.web.dao.topic.AttachmentDao;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.indexpic.IndexPic;
import com.sun.cms.web.dto.topic.AttachmentDto;

@Service
public class SystemCleanService {
	
	@Autowired
	AttachmentDao attachmentDao;
	@Autowired
	IndexPicDao indexPicDao;
	/**
	 * 获取没有使用的附件-加分页
	 * @author dongqun
	 * 2018年3月6日下午2:29:24
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageDto<AttachmentDto> getNoUseAttachments(int pageNum,int pageSize){
		try {
			PageHelper.startPage(pageNum, pageSize);
			List<AttachmentDto> pagelist = attachmentDao.getNoUseAttachments();
			PageInfo<AttachmentDto> pageInfo = new PageInfo<>(pagelist);
			PageDto<AttachmentDto> pageDto = new PageDto<>();
			pageDto.setRows(pageInfo.getList());
			pageDto.setTotal(pageInfo.getTotal());
			return pageDto;
		} catch (Exception e) {			
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取没有使用的附件的数量
	 * @author dongqun
	 * 2018年3月6日下午2:30:24
	 * @return
	 */
	public int getNoUseAttachSize(){
		Integer count = attachmentDao.getNoUseAttachSize();
		if (count==null) {
			return 0;
		}
		return count;
	}
	/**
	 * 获取首页图片文件夹下除文件目录外的所有文件
	 * @author dongqun
	 * 2018年3月6日下午3:32:10
	 * @return
	 */
	private File[] listIndexPic(){
		String root_path = SystemContext.getRealPath();
		String index_path = root_path +"/resources/upload/indexPic";
		File file = new File(index_path);
		File[] files = file.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				if (pathname.isDirectory()) {
					return false;
				}
				return true;
			}
		});
		return files;
	}
	
	public int getNoUseIndexPicSize(){
		List<IndexPic> indexPics = indexPicDao.select(new IndexPic());
		File[] imgs = listIndexPic();
		int count=0;
		for (File file : imgs) {
			int size=0;
			for (IndexPic indexPic : indexPics) {
				if (file.getName().equals(indexPic.getNewName())) {
					break;				
				}else {
					size++;
				}
			}
			if (size==indexPics.size()) {
				count++;
			}
		}
		return count;
	}
	
	public List<String> getNoUseIndexPics(){
		List<String> result = new ArrayList<>();
		List<IndexPic> indexPics = indexPicDao.select(new IndexPic());
		File[] imgs = listIndexPic();
		for (File file : imgs) {
			int size=0;
			for (IndexPic indexPic : indexPics) {
				if (file.getName().equals(indexPic.getNewName())) {
					break;				
				}else {
					size++;
				}
			}
			if (size==indexPics.size()) {
				result.add(file.getName());
			}
		}
		return result;
	}
	
	public boolean cleanIndexPic(){
		try {
			String root_path = SystemContext.getRealPath();
			String index_path = root_path +"/resources/upload/indexPic/";
			//1、删除临时文件夹中文件
			File tempFile = new File(index_path+"temp");
			File[] temps = tempFile.listFiles();
			for (File file : temps) {
				if (file.exists()) {
					file.delete();
				}
			}
			//2、删除没有用到的首页图片
			List<String> files = getNoUseIndexPics();
			for (String fname : files) {
				File file = new File(index_path+fname);
				if (file.exists()) {
					file.delete();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean cleanAttach(){
		boolean result;
		try {
			List<AttachmentDto> noUseAttachs = attachmentDao.getNoUseAttachments();
			if (noUseAttachs!=null) {
				result = deleteAttach(noUseAttachs);
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Transactional
	public boolean deleteAttach(List<AttachmentDto> dtos){
		try {
			for (AttachmentDto dto : dtos) {
				attachmentDao.delete(dto);
			}			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
