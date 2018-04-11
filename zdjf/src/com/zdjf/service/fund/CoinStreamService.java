package com.zdjf.service.fund;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zdjf.dao.fund.ICoinStreamDao;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.CoinStreamVo;
import com.zdjf.frame.dataaccess_api.Page;
@Service
public class CoinStreamService implements ICoinStreamService {
	private ICoinStreamDao coinStreamDao;
	

	@Override
	public Serializable save(CoinStream coinStream) {
		return coinStreamDao.save(coinStream);
	}

	@Override
	public void delete(CoinStream coinStream) {
		coinStreamDao.delete(coinStream);
	}

	@Override
	public void update(CoinStream coinStream) {
		coinStreamDao.update(coinStream);
	}

	@Override
	public CoinStreamVo load(CoinStream coinStream) {
		return (CoinStreamVo) coinStreamDao.reload(coinStream);
	}

	@SuppressWarnings("unchecked")
	public List<CoinStreamVo> selectForList(CoinStream coinStream) {
		return (List<CoinStreamVo>) coinStreamDao.selectForList("selectCoinStream", coinStream);
	}

	@Override
	public CoinStream selectForObjectById(long id) {
		CoinStream coinStream=new CoinStream();
		coinStream.setId(id);
		return (CoinStream) coinStreamDao.selectForObject("selectCoinStreamById", coinStream);
	}

	@Override
	public void updateCoinStreamById(CoinStream coinStream) {
		coinStreamDao.update("updateCoinStreamById", coinStream);
	}

	@Override
	public void deleteById(long id) {
		coinStreamDao.delete("deleteCoinStreamById",id);
	}

	@Override
	public Page page(Page page, CoinStream coinStream) {
		return coinStreamDao.page(page, coinStream);
	}
	@Override
	public Page page(Page page, Map<String, Object> map) {
		
		return coinStreamDao.page(page, map);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(CoinStream coinStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(CoinStream coinStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(CoinStream coinStream) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(CoinStream coinStream) {
		// TODO Auto-generated method stub
		return null;
	}
	@Autowired
	public void setCoinStreamDao(
			@Qualifier("coinStreamDao")ICoinStreamDao coinStreamDao) {
		this.coinStreamDao = coinStreamDao;
	}

	@Override
	public void updateCoinStream(CoinStream coinStream) {
		// TODO Auto-generated method stub
		coinStreamDao.update("updateCoinStream", coinStream);
	}

	@Override
	public List<Map<String, Object>> selectUserCoinForList(CoinStream coinStream) {
		StringBuffer sql = new StringBuffer("SELECT");
		sql.append(" DATE_FORMAT(s.create_time,\"%Y-%m-%d %H:%i:%s\") create_time,");
		sql.append(" f.data_field_name type,");
		sql.append(" s.amount,");
		sql.append(" s.action,");
		sql.append(" s.balance");
		sql.append(" FROM zd_coin_stream s,zd_data_field f");
		sql.append(" WHERE s.type = f.data_field_value AND f.data_field_id = 'coin_type'");
		if (coinStream != null && coinStream.getUser_id() != null) {
			sql.append(" AND s.user_id ="+coinStream.getUser_id());
		}
		if (coinStream != null && coinStream.getStatus() != 0) {
			sql.append(" AND s.status ="+coinStream.getStatus());
		}
		sql.append(" ORDER BY s.create_time DESC");
		return coinStreamDao.selectForMap(sql.toString());
	}

	@Override
	public List<Map<String, Object>> selectUserCoinForListByType(Long userId, Integer type) {
		StringBuffer sql = new StringBuffer("SELECT *");
		sql.append(" FROM zd_coin_stream");
		sql.append(" WHERE status = 1");
		if (userId != null) {
			sql.append(" AND user_id = "+ userId);
		}
		if (type != null) {
			sql.append(" AND type = "+ type);
		}
		return coinStreamDao.selectForMap(sql.toString());
	}

	@Override
	public List<Map<String, Object>> selectUserCoinForListByInvest(Long userId) {
		StringBuffer sql = new StringBuffer("SELECT 1");
		sql.append(" FROM zd_coin_stream a");
		sql.append(" INNER JOIN zd_funds b");
		sql.append(" ON a.user_id = b.user_id and a.remark = b.trade_no");
		sql.append(" WHERE a.status = 1");
		sql.append(" AND a.type = 11");
		sql.append(" AND a.amount = 20");
		sql.append(" AND b.status = 1");
		sql.append(" AND b.amount >= 1000");
		if (userId != null) {
			sql.append(" AND a.user_id = "+ userId);
		}
		return coinStreamDao.selectForMap(sql.toString());
	}
}
