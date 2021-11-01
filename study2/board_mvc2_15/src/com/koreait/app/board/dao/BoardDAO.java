package com.koreait.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sqlfactory = SqlMapConfig.getInstance();
	SqlSession sqlSession;
	
	public BoardDAO() {
		sqlSession = sqlfactory.openSession(true);
	}
	public int getBoardCnt() {
		return sqlSession.selectOne("Board.boardCnt");
	}
	public List<BoardBean> getBoardList(int startRow, int endRow) {
		HashMap<String, Integer> datas = new HashMap<>();
		datas.put("startRow", startRow);
		datas.put("endRow", endRow);
		List<BoardBean> boardList = sqlSession.selectList("Board.listAll",datas);
		//selectList 메서드를 쓰면 List형식으로 boardList에 담아준다.
		return boardList;
	}
}














