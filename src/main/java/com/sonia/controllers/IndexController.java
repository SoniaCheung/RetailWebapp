package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.pageLogics.IndexPageLogic;

@Controller
public class IndexController {
	
	@Resource(name="indexPageLogic")
	IndexPageLogic indexPageLogic;
	
	@RequestMapping(value="/")
	public String goToIndex(HttpServletRequest request){
		List<IndexPageBasicProductDisplay> indexProductBasicDisaplays = indexPageLogic.getAllIndexPageBasixDisplayObjects();
		request.setAttribute("indexProductBasicDisaplays", indexProductBasicDisaplays);
		return "index";
	}

}
