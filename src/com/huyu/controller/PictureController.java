package com.huyu.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyu.entity.Picture;
import com.huyu.service.PictureService;

@Controller
@RequestMapping("/pic")
public class PictureController {

	private PictureService pictureService;

	public PictureService getPictureService() {
		return pictureService;
	}
	@Resource
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	@RequestMapping("/showBanner")
	public @ResponseBody List<Picture> showPicByBanner(){
		List<Picture> list = pictureService.list(0);
		return list;
	}
	
}
