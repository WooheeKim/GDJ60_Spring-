package com.woo.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.woo.s1.board.BbsDTO;
import com.woo.s1.board.BbsService;
import com.woo.s1.util.Pager;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private BbsService noticeService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return "notice";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getBoardList(Pager pager) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<BbsDTO> ar = noticeService.getBoardList(pager);
		
		modelAndView.addObject("list", ar);
		modelAndView.setViewName("board/list");
		
		return modelAndView;
	}
}
