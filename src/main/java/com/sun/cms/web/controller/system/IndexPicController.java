package com.sun.cms.web.controller.system;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.indexpic.IndexPic;
import com.sun.cms.web.dto.indexpic.IndexPicTempDto;
import com.sun.cms.web.dto.system.BaseInfo;
import com.sun.cms.web.service.indexpic.IndexPicService;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

@Controller
@RequestMapping("/system/indexPic")
@AuthClass
public class IndexPicController extends BaseController<IndexPic> {
	@Autowired
	IndexPicService indexPicService;
	//首页图片的存储路径
	private static final String file_path = "/resources/upload/indexPic";
	
	@RequestMapping("/addPage")
	@AuthMethod(role = "admin")
	public ModelAndView addPage(){
		ModelAndView modelAndView = new ModelAndView("/indexpic/add");
		return modelAndView;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	@AuthMethod(role = "admin")
	public ModelAndView add(IndexPic indexPic){
		ModelAndView modelAndView = new ModelAndView("/indexpic/list");
		indexPicService.insert(indexPic);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/uploadImg",method=RequestMethod.POST)
	@ResponseBody
	@AuthMethod(role = "admin")
	public ModelMap uploadIndexPicture(HttpServletRequest request,HttpSession session){
		ModelMap map = new ModelMap();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile  indexPic = multipartRequest.getFile("indexPic");
			String oldName = indexPic.getOriginalFilename();
			String newName = new Date().getTime()+"."+FilenameUtils.getExtension(oldName);
			String root_path = session.getServletContext().getRealPath("");
			
			File indexPicDir = new File(root_path+file_path+"/temp");
			if(!indexPicDir.exists()) indexPicDir.mkdirs();
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute("baseinfo");
			double requireHeight = baseInfo.getIndexPicHeight();
			double requireWidth = baseInfo.getIndexPicWidth();
			BufferedImage uploadBi = ImageIO.read(indexPic.getInputStream());
			double picHeight = uploadBi.getHeight();
			double picWidth = uploadBi.getWidth();
			if (picWidth>requireWidth && picWidth/picHeight<requireWidth/requireHeight) {
				Builder<BufferedImage> builder= Thumbnails.of(uploadBi);
				if (picWidth-requireWidth>150) {
					builder.scale((requireWidth+150)/picHeight);
				}else {
					builder.scale(1.0);
				}
				BufferedImage newBi = builder.asBufferedImage();
				String temp_path = root_path + file_path + "/temp/" + newName;
				builder.toFile(new File(temp_path));
				IndexPicTempDto indexPicDto = new IndexPicTempDto();
				indexPicDto.setNewName(newName);
				indexPicDto.setOldName(oldName);
				indexPicDto.setImgHeight(newBi.getHeight());
				indexPicDto.setImgWidth(newBi.getWidth());
				indexPicDto.setIndexPicHeight(new Double(requireHeight).intValue());
				indexPicDto.setIndexPicWidth(new Double(requireWidth).intValue());
				map.addAttribute("state", "success");
				map.addAttribute("indexPicDto", indexPicDto);
			}else {
				map.addAttribute("state", "fail");
				map.addAttribute("message", "输入的图片不符合尺寸");
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map;
	}
}
