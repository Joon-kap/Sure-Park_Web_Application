package com.ajou.cmu.common;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
/*branch test*/
public class BaseDao extends SqlMapClientDaoSupport{
	@Resource(name="sqlMapClient")
	public void setDao(SqlMapClient sqlMapClient){
		this.setSqlMapClient(sqlMapClient);
	}
}
