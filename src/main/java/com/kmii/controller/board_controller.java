package com.kmii.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.kmii.dto.BoardMemberDto;
import com.kmii.dao.BoardDao;
import com.kmii.dao.MemberDao;
import com.kmii.dto.BoardDto;

/**
  @WebServlet : 웹서버가 받기전에 먼저 서블릿이 받아서 처리한다(가로챈다)
 */
@WebServlet("*.do")

public class board_controller extends HttpServlet {

	private static final int PAGE_GROUP_SIZE = 10 ; 
	BoardDao boardDao = new BoardDao();
   
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
		MemberDao memberDao = new MemberDao();
		List<BoardDto> bDtos = new ArrayList<BoardDto>();
	//	List<BoardMemberDto> bmDtos = new ArrayList<BoardMemberDto>();
		HttpSession session = null;
		
		
		
		if(comm.equals("/boardList.do")) { //게시판의 모든 글 보기 요청
			
						
			String search = request.getParameter("search");
			String searchKeyword = request.getParameter("searchKeyword"); // 빈칸인데 검색누르면 null 아니고 "", strip() 넣어서 양쪽 공백삭제
			int page = 1;
			int totalContent = boardDao.countBoard();
			
			
			
			if(request.getParameter("page") == null) {   // 참이면 링크타고 게시판으로 들어온 경우
				page = 1;  // 무조건 첫페이지를 보여주게 됨
			} else { //유저가 보고 싶은 페이지 번호를 클릭한 경우
				page = Integer.parseInt(request.getParameter("page"));  //유저가 클릭한 유저가 보고 싶어하는 페이지의 번호
			}
						
			
			
			if(search != null && searchKeyword !=null && !searchKeyword.strip().isEmpty()) {  // 검색 결과 리스트를 원하는 경우 
				bDtos = boardDao.searchBoardList(searchKeyword, search, page);  // 검색어를 넣어 검색된 애를 dto로 넣어서 arraylist로 넘겨줘야한다
			
			} else {  // 모든 게시판 리스트를 원하는경우 (list.do로 넘어온 경우) 
				 bDtos = boardDao.boardList(page);
			}
			
			
			int totalPage = (int) Math.ceil((double)totalContent / BoardDao.PAGE_SIZE ) ;
			int startPage = (((page-1) / PAGE_GROUP_SIZE) * PAGE_GROUP_SIZE)+1 ;
			int endPage = startPage + (PAGE_GROUP_SIZE -1); // 게시글 없는 페이지까지 출력된다
			
			//마지막 페이지 그룹인 경우에는 실제마지막 페이지로 표시
			// 글갯수 437 - 44페이지 , 실제 endpage는 44로변경
			if (endPage > totalPage) {
				endPage = totalPage;  //  totalPage = 실제 마지막 페이지값 (437 -> 44)
			}
		
			
			
		   // System.out.println(countDtos.get(0));
		   request.setAttribute("bDtos", bDtos);  // 위에 둘중하나 경우를 request 객체에 싣고 포워딩해주는거
		   request.setAttribute("currentPage", page);
		   request.setAttribute("totalPage", totalPage);
		   request.setAttribute("startPage", startPage);
		   request.setAttribute("endPage", endPage); // 페이지 그룹 출력시 마지막 페이지번호
		   
		   
		   
		   viewpage = "boardList.jsp";
		
//			String sid = (String) session.getAttribute("sid");
//			
//			if(sid != null) { //로그인 한 상태
//				viewpage="insert.jsp";
//			} else {
//				response.sendRedirect("login.do?msg=2");
//				return;
//			}
//			
			
			
			
		
		
		} else if(comm.equals("/write.do")) {  // 글 수정 폼 이동
		    request.setCharacterEncoding("utf-8");
		    
		    session = request.getSession(false); // 기존 세션 가져오기, 없으면 null
		    if (session == null || session.getAttribute("sid") == null) {
		        response.sendRedirect("login.do?msg=2");
		        return;
		    }

		    String sid = (String) session.getAttribute("sid");
		    
		    String bnum = request.getParameter("bnum"); // 수정하려고 하는 글 번호
		    BoardDto boardDto = boardDao.contentView(bnum);
		    
		    if(boardDto.getMemberid().equals(sid)) {
		        request.setAttribute("boardDto", boardDto);
		        viewpage = "write.jsp";
		    } else {
		        viewpage = "boardList.do";
		    }
		
		} else if(comm.equals("/modifyOk.do")) {    // 수정
			
			request.setCharacterEncoding("utf-8");
			
			
		
		
			String bnum = request.getParameter("bnum");
			String btitle =	request.getParameter("btitle");
			
			
			String memberid =request.getParameter("memberid");
			String bcontent =request.getParameter("bcontent");
			
			boardDao.boardUpdate(bnum, btitle, bcontent);
			
			BoardDto boardDto = boardDao.getBoardDetail(bnum);
			request.setAttribute("boardDto", boardDto);
			
			viewpage = "boardContent.do";
		
		
		}else if(comm.equals("/delete.do")) {  // 글 삭제
			String bnum=request.getParameter("bnum");
			session = request.getSession(); 
			
			String sid = (String) session.getAttribute("sid");
			
			if(sid != null) { //로그인 한 상태
				viewpage="insert.jsp";
			} else {
				response.sendRedirect("login.do?msg=2");
				return;
			}
			
			viewpage = "insert.jsp";
			
			boardDao.boardDelete(bnum);
			
			viewpage = "boardList.do";
			
		
		}else if(comm.equals("/boardContent.do")) { // 글내용보기
			String bnum = request.getParameter("bnum");
			
			//조회수 올려주는 메서드 호출
			boardDao.updateBhit(bnum); // 조회수 증가
			
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
			
		} else if(comm.equals("/login.do"))	{ //로그인 페이지로 이동
			viewpage ="login.jsp";
		
		}else if(comm.equals("/loginOk.do"))	{
			request.setCharacterEncoding("utf-8");
			String loginId = request.getParameter("id");
			String loginPw = request.getParameter("pw");
			
			int loginFlag=memberDao.loginCheck(loginId, loginPw);
			
			if(loginFlag ==1) {
				session = request.getSession();  // session은 request객체에서 선언해줘야한다. 
				session.setAttribute("sid", loginId);
				
			} else {
				response.sendRedirect("login.do?msg=1");
				return; // 멈춰야하니까 써준다. 안쓰면 밑에 viewpage까지 포워딩 하려고해서 꼭 써야한다.
			}
			
			
			viewpage ="boardList.do";
				
		}else {
			viewpage = "homepage.jsp";
		} 
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewpage); 
		dispatcher.forward(request, response);	
		 // boardList.jsp에게 웹 서블릿 내에서 제작한 request 객체를 전달한 후 boardList.jsp로 이동해라
		
		}
	
	
	  
	    
	}

	
	