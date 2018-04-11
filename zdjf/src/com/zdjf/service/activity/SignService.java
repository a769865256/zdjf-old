package com.zdjf.service.activity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.activity.ISignDao;
import com.zdjf.domain.activity.Sign;
import com.zdjf.frame.dataaccess_api.Page;

@Service
public class SignService implements ISignService{

	
	private ISignDao signDao;
	@Override
	public Serializable save(Sign sign) {
		return signDao.save(sign);
	}

	@Override
	public void delete(Sign sign) {
		signDao.delete(sign);
	}

	@Override
	public void update(Sign sign) {
		signDao.update(sign);
	}

	@Override
	public Sign selectForObjectById(Long id) {
		Sign sign=new Sign();
		sign.setId(id);
		return (Sign) signDao.selectForObject("selectSignById", sign);
	}

	@Override
	public void updateSignById(Sign sign) {
		signDao.update("updateSignById", sign);
	}

	@Override
	public void deleteById(Long id) {
		signDao.delete("deleteSignById", id);
	}

	@Override
	public Page page(Page page, Sign sign) {
		return signDao.page(page, sign);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(Sign sign) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(Sign sign) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(Sign sign) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(Sign sign) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Sign> selectForList(Sign sign) {
		return (List<Sign>) signDao.selectForList("selectSign", sign);
	}

	@Override
	public List<Map<String, Object>> selectForLatestSignMap() {
		String sql = "select  insert(phone, 4, 4, '****') user,cast(coin as signed) coin,create_date from zd_sign  where create_date = DATE_FORMAT(NOW(),\"%Y-%m-%d\") ORDER BY create_time desc LIMIT 0,10";
		return signDao.selectForMap(sql);
	}

	@Override
	public List<Map<String, Object>> selectForSignUserByCoins() {
		String sql = "SELECT insert(phone, 4, 4, '****') user,phone,cast(SUM(coin) as signed) coins from zd_sign where DATE_FORMAT(create_time,\"%Y-%m\") = DATE_FORMAT(NOW(),\"%Y-%m\") GROUP BY phone ORDER BY coins desc LIMIT 0,8";
		return signDao.selectForMap(sql);
	}

	@Autowired
	public void setAdvertiseDao(
			@Qualifier("signDao")ISignDao signDao) {
		this.signDao = signDao;
	}

	@Override
	public List<Map<String, Object>> selectForUserSignInfEveryDay(Long userId) {
		StringBuffer sql = new StringBuffer("SELECT");
		sql.append(" SUM(coin) coins,");
		sql.append(" create_date,");
		sql.append(" SUBSTR(create_date, 9,10) create_day");
		sql.append(" FROM");
		sql.append(" zd_sign");
		sql.append(" WHERE status=1");
		if (userId != null) {
			sql.append(" AND user_id=" + userId);
		}
		sql.append(" GROUP BY create_date");
		return signDao.selectForMap(sql.toString());
	}
}
