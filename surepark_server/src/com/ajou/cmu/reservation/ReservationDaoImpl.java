package com.ajou.cmu.reservation;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.cmu.common.BaseDao;
import com.ajou.cmu.common.Dao;

 /* test22 */
@Repository("revDao")
public class ReservationDaoImpl extends BaseDao implements Dao {

	@Override
	public Object select(Object obj) throws SQLException {
		return null;
//		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectParkingReservation");
	}
	
//	//대한 - 2016.07.17 21:35 - ServiceImpl의 getIdentifierObject에 매핑되는 Dao 구현
//	public Object selectIdentifier(Object obj) throws SQLException {
//		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectIdentifiers", obj);
//	}

	//대한 - 2016.07.17 10:53 - ServiceImpl의 countIdentifierObject에 매핑되는 Dao 구현
	public Object countnumIdentifierObject(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectIdentifiers", obj);
	}
	
	//대한 - 2016.07.18 13:26 - 현재 주차상태를 사용자한테 보내는 쿼리를 위한 Service 작성
	public Object getCurrentStatusObject(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("selectcurrentstatus", obj);
	}
	
	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		return 0;
	}

	@Override
	public void insert(Object obj) throws SQLException {
		//쿼리 Ibatis로 매핑
		getSqlMapClientTemplate().insert("com.ajou.cmu.reservation.insertPakingReservation", obj);
		
	}

	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.updatePakingReservation", obj);
	}

	public Object selectAvailable() throws SQLException{
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectParkingReservation");
	}
}
