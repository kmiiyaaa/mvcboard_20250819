package com.kmii.dto;

import java.security.Timestamp;
import java.sql.Date;
import java.sql.Time;

public class RDto {
	
	
	private int rnum; // 예약번호 - 기본키 AI자동추가
	private String memberid;  // 예약한 사람 아이디(로그인 된 아이디 ) - 회원정보 테이블과 연동할 외래키
	private String rname;
	private String rphone;
	private Date rdate; // 예약날짜
	private Time rtime; // 예약시간
	private Timestamp createdate ; // 예약요청한 날짜,시간
	
	
	
	public RDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public RDto(int rnum, String memberid, String rname, String rphone, Date rdate, Time rtime, Timestamp createdate) {
		super();
		this.rnum = rnum;
		this.memberid = memberid;
		this.rname = rname;
		this.rphone = rphone;
		this.rdate = rdate;
		this.rtime = rtime;
		this.createdate = createdate;
	}



	public int getRnum() {
		return rnum;
	}



	public void setRnum(int rnum) {
		this.rnum = rnum;
	}



	public String getMemberid() {
		return memberid;
	}



	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}



	public String getRname() {
		return rname;
	}



	public void setRname(String rname) {
		this.rname = rname;
	}



	public String getRphone() {
		return rphone;
	}



	public void setRphone(String rphone) {
		this.rphone = rphone;
	}



	public Date getRdate() {
		return rdate;
	}



	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}



	public Time getRtime() {
		return rtime;
	}



	public void setRtime(Time rtime) {
		this.rtime = rtime;
	}



	public Timestamp getCreatedate() {
		return createdate;
	}



	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}

	
	

}
