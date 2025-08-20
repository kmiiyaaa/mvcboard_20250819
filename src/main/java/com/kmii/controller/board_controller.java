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
		
		
		
		} else if(comm.equals("/write.do")) {  // 글 수정 폼 이동
			String bnum = request.getParameter("bnum"); // 수정하려고 하는 글 번호
			BoardDto boardDto = boardDao.contentView(bnum);
			
			request.setAttribute("boardDto", boardDto);
			viewpage = "write.jsp";
		
		
		} else if(comm.equals("/modifyOk.do")) {  
			
			request.setCharacterEncoding("utf-8");
		
			String bnum = request.getParameter("bnum");
			String btitle =	request.getParameter("btitle");
			String memberid =request.getParameter("memberid");
			String bcontent =request.getParameter("bcontent");
			
			boardDao.boardUpdate(bnum, btitle, bcontent);
			
			BoardDto boardDto = boardDao.getBoardDetail(bnum);
			request.setAttribute("boardDto", boardDto);
			
			viewpage = "boardContent.jsp";
		
		
		}else if(comm.equals("/delete.do")) {  // 글 삭제
			
			String bnum=request.getParameter("bnum");
			

			boardDao.boardDelete(bnum);
			
			viewpage = "boardList.do";
			
		
		}else if(comm.equals("/boardContent.do")) { // 글내용보기
			String bnum = request.getParameter("bnum");  //유저가 삭제할 글의 번호
			
			BoardDto boardDto = boardDao.contentView(bnum); //boardDto 반환(유저가 선택한 글번호에 해당하는 dto반환)
			
			if(boardDto == null) {  // 해당글이 존재하지 않음
				//request.setAttribute("msg", "존재하지 않는 글입니다.");
				response.sendRedirect("boardContent.jsp?msg=1"); 
				return;
			} 
			request.setAttribute("boardDto", boardDto);
						
				
			viewpage = "boardContent.jsp";
			
		} else if(comm.equals("/writeOk.do")) {  // 글 수정
			request.setCharacterEncoding("utf-8");
			
			String btitle =	request.getParameter("btitle");
			String memberid =request.getParameter("memberid");
			String bcontent =request.getParameter("bcontent");
			
			boardDao.insertBoard(btitle, memberid, bcontent);  // 새글이 DB에 입력
			response.sendRedirect("boardList.do");  // 포워딩 하지 않고 강제로 list.do 이동
			return; // 프로그램 진행 멈춤
			
		} else if(comm.equals("/login.do"))	{
			
			
			viewpage ="login.jsp";
		
		
		}else {
			viewpage = "homepage.jsp";
		} 
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage); 
		dispatcher.forward(request, response);	
		 // boardList.jsp에게 웹 서블릿 내에서 제작한 request 객체를 전달한 후 boardList.jsp로 이동해라
		
	}
	
	
	  
	    
	}

	
	