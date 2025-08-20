<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kmii.dao.BoardDao" %>
<%
    request.setCharacterEncoding("UTF-8");

    // POST 요청일 때만 처리
    if(request.getMethod().equalsIgnoreCase("POST")) {
        String btitle = request.getParameter("btitle");
        String memberid = request.getParameter("memberid");
        String bcontent = request.getParameter("bcontent");

        BoardDao dao = new BoardDao();
        dao.insertBoard(btitle, memberid, bcontent); // 기존 메서드 그대로 사용

        // 글 등록 후 게시판 목록으로 이동
        response.sendRedirect("boardList.do");
        return; // JSP 더 이상 실행하지 않음
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <title>글쓰기</title>
  <link rel="stylesheet" href="css/boardStyle.css">
</head>
<body>
<div class="nav">
  <div class="nav-inner container">
    <div class="brand"><a href="homepage.jsp" style="color:inherit; text-decoration:none;">HOME</a></div>
    <div><a href="#" style="color:var(--accent); text-decoration:none">로그인</a></div>
  </div>
</div>

<main class="container">
  <h1>새 글 작성</h1>
  <div class="panel">
    <form method="post" class="table" style="padding:1rem;">
      <div class="row" style="flex-direction:column; align-items:flex-start;">
        <label>제목</label>
        <input type="text" name="btitle" placeholder="제목을 입력하세요"
               style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;">
      </div>

      <div class="row" style="flex-direction:column; align-items:flex-start;">
        <label>작성자</label>
        <input type="text" name="memberid" value=${sessionScope.sid} readonly="readonly"
               style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;">
      </div>

      <div class="row" style="flex-direction:column; align-items:flex-start;">
        <label>내용</label>
        <textarea name="bcontent" rows="10" placeholder="내용을 입력하세요"
                  style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;"></textarea>
      </div>

      <div style="margin-top:1rem; text-align:right;">
        <button type="submit" class="btn">등록하기</button>
        <a href="boardList.do" class="btn">취소</a>
      </div>
    </form>
  </div>

  <footer>© 2025 Board</footer>
</main>
</body>
</html>