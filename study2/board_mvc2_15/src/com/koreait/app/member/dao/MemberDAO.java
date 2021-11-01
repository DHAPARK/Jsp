package com.koreait.app.member.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.mybatis.config.SqlMapConfig;

public class MemberDAO {
	SqlSessionFactory sqlfactory= SqlMapConfig.getInstance();
	SqlSession sqlsession = null;
	
	public MemberDAO() {
		sqlsession=sqlfactory.openSession(true);
	}
	public boolean join(MemberBean member) {
		return 1 == sqlsession.insert("Member.join",member);
	}
	public MemberBean login(String userid, String userpw) {
		HashMap<String, String> datas = new HashMap<>();
		datas.put("userid",userid);
		datas.put("userpw", userpw);
		MemberBean member = (MemberBean)sqlsession.selectOne("Member.login", datas);
		return member;
	}
	public boolean checkId(String userid) {
		return 0==(Integer)sqlsession.selectOne("Member.checkId",userid);
	}
}









