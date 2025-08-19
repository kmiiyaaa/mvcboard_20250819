<%@ page import="com.kmii.dao.BoardDao" %>
<%@ page import="com.kmii.dto.BoardDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    request.setCharacterEncoding("UTF-8");

    // 수정 여부 판단
    String bnumStr = request.getParameter("bnum");
    BoardDto dto = null;
    boolean isEdit = false;

    if(bnumStr != null && !bnumStr.isEmpty()) {
        int bnum = Integer.parseInt(bnumStr);
        BoardDao dao = new BoardDao();
        dto = dao.getBoardDetail(bnum);
        isEdit = true;
    }

    // POST 요청 처리
    if(request.getMethod().equalsIgnoreCase("POST")) {
        String btitle = request.getParameter("btitle");
        String bcontent = request.getParameter("bcontent");
        String memberid = request.getParameter("memberid");

        BoardDao dao = new BoardDao();

        if(isEdit) {
            dto.setBtitle(btitle);
            dto.setBcontent(bcontent);
            dto.setMemberid(memberid);

            boolean result = dao.updateBoard(dto);
            if(result) {
                response.sendRedirect("boardList.jsp");
                return;
            } else {
                out.println("<script>alert('글 수정 실패'); history.back();</script>");
            }
        } else {
            dto = new BoardDto();
            dto.setBtitle(btitle);
            dto.setBcontent(bcontent);
            dto.setMemberid(memberid);

            boolean result = dao.insertBoard(dto);
            if(result) {
                response.sendRedirect("boardList.jsp");
                return;
            } else {
                out.println("<script>alert('글 등록 실패'); history.back();</script>");
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title><%= isEdit ? "글 수정" : "새 글 작성" %></title>
<link rel="stylesheet" href="css/boardStyle.css">
</head>
<body>
<!-- 상단 네비 -->
<div class="nav">
  <div class="nav-inner container">
    <div class="brand">
      <a href="homepage.jsp" style="color:inherit; text-decoration:none;">HOME</a>
    </div>
    <div>
      <a href="#" style="color:var(--accent); text-decoration:none">로그인</a>
    </div>
    <div><a href="#" style="color:var(--accent); text-decoration:none">로그인</a></div>
  </div>
</div>

<main class="container">
<h1><%= isEdit ? "글 수정" : "새 글 작성" %></h1>
<div class="panel">
<form method="post" class="table" style="padding:1rem;">
  <% if(isEdit) { %>
      <input type="hidden" name="bnum" value="<%= dto.getBnum() %>">
  <% } %>

  <div class="row" style="flex-direction:column; align-items:flex-start;">
    <label>제목</label>
    <input type="text" name="btitle" value="<%= isEdit ? dto.getBtitle() : "" %>" placeholder="제목을 입력하세요"
           style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;">
  </div>

  <div class="row" style="flex-direction:column; align-items:flex-start;">
    <label>작성자</label>
    <input type="text" name="memberid" value="<%= isEdit ? dto.getMemberid() : "" %>" placeholder="작성자를 입력하세요"
           style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;">
  </div>

  <div class="row" style="flex-direction:column; align-items:flex-start;">
    <label>내용</label>
    <textarea name="bcontent" rows="10" placeholder="내용을 입력하세요"
              style="width:100%; padding:0.5rem; border-radius:8px; border:1px solid #444; background:#111; color:#eee;"><%= isEdit ? dto.getBcontent() : "" %></textarea>
  </div>

  <div style="margin-top:1rem; text-align:right;">
    <button type="submit" class="btn"><%= isEdit ? "수정 완료" : "등록하기" %></button>
    <a href="boardList.jsp" class="btn">취소</a>
  </div>
</form>
</div>
<footer>© 2025 Board</footer>
</main>
</body>
</html>
