package com.kmii.command;

import com.kmii.dao.BoardDao;
import com.kmii.dto.BoardDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BmodifyCommand implements BCommand {
	
	public void excute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDao boardDao = new BoardDao();
		
		String bnum = request.getParameter("bnum");
		String btitle =	request.getParameter("btitle");
		String memberid =request.getParameter("memberid");
		String bcontent =request.getParameter("bcontent");
		
		boardDao.boardUpdate(bnum, btitle, bcontent);  // 새글 수정 메서드 호출
		
		BoardDto boardDto = boardDao.getBoardDetail(bnum);
		request.setAttribute("boardDto", boardDto);
		
		
	}

}
