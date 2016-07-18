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
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectParkingReservation", obj);
	}
	
	//대한 - 2016.07.17 21:35 - ServiceImpl의 getIdentifierObject에 매핑되는 Dao 구현
	public Object selectIdentifier(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.selectIdentifiers", obj);
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
		getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.insertPakingReservation", obj);
		
	}

	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().queryForObject("com.ajou.cmu.reservation.updatePakingReservation", obj);
	}

}
