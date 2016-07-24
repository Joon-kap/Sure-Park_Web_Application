package com.ajou.cmu.attandent;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ajou.secure.user.UserDaoImpl;

//git test by daehan

@Service("attService")
public class AttendantServiceImpl implements com.ajou.cmu.common.Service {
	@Resource(name = "attDao")
	AttendantDaoImpl dao;
	

	@Override
	public Object getObject(Object obj) throws SQLException {
		return this.dao.select(obj);
	}
	
//	//���� - 2016.07.17 21:32 - identifier �������� �������� ������ ���� Service ���� �߰� ����
//	public Object getIdentifierObject(Object obj) throws SQLException {
//		return this.dao.selectIdentifier(obj);
//	}
	
	//���� - 2016.07.17 22:50 - identifier �� DB�� �˻��ؼ� ��ȸ�� 0�̸� ������ �ȵ� ��, 1�̸� �ùٸ��Ƿ� Gate Open, 2�̻��̸� ������ �����Ƿ� Dava ȣ���� �Ѵ�.
	public Object countIdentifierObject(Object obj) throws SQLException {
		// TODO Auto-generated method stub
		return this.dao.countnumIdentifierObject(obj);
	}
	
	//���� - 2016.07.18 13:26 - ���� �������¸� ��������� ������ ������ ���� Service �ۼ�
	public Object getCurrentStatusObject(Object obj) throws SQLException {
		return this.dao.getCurrentStatusObject(obj);
	}
	
	//���� - 2016.07.18 23:54 - 
	public Object getReservationTimeObject(Object obj) throws SQLException {
		return this.dao.getReservationTimeObject(obj);
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
}