package com.ajou.cmu.owner;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ajou.cmu.common.BaseDao;
import com.ajou.cmu.common.Dao;
import com.ajou.cmu.common.RequestParameter;

 /* test22 */
@Repository("ownerDao")
public class OwnerDaoImpl extends BaseDao implements Dao {

	@Override
	public Object select(Object obj) throws SQLException {
		return getSqlMapClientTemplate().queryForObject("com.ajou.cmu.owner.login", obj);
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

	@Override
	public void insert(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List getList(Object obj) throws SQLException {
		
		return getSqlMapClientTemplate().queryForList("com.ajou.cmu.owner.selectListByDate", obj);
	}

	public Object getCurrentStatusObject(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object selectAvailable() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateParkingSpot(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public void updatePayment(Object obj) {
		// TODO Auto-generated method stub
		
	}

	public Object selectAvailableSpot() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateGP(RequestParameter rp) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.cmu.owner.updategp", rp);
	}

	public void updateFee(RequestParameter rp) {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().update("com.ajou.cmu.owner.updatefee", rp);
	}

	public Object getConf() {
		// TODO Auto-generated method stub
		return  getSqlMapClientTemplate().queryForObject("com.ajou.cmu.owner.getconf");
	}
}
