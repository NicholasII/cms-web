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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.sun.cms.model.PageDto;
import com.sun.cms.web.auth.AuthClass;
import com.sun.cms.web.auth.AuthMethod;
import com.sun.cms.web.common.BaseController;
import com.sun.cms.web.dto.SystemContext;
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
	public static final String file_path = "/resources/upload/indexPic";
	public final static int T_W = 120;
	
	@RequestMapping("/addPage")
	@AuthMethod(role = "admin")
	public ModelAndView addPage(){
		ModelAndView modelAndView = new ModelAndView("/indexpic/add");
		return modelAndView;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@AuthMethod(role = "admin")
	public ModelAndView add(IndexPic indexPic){
		ModelAndView modelAndView = new ModelAndView("/indexpic/load");
		try {
			indexPic.setCreateDate(new Date());
			indexPicService.insertAndUpdatePos(indexPic);
			modelAndView.addObject("state", true);
		} catch (Exception e) {	
			modelAndView.addObject("state", false);
			e.printStackTrace();
		}
		return modelAndView;
	}
	@RequestMapping("/deleteIndexPic/{id}")
	public ModelAndView delete(@PathVariable int id){
		IndexPic indexPic = new IndexPic();
		indexPic.setId(id);
		boolean result = indexPicService.delete(indexPic);
		return list();
	}
	@RequestMapping("/updateIndexPic/{id}")
	@AuthMethod(role="admin")
	public ModelAndView updatePage(@PathVariable int id){
		ModelAndView modelAndView = new ModelAndView("/indexpic/update");
		IndexPic indexPic = new IndexPic();
		indexPic.setId(id);
		indexPic = indexPicService.getOne(indexPic);
		if (indexPic!=null) {
			modelAndView.addObject("indexpic",JSON.toJSONString(indexPic));
		}
		return modelAndView;
	}
	
	@RequestMapping("/update")
	@AuthMethod(role="admin")
	public ModelAndView update(IndexPic indexPic){
		boolean result;
		try {
			result = indexPicService.update(indexPic);
			if (result) {
				return list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("/indexpic/update");
		modelAndView.addObject("indexpic",JSON.toJSONString(indexPic));
		return modelAndView;
	}
	
	@RequestMapping("/updatePos/{id}/{pos}/{newPos}")
	@AuthMethod(role="admin")
	public ModelAndView updatePositon(@PathVariable int id,@PathVariable int pos,@PathVariable int newPos){
		boolean result;
		try {
			result = indexPicService.updatePosition(id, pos, newPos);
			if (result) {
				return list();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list();
	}
	
	@RequestMapping("/list")
	@AuthMethod(role = "admin")
	public ModelAndView list(){
		ModelAndView modelAndView = new ModelAndView("/indexpic/list");		
		try {
			IndexPic indexPic = new IndexPic();
			int pageSize = SystemContext.getPageSize();
			int pageNum = SystemContext.getPageOffset();
			PageDto<IndexPic> pageDto = indexPicService.getPageList(indexPic, pageNum, pageSize);
			modelAndView.addObject("indexpic", pageDto);
			modelAndView.addObject("total", pageDto.getTotal());
		} catch (Exception e) {	
			e.printStackTrace();
		}
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
	
	@RequestMapping(value="/confirmImg",method=RequestMethod.POST)
	@ResponseBody
	@AuthMethod(role = "admin")
	public ModelMap confirmPicture(int x,int y,int w,int h,String newName,HttpSession session){
		ModelMap map = new ModelMap();
		try {
			BaseInfo baseInfo = (BaseInfo)session.getServletContext().getAttribute("baseinfo");
			int width = baseInfo.getIndexPicWidth();
			int height = baseInfo.getIndexPicHeight();
			String root_path = session.getServletContext().getRealPath("")+file_path;
			String temp_path = root_path + "/temp/" + newName;
			File tempFile = new File(temp_path);
			String thumbnail_path = root_path + "/thumbnail/";
			BufferedImage bi = ImageIO.read(new File(temp_path));
			Builder<BufferedImage> builder = Thumbnails.of(bi);
			String file_path = root_path + "/" +newName;
			BufferedImage newBi = builder.sourceRegion(x, y, w, h).size(width, height).keepAspectRatio(true).asBufferedImage();
			builder.toFile(new File(file_path));
			File thumbnailDir = new File(thumbnail_path);
			if (!thumbnailDir.exists()) {
				thumbnailDir.mkdirs();
			}
			thumbnail_path += newName;
			Thumbnails.of(newBi).scale((double)T_W/(double)width).toFile(new File(thumbnail_path));
			tempFile.delete();
			map.addAttribute("status", "success");			
		} catch (IOException e) {
			map.addAttribute("status", "fail");
			e.printStackTrace();
		}
		return map;
	}
}
