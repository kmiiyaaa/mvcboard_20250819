package com.kmii.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kmii.dao.BoardDao;
import com.kmii.dto.BoardDto;

/**
  @WebServlet : 웹서버가 받기전에 먼저 서블릿이 받아서 처리한다(가로챈다)
 */
@WebServlet("*.do")
public class board_controller extends HttpServlet {

       
   
    public board_controller() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		//System.out.println(uri);   // /jsp_mvcboard_250819/boardList.do 출력
		
		String conpath = request.getContextPath();
		//System.out.println(conpath);   // /jsp_mvcboard_250819 출력
		
		String comm = uri.substring(conpath.length());  // 최종 요청값
		System.out.println(comm);  //   /*.do 출력
		
		
		String viewpage = null;
		BoardDao boardDao = new BoardDao();
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
		
		
		if(comm.equals("/boardList.do")) { //게시판의 모든 글 보기 요청
		    bDtos = boardDao.boardList();
		    request.setAttribute("bDtos", bDtos); 
		    viewpage = "boardList.jsp";
		} else if(comm.equals("/insert.do")) { // 글쓰기
			viewpage = "insert.jsp";
		} else if(comm.equals("/wirte.do")) {  // 글 수정
			viewpage = "write.jsp";
		}else if(comm.equals("/delete.do")) {  // 글 삭제
			viewpage = "boardList.do";
		}else if(comm.equals("/boardContent.do")) { // 글내용보기
			String bnum = request.getParameter("bnum");
			BoardDto boardDto = boardDao.contentView(bnum);
			request.setAttribute("boardDto", boardDto);
			viewpage = "boardContent.jsp";
			
		} else if(comm.equals("/wirteOk.do")) {  // 글 수정
			request.setCharacterEncoding("utf-8");
			
			String btitle =	request.getParameter("btitle");
			String memberid =request.getParameter("memberid");
			String bcontent =request.getParameter("bcontent");
			
			boardDao.insertBoard(btitle, memberid, bcontent);  // 새글이 DB에 입력
			viewpage = "boardList.do";
			
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage); 
		dispatcher.forward(request, response);	
		 // boardList.jsp에게 웹 서블릿 내에서 제작한 request 객체를 전달한 후 boardList.jsp로 이동해라
		
	}
	
	
	  
	    
	}

	
	