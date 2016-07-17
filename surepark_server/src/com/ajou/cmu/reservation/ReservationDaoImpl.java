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
		return getSqlMapClientTemplate().queryForObject("com.neopad.infobee_property.user.selectUserByUserEmail", obj);
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
		//Äõ¸® Ibatis·Î ¸ÅÇÎ
		
	}

	@Override
	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
