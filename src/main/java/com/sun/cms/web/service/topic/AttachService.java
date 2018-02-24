package com.sun.cms.web.service.topic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.cms.service.BaseService;
import com.sun.cms.web.dao.topic.AttachmentDao;
import com.sun.cms.web.dto.SystemContext;
import com.sun.cms.web.dto.topic.AttachmentDto;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;


@Service
public class AttachService extends BaseService<AttachmentDao, AttachmentDto> {
	
	public final static int IMG_WIDTH = 900;
	public final static int THUMBNAIL_WIDTH = 100;
	public final static int THUMBNAIL_HEIGHT = 80;
	@Autowired
	AttachmentDao attachmentDao;
	
	
	public void bindTopic(String[] attachId, String topicid) throws Exception{
		try {
			for (int i = 0; i < attachId.length; i++) {
				String attach = attachId[i];
				AttachmentDto temp = new AttachmentDto();
				temp.setId(attach);
				AttachmentDto attachment = attachmentDao.selectOne(temp);
				if (attachment!=null) {
					attachment.setTopicid(topicid);
					attachmentDao.update(attachment);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	public List<AttachmentDto> getAttachs(String topicId){
		AttachmentDto attachmentDto = new AttachmentDto();
		attachmentDto.setTopicid(topicId);
		return attachmentDao.select(attachmentDto);
	}
	
	public void add(AttachmentDto attachment,InputStream is) throws Exception{
		try {
			attachmentDao.insert(attachment);
			saveFile(attachment, is);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	private void saveFile(AttachmentDto attachment,InputStream is) throws Exception{
		String realpath = SystemContext.getRealPath();
		String path = realpath + "/resources/upload/";
		String thumbPath = realpath + "/resources/upload/thumbnail/";
		File source = new File(path);
		File thumbFile = new File(thumbPath);
		if(!source.exists()) source.mkdirs();
		if(!thumbFile.exists()) thumbFile.mkdirs();
		path = path + attachment.getNewname();
		thumbPath = thumbPath + attachment.getNewname();
		if (attachment.getIsimg()==1) {
			BufferedImage oldBi = ImageIO.read(is);
			int width = oldBi.getWidth();
			Builder<BufferedImage> bf = Thumbnails.of(oldBi);
			if (width>IMG_WIDTH) {
				bf.scale((double)IMG_WIDTH/(double)width);
			}else {
				bf.scale(1.0);
			}
			bf.toFile(new File(path));
			BufferedImage newBi = Thumbnails.of(oldBi).scale(1.0).asBufferedImage();
			Thumbnails.of(newBi).sourceRegion(Positions.CENTER, THUMBNAIL_WIDTH, THUMBNAIL_HEIGHT).size(100, 80).toFile(new File(thumbPath));
		}else {
			FileUtils.copyInputStreamToFile(is, new File(path));
		}
	}

}
