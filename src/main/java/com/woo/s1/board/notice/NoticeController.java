package com.woo.s1.board.notice;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.woo.s1.board.BbsDTO;
import com.woo.s1.board.BoardDTO;
import com.woo.s1.board.BoardFileDTO;
import com.woo.s1.util.Pager;

@Controller
@RequestMapping("/notice/*")
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("boardName")
	public String getBoardName() {
		return "notice";
	}
	
	@GetMapping("listTop")
	public ModelAndView getBoardListTop(Pager pager) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		pager.setPerPage(5L);
		
		List<BbsDTO> ar = noticeService.getBoardList(pager);
		
		modelAndView.addObject("list", ar);
		modelAndView.setViewName("common/noticeResult");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public ModelAndView getBoardList(Pager pager) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<BbsDTO> ar = noticeService.getBoardList(pager);
		
		modelAndView.addObject("list", ar);
		modelAndView.setViewName("board/list");
		return modelAndView;
	}
	
	@GetMapping("add")
	public ModelAndView setBoardAdd() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("board/add");
		return modelAndView;
	}
	
	@PostMapping("add")
	public ModelAndView setBoardAdd(NoticeDTO noticeDTO, MultipartFile [] addFiles, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		int result = noticeService.setBoardAdd(noticeDTO, addFiles, session);
		
		String message = "등록 실패";
		
		if(result>0) {
			message = "글이 등록 되었습니다.";
		}
		
		modelAndView.addObject("result", message);
		modelAndView.addObject("url", "./list");
		modelAndView.setViewName("common/result");
		return modelAndView;
		
	}
	
	@GetMapping("detail")
	public ModelAndView getBoardDetail(NoticeDTO noticeDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		BoardDTO boardDTO = noticeService.getBoardDetail(noticeDTO);
		modelAndView.addObject("dto", boardDTO);
		modelAndView.setViewName("board/detail");
		
		return modelAndView;		
	}
	
	
	@PostMapping("delete")
	public ModelAndView setBoardDelete(BbsDTO bbsDTO, HttpSession session) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("common/result");
		
		int result = noticeService.setBoardDelete(bbsDTO, session);
		
		String message = "삭제 실패";
		
		if(result>0) {
			message = "삭제되었습니다.";
		}
		
		modelAndView.addObject("result", message);
		modelAndView.addObject("url", "./list");
		
		return modelAndView;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFileDTO boardFileDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		boardFileDTO = noticeService.getBoardFileDetail(boardFileDTO);
		
		modelAndView.addObject("boardFile", boardFileDTO);
		modelAndView.setViewName("fileDownView");
		
		
		return modelAndView;
	}
	
	@GetMapping("update")
	public ModelAndView setBoardUpdate(BoardDTO boardDTO) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		boardDTO = noticeService.getBoardDetail(boardDTO);
		
		modelAndView.addObject("dto", boardDTO);
		modelAndView.setViewName("board/update");
		
		return modelAndView;
	}
}
