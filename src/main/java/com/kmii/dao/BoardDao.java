package com.kmii.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kmii.dto.BoardDto;
import com.kmii.dto.BoardMemberDto;
import com.kmii.dto.MemberDto;


public class BoardDao {
	
	// DB 커넥션 준비
	private String driverName = "com.mysql.jdbc.Driver";   // MYSQL JDBC 드라이버 이름 - mysql사용시 fix값 고정
	private String url = "jdbc:mysql://localhost:3306/jspdb";   // MYSQL이 설치된 서버의 주소(ip)와 연결할 db(스키마) 이름
	private String userName = "root";
	private String password = "12345";	
		
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
		
	public List<BoardDto>  boardList() { // 게시판 모든글 리스트 가져와서 반환하는 메서드, 매개변수 따로 필요는 없다/ 글하나 -> boardDto, 여러개는 list쓴다
		//String sql = "SELECT * FROM board ORDER BY bnum DESC";
		String sql = "SELECT b.bnum, b.btitle, b.bcontent, b.memberid, m.memberemail, b.bdate, b.bhit "
		           + "FROM board b "
		           + "INNER JOIN members m ON b.memberid = m.memberid "
		           + "ORDER BY b.bnum DESC";
		//List<BoardMemberDto> bmDtos = new ArrayList<BoardMemberDto>();
		List<BoardDto> bDtos = new ArrayList<BoardDto>(); 
		
		
		try {
			Class.forName(driverName); 
			conn = DriverManager.getConnection(url, userName, password);
			
			pstmt = conn.prepareStatement(sql);
		
			rs = pstmt.executeQuery();  // 모든 글 리스트(레코드) 반환
			
			
			while(rs.next()) {   // 글이하나가 아니고 여러개라 if는 x, while로 돌려줌
				int bnum = rs.getInt("bnum");  // boarddto에서 받는게 아니고 sql문에서 뽑아준거
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String memberid = rs.getString("memberid");
				String memberemail = rs.getString("memberemail");
				int bhit = rs.getInt("bhit");
				String bdate = rs.getString("bdate");
				
				//BoardMemberDto bmDto = new BoardMemberDto(bnum, btitle, bcontent, memberid, memberemail ,bhit, bdate);
				MemberDto memberDto = new MemberDto();
				memberDto.setMemberid(memberid);
				memberDto.setMemberemail(memberemail);
				
				
				BoardDto bDto = new BoardDto(bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
				bDtos.add(bDto);
				
				
				}
		
		} catch (Exception e) {
			System.out.println("게시판 목록 가져오기 실패"); 
			e.printStackTrace();  //에러 내용 출력
			
		} finally {  // finally : 에러 유무와 상관없이 무조건 실행할 내용 입력 -> 여기선 에러와 상관없이 커넥션 닫기
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt != null){  
					pstmt.close();
				}
				
				if(conn != null) {  
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		
		return bDtos;  // 글(bDto) 여러개가 담긴 list인 bDtos 반환
		}
	
	public BoardDto getBoardDetail(String num) { // 글하나 불러오는 메서드
	    String sql = "SELECT * FROM board WHERE bnum=?";
	    BoardDto dto = null;
	    try {
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, userName, password);
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, num);
	        rs = pstmt.executeQuery();

	        if(rs.next()) {
	            dto = new BoardDto();
	            
	            int bnum = rs.getInt("bnum");
				String btitle = rs.getString("btitle");
				String bcontent = rs.getString("bcontent");
				String memberid = rs.getString("memberid");
				int bhit = rs.getInt("bhit");
				String bdate = rs.getString("bdate");				
				String memberemail = rs.getString("memberemail"); 
	            
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if(rs!=null) rs.close();
	            if(pstmt!=null) pstmt.close();
	            if(conn!=null) conn.close();
	        } catch(Exception e) { e.printStackTrace(); }
	    }
	    return dto;
	}
	
	
	
	// 글 등록
	public void insertBoard(String btitle, String memberid, String bcontent ) {  //user가 쓰는건 아니어서 bhit는 안가져와도 된다
		
	    String sql = "INSERT INTO board(btitle, bcontent, memberid, bhit) VALUES (?, ?, ?, 0)";
	    // 새글 등록이므로 조회수는 0부터 시작 -> bhit:0 
	    
	    try {
	    	
	        Class.forName(driverName);
	        conn = DriverManager.getConnection(url, userName, password);
	        
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, btitle);
	        pstmt.setString(2, bcontent);
	        pstmt.setString(3, memberid);
	        
	        pstmt.executeUpdate();  // 딱히 확인할 필요없어서 result= 나 int result 이런거 필요없다
	        
	    } catch (Exception e) {
	    	System.out.println("글 등록 실패");
	        e.printStackTrace();
	        
	    } finally {
	        try {
	            if(pstmt != null) {
	            	pstmt.close();
	            }
	            if(conn != null) {
	            	conn.close();
	            }
	        } catch(Exception e) {
	        	e.printStackTrace(); 
	        	}
	    
	    }
	}

//	// 글 수정
//	public boolean updateBoard(String btitle, String memberid, String bcontent) {
//	    String sql = "UPDATE board SET btitle=?, bcontent=?, memberid=? WHERE bnum=?";
//	    try {
//	        Class.forName(driverName);
//	        conn = DriverManager.getConnection(url, userName, password);
//	        pstmt = conn.prepareStatement(sql);
//	      //  pstmt.setString(1, dto.getBtitle());
//	      //  pstmt.setString(2, dto.getBcontent());
//	      //  pstmt.setString(3, dto.getMemberid());
//	      //  pstmt.setInt(4, dto.getBnum());
//	        int count = pstmt.executeUpdate();
//	        return count > 0;
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        return false;
//	    } finally {
//	        try {
//	            if(pstmt != null) pstmt.close();
//	            if(conn != null) conn.close();
//	        } catch(Exception e) { e.printStackTrace(); }
//	    }
//	}
//	
	public BoardDto contentView(String boardnum) { //게시판 글 목록에서 유저가 클릭한 글 번호의 글 dto 반환 메서드
		
		 	String sql = "SELECT * FROM board WHERE bnum = ?";
			// BoardDto boardDto = new BoardDto();
		 	BoardDto boardDto = null;

		    try {
		    	
		        Class.forName(driverName);
		        conn = DriverManager.getConnection(url, userName, password);
		        
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, boardnum);
		        rs = pstmt.executeQuery();

		        while (rs.next()) {
		        	

		        	int bnum = rs.getInt("bnum");
					String btitle = rs.getString("btitle");
					String bcontent = rs.getString("bcontent");
					String memberid = rs.getString("memberid");
					String memberemail = rs.getString("memberemail");
					int bhit = rs.getInt("bhit");
					String bdate = rs.getString("bdate");
					
					MemberDto memberDto = new MemberDto();
					memberDto.setMemberid(memberid);
					memberDto.setMemberemail(memberemail);
		        	
		        	boardDto = new BoardDto(bnum, btitle, bcontent, memberid, bhit, bdate, memberDto);
		        }

		    } catch(Exception e) {
		    	System.out.println("게시판 글 가져오기 실패");
		        e.printStackTrace();
		    } finally {
		        try { if(rs!=null) 
		        	rs.close(); 
		        } catch(Exception e) {}
		        try { if(pstmt!=null) 
		        	pstmt.close(); 
		        } catch(Exception e) {}
		        try { if(conn!=null) 
		        	conn.close(); 
		        } catch(Exception e) {}
		    }

		    return boardDto;
		}
		
	
			public void boardUpdate(String bnum, String btitle, String bcontent) {
				
				String sql = "UPDATE board SET btitle=?, bcontent=? WHERE bnum=?";
				int result = 0;
		
			    try {
			        Class.forName(driverName);
			        conn = DriverManager.getConnection(url, userName, password);
			        
			        pstmt = conn.prepareStatement(sql);
			        pstmt.setString(1,btitle);
			        pstmt.setString(2, bcontent);
			        pstmt.setString(3, bnum);
			        
			       pstmt.executeUpdate();
			    
			   
			    
			    } catch (Exception e) {
			    	System.out.println("글 수정 실패");
			        e.printStackTrace();
			        
			    } finally {
			        try {
			            if(pstmt != null) {
			            	pstmt.close();
			            }
			            if(conn != null) {
			            	conn.close();
			            }
			        } catch(Exception e) {
			        	e.printStackTrace(); 
			        	}
			    }		    	    
			}
			
	
			public void boardDelete(String bnum) {
				
				String sql = "DELETE FROM board WHERE bnum = ? ";
				
				
				 try {
				        Class.forName(driverName);
				        conn = DriverManager.getConnection(url, userName, password);				
				        
				        pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1,bnum);
				        pstmt.executeUpdate();
				        
				        
				 } catch (Exception e) {
				    	System.out.println("글 삭제 실패");
				        e.printStackTrace();
				        
				    } finally {
				        try {
				            if(pstmt != null) {
				            	pstmt.close();
				            }
				            if(conn != null) {
				            	conn.close();
				            }
				        } catch(Exception e) {
				        	e.printStackTrace(); 
				        	}
				    }		    	    
				
				
			}
			
			
			public void updateBhit(String bnum) {
			
				String sql = "UPDATE board SET bhit=bhit+1 WHERE bnum=? ";   // 조회수가 1씩 늘어나는 sql문
				
				 try {
				        Class.forName(driverName);
				        conn = DriverManager.getConnection(url, userName, password);
				        
				        pstmt = conn.prepareStatement(sql);
				        pstmt.setString(1,bnum);
				       
				        pstmt.executeUpdate();
				    
				   
				    
				    } catch (Exception e) {
				    	System.out.println("조회수 증가 실패");
				        e.printStackTrace();
				        
				    } finally {
				        try {
				            if(pstmt != null) {
				            	pstmt.close();
				            }
				            if(conn != null) {
				            	conn.close();
				            }
				        } catch(Exception e) {
				        	e.printStackTrace(); 
				        	}
				    }		    	    
			
				}
				
				
				
			}
		
	



