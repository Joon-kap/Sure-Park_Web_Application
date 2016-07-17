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
}
