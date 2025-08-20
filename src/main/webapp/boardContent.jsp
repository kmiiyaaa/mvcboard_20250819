<%@ page import="com.kmii.dao.BoardDao" %>
<%@ page import="com.kmii.dto.BoardDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

	
<% 	
		System.out.println(request.getParameter("msg"));
	
		if(request.getParameter("error") != null) {
			out.println("<script>alert('해당글이 존재하지 않습니다');window.location.href('boardList.do');</script>");
		}
	

%>


<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>Board Detail</title>
  <link rel="stylesheet" href="css/boardStyle.css">
</head>
<div class="nav">
    <div class="nav-inner">
        <div class="brand">
            <a href="homepage.jsp">HOME</a>
        </div>
        <div class="menu">
            <a href="boardList.do">자유게시판</a>  | 
            <a href="login.jsp">로그인</a>
        </div>
    </div>
</div>

<main class="container" align="center">
  <h1 class="post-title">${boardDto.btitle}</h1>
  <hr>
  
  <!-- 메타 정보 영역 -->
  <div class="meta-box" align="center">
    <span><strong>번호</strong> ${boardDto.bnum}</span>
      
    <span><strong>작성자</strong> ${boardDto.memberid}</span>
    <span><strong>이메일</strong> ${}</span>
    <span><strong>작성일</strong> ${boardDto.bdate}</span>
	  <span><strong>조회수</strong> ${boardDto.bhit}</span>
	  
   
  </div>
  <hr>

  <!-- 본문 내용 -->
  <div class="content-box" align="center">
  <br><br>
    ${boardDto.bcontent}
  <br><br><br><br>
  </div>
  <a href="boardList.do"><button class="btn"> 글목록보기 </button></a>
	<c:if test="${sessionScope.sid == boardDao.memberid }">
	  <a href="write.do?bnum=${boardDto.bnum}"><button class="btn"> 수정 </button></a>
	  <a href="delete.do?bnum=${boardDto.bnum}"><button class="btn"> 삭제 </button></a>
  </c:if>
  
</main>

    <footer>© 2025 Board</footer>
 
</body>
</html>
