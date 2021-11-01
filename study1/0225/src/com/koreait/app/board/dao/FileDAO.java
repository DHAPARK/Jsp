package com.koreait.app.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class FileDAO {
	SqlSessionFactory sqlfactory = SqlMapConfig.getInstance();
	SqlSession sqlsession;
	public FileDAO() {
		sqlsession = sqlfactory.openSession(true);
	}
	
	public boolean insertFiles(FileBean file) {
		return sqlsession.insert("File.insertFiles",file) == 1;
	}

	public List<FileBean> getDetail(int boardnum) {
		List<FileBean> files = sqlsession.selectList("File.getDetail", boardnum);
		return files;
	}

	public void deleteFiles(int boardnum) {
		sqlsession.delete("File.deleteFiles",boardnum);
	}

	public void deleteFileByName(String filename) {
		sqlsession.delete("File.deleteByName",filename);
	}
	
}
