package com.koreait.app.board.dao;

public class BoardBean {
	private int boardnum;
	private String boardtitle;
	private String boardcontents;
	private String userid;
	private String boarddate;
	private int boardreadcount;
	
	public int getBoardnum() {
		return boardnum;
	}
	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}
	public String getBoardtitle() {
		return boardtitle;
	}
	public void setBoardtitle(String boardtitle) {
		this.boardtitle = boardtitle;
	}
	public String getBoardcontents() {
		return boardcontents;
	}
	public void setBoardcontents(String boardcontents) {
		this.boardcontents = boardcontents;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getBoarddate() {
		return boarddate;
	}
	public void setBoarddate(String boarddate) {
		this.boarddate = boarddate;
	}
	public int getBoardreadcount() {
		return boardreadcount;
	}
	public void setBoardreadcount(int boardreadcount) {
		this.boardreadcount = boardreadcount;
	}
}   
