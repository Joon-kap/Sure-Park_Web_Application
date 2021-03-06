package com.ajou.cmu.owner;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ajou.cmu.common.RequestParameter;
import com.ajou.secure.user.UserDaoImpl;

//git test by daehan

@Service("ownerService")
public class OwnerServiceImpl implements com.ajou.cmu.common.Service {
	@Resource(name = "ownerDao")
	OwnerDaoImpl dao;
	

	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}
	
//	//대한 - 2016.07.17 21:32 - identifier 가져오는 쿼리로의 매핑을 위한 Service 로직 추가 구현
//	public Object getIdentifierObject(Object obj) throws SQLException {
//		return this.dao.selectIdentifier(obj);
//	}
	
	
	
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

		return 	dao.getList(obj);
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
	
	public void updateSpot(Object obj) throws SQLException{
		dao.updateParkingSpot(obj);
	}

	public void setPayNexitTime(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		dao.updatePayment(obj);
	}

	public Object getAvailableSpot() {
		
		return dao.selectAvailableSpot();
	}

	public void update(RequestParameter rp) throws SQLException {
		// TODO Auto-generated method stub
		dao.update(rp);
	}
	
	public void updatefacil(RequestParameter rp) throws SQLException {
		// TODO Auto-generated method stub
		dao.updatefacil(rp);
	}
	public void updateConf(RequestParameter rp) throws SQLException {
		// TODO Auto-generated method stub
		dao.updateConf(rp);
	}

	public void setgp(RequestParameter rp) {
		// TODO Auto-generated method stub
		dao.updateGP(rp);
	}

	public void setFee(RequestParameter rp) {
		// TODO Auto-generated method stub
		dao.updateFee(rp);
	}

	public Object getConfObject() {
		// TODO Auto-generated method stub
		return dao.getConf();
	}
}
