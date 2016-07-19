package com.ajou.cmu.reservation;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ajou.secure.user.UserDaoImpl;

//git test by daehan

@Service("revService")
public class ReservationServiceImpl implements com.ajou.cmu.common.Service {
	@Resource(name = "revDao")
	ReservationDaoImpl dao;
	

	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}
	
//	//대한 - 2016.07.17 21:32 - identifier 가져오는 쿼리로의 매핑을 위한 Service 로직 추가 구현
//	public Object getIdentifierObject(Object obj) throws SQLException {
//		return this.dao.selectIdentifier(obj);
//	}
	
	//대한 - 2016.07.17 22:50 - identifier 를 DB에 검색해서 조회가 0이면 예약이 안된 것, 1이면 올바르므로 Gate Open, 2이상이면 문제가 있으므로 Dava 호출을 한다.
	public Object countIdentifierObject(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return this.dao.countnumIdentifierObject(obj);
	}
	
	//대한 - 2016.07.18 13:26 - 현재 주차상태를 사용자한테 보내는 쿼리를 위한 Service 작성
	public Object getCurrentStatusObject(Object obj) throws SQLException {
		return this.dao.getCurrentStatusObject(obj);
	}

	@Override
	public boolean edit(Object obj) throws SQLException {
		return true;
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remove(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean save(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.insert(obj);
		return false;
	}

	@Override
	public boolean delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Object getAvailableStatus() throws SQLException {
		return dao.selectAvailable();
	}
}
