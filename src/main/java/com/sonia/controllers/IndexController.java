package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sonia.displayObjects.IndexPageBasicProductDisplay;
import com.sonia.pageLogics.IndexPageLogic;

@Controller
public class IndexController {
	
	@Resource(name="indexPageLogic")
	IndexPageLogic indexPageLogic;
	
	@RequestMapping(value="/")
	public String goToIndex(HttpServletRequest request){
		List<IndexPageBasicProductDisplay> indexProductBasicDisaplays = indexPageLogic.getAllIndexPageBasicDisplayObjects();
		request.setAttribute("indexProductBasicDisaplays", indexProductBasicDisaplays);
		return "index";
	}

	@RequestMapping(value = "/category")
	public String goToCategory(@RequestParam("categoryName") String categoryName, HttpServletRequest request) {
		List<IndexPageBasicProductDisplay> indexProductBasicDisaplays = indexPageLogic.getIndexPageBasicDisplayObjectsByCategory(categoryName);
		request.setAttribute("indexProductBasicDisaplays", indexProductBasicDisaplays);
		return "index";
	}

}
