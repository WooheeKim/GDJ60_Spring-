package com.woo.s1.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woo.s1.board.BbsDAO;
import com.woo.s1.board.BbsDTO;
import com.woo.s1.board.BoardDTO;
import com.woo.s1.board.BoardService;
import com.woo.s1.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO; 
	
	@Override
	public List<BbsDTO> getBoardList(Pager pager) throws Exception {
		pager.makeRow();
		
		pager.makeNum(noticeDAO.getTotalCount(pager));
		
		return noticeDAO.getBoardList(pager);
	}

	@Override
	public int setBoardAdd(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.setBoardAdd(bbsDTO);
	}

	@Override
	public int setBoardUpdate(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int setBoardDelete(BbsDTO bbsDTO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardDTO getBoardDetail(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return noticeDAO.getBoardDetail(boardDTO);
	}
	
}
