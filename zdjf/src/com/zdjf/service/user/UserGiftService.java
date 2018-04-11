package com.zdjf.service.user;

import com.zdjf.dao.user.IUserGiftDao;
import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.frame.dataaccess_api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserGiftService implements IUserGiftService {
	
	private IUserGiftDao userGiftDao;

	@Override
	public Serializable save(UserGift userGift) {
		return userGiftDao.save(userGift);
	}

	@Override
	public void delete(UserGift userGift) {
		userGiftDao.delete(userGift);		
	}

	@Override
	public void update(UserGift userGift) {
		userGiftDao.update(userGift);		
	}

	@Override
	public UserGift selectForObjectByGiftId(UserGift userGift) {
		return (UserGift) userGiftDao.selectForObject("selectUserGiftByGiftId", userGift);
	}

	@Override
	public void updateUserGiftByGiftId(UserGift userGift) {
		userGiftDao.update("updateUserGiftByGiftId", userGift);
	}

	@Override
	public void deleteByGiftId(long id) {
		userGiftDao.delete("deleteUserGiftByGiftId", id);		
	}

	@Override
	public   Page page(Page page, UserGift userGift) {
		return userGiftDao.page(page, userGift);
	}

	@Override
	public Map<Object, Object> selectForMapMonth(UserGift userGift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(UserGift userGift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(UserGift userGift) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(UserGift userGift) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	public void setIUserGiftDao(
			@Qualifier("userGiftDao") IUserGiftDao  userGiftDao) {
		this.userGiftDao = userGiftDao;
	}

	@Override
	public UserGiftVo load(UserGift userGift) {
		return (UserGiftVo)userGiftDao.reload(userGift);
	}

	@SuppressWarnings("unchecked")
	public List<UserGiftVo> selectForList(UserGift userGift) {
		return (List<UserGiftVo>)userGiftDao.selectForList("selectUserGift",userGift);
	}
	
	@SuppressWarnings("unchecked")
	public List getUserGiftList(Map map){
		return userGiftDao.selectForList("selectUserGiftList",map);
	}
	@SuppressWarnings("unchecked")
	public int selectGiftCountByUid(Long user_id){
		return (int)userGiftDao.selectForObject("selectGiftCountByUid",user_id);
	}
	
	@SuppressWarnings("unchecked")
	public Double  getgiftUsedNum(Long user_id){
		return (Double)userGiftDao.selectForObject("getgiftUsedNum",user_id);
	}

	@Override
	public Page page(Page page, Map<String, Object> map) {
		return userGiftDao.page(page, map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> selectForList(Map<String, Object> map) {
		String page_name=map.get("page_name").toString();
		return (List<Map<String, Object>>) userGiftDao.selectForList(page_name,map);
	}

	@Override
	public void addUserGift(List<Long> userIds, String giftName) {
		Map<String,Object> hashMap = new HashMap<>();
		hashMap.put("list",userIds);
		hashMap.put("giftName",giftName);
		userGiftDao.save("batchInsertUserGift",hashMap);
	}

    @Override
    public void updateOutOfDateUserGift() {
        userGiftDao.update("updateOutOfDateUserGift");
    }
}
