<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Homepage</title>
<link rel="stylesheet" href="css/midnight.css">
<style>
:root{
--bg:#0b0b0d; /* 더 어둡게 */
--card:#111114;
--text:#f5f5f7;
--muted:#a1a1aa;
--line:#2a2a2d;
--radius:20px;
--shadow:0 6px 24px rgba(0,0,0,.7);
}


body{
background:var(--bg);
color:var(--text);
font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, sans-serif;
margin:0;
line-height:1.6;
}
a{color:var(--text); text-decoration:none;}


.nav{background:#000; border-bottom:1px solid var(--line); position:sticky; top:0; z-index:10;}
.nav-inner{display:flex; justify-content:space-between; align-items:center; padding:18px 40px; max-width:1200px; margin:0 auto;}
.nav .brand{font-weight:700; font-size:20px;}


.hero{
text-align:center;
padding:120px 20px;
}
.hero h1{
font-size:clamp(36px, 6vw, 72px);
font-weight:800;
letter-spacing:-0.03em;
margin-bottom:20px;
}
.hero p{
font-size:18px;
color:var(--muted);
max-width:600px;
margin:0 auto 40px;
}
.hero .btn{
padding:14px 32px;
border-radius:999px;
border:none;
font-size:18px;
cursor:pointer;
background:linear-gradient(180deg, #0055cc, #003c99);
color:white;
box-shadow:0 8px 30px rgba(0,0,0,.7);
}
.hero .btn:hover{filter:brightness(1.1)}


.features{display:grid; grid-template-columns:repeat(auto-fit,minmax(260px,1fr)); gap:24px; margin:80px 0}
.card{background:var(--card); border:1px solid var(--line); border-radius:var(--radius); padding:40px 24px; box-shadow:var(--shadow); text-align:center;}
.card h3{margin:0 0 12px; font-size:22px}
.card p{margin:0; color:var(--muted)}


footer{margin-top:80px; text-align:center; color:var(--muted); padding:20px}
</style>
</head>
<body>
<!-- 네비게이션 -->
<div class="nav">
<div class="nav-inner container">
<div class="brand">🌙</div>
<div>
<a href="#" style="margin-right:16px;">공지</a>
<a href="boardList.do" style="margin-right:16px;">게시판</a>
<a href="#">로그인</a>
</div>
</div>
</div>


<!-- 히어로 섹션 -->
<section class="hero">
<h1>Welcome</h1>
<p>즐거운 소통 페이지 입니다.</p>
<a href="boardList.do"><button class="btn">게시판 바로가기</button></a>
</section>


<!-- 특징 섹션 -->
<main class="container">
<div class="features">
<div class="card">
<h3>📌 공지</h3>
<p>최신 업데이트와 소식을 확인하세요.</p>
</div>
<div class="card">
  <h3><a href="boardList.do" style="text-decoration:none; color:inherit;">💬 게시판</a></h3>
  <p><a href="boardList.jsp" style="text-decoration:none; color:inherit;">자유롭게 질문하고 소통하세요.</a></p>
</div>
<div class="card">
<h3>🔒 회원</h3>
<p>로그인 후 다양한 기능을 사용할 수 있습니다.</p>
</div>
</div>
</main>


<footer>
© 2025 My Homepage.
</footer>
</body>
</html>