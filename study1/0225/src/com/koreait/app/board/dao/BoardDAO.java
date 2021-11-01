package com.koreait.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sqlfactory = SqlMapConfig.getInstance();
	SqlSession sqlsession;
	
	public BoardDAO() {
		sqlsession = sqlfactory.openSession(true);
											////
	}
	
	public int getBoardSeq() {
		return (Integer)sqlsession.selectOne("Board.boardSeq");
	}
	
	public int getBoardCnt() {
		return (int)sqlsession.selectOne("Board.boardCnt");
	}

	public List<BoardBean> getBoardList(int startRow, int endRow) {
		HashMap<String,Integer> datas = new HashMap<>();
		datas.put("startRow",startRow);
		datas.put("endRow", endRow);
		List<BoardBean> boardList = sqlsession.selectList("Board.listAll", datas);
		return boardList;
	}

	public boolean insertBoard(BoardBean board) {
		return 1==sqlsession.insert("Board.insertBoard",board);
	}

	public BoardBean getDetail(int boardnum) {
		return sqlsession.selectOne("Board.getDetail",boardnum);
	}

	public void updateReadCount(int boardnum) {
		sqlsession.update("Board.updateReadCount",boardnum);
	}

	public boolean modifyBoard(BoardBean board) {
		return 1==sqlsession.update("Board.modifyBoard",board);
	}

	public void delete(int boardnum) {
		sqlsession.delete("Board.delete",boardnum);
	}
}
