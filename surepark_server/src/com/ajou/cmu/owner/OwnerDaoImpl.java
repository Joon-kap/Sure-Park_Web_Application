package com.ajou.cmu.owner;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.cmu.common.BaseDao;
import com.ajou.cmu.common.Dao;

 /* test22 */
@Repository("ownerDao")
public class OwnerDaoImpl extends BaseDao implements Dao {

	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.owner.selectAll", obj);
	}
	
	@Override
	public void delete(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getRowCount(Object obj) throws SQLException {
		return 0;
	}

	public void update(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.cmu.owner.updateInfo", obj);
	}
	public void updateConf(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.cmu.owner.updateConf", obj);
	}
	public void updatefacil(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.cmu.owner.updatefacil", obj);
	}

}
