package com.sonia.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String goToIndex(HttpServletRequest request, HttpSession session){
		List<IndexPageBasicProductDisplay> allDisplayProducts = indexPageLogic.getAllIndexPageBasicDisplayObjects();
		return setIndexProductBasicDisaplays(request, allDisplayProducts);
	}

	@RequestMapping(value = "/category")
	public String goToCategory(@RequestParam("categoryName") String categoryName, HttpServletRequest request, HttpSession session) {
		List<IndexPageBasicProductDisplay> categoryDisplayProducts = indexPageLogic.getIndexPageBasicDisplayObjectsByCategory(categoryName);
		return setIndexProductBasicDisaplays(request, categoryDisplayProducts);
	}

	@RequestMapping(value = "/find")
	public String findProducts(@RequestParam("searchKeyword") String keyword, HttpServletRequest request, HttpSession session) {
		List<IndexPageBasicProductDisplay> searchDisplayProducts = indexPageLogic.getIndexPageBasicDisplayObjectsBySearchKey(keyword);
		return setIndexProductBasicDisaplays(request, searchDisplayProducts);
	}
	
	private String setIndexProductBasicDisaplays(HttpServletRequest request, List<IndexPageBasicProductDisplay> data) {
		request.setAttribute("indexProductBasicDisaplays", data);
		return "index";
	}
}
