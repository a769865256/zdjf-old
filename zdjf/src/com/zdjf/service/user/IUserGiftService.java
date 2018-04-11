package com.zdjf.service.user;

import com.zdjf.domain.user.UserGift;
import com.zdjf.domain.user.UserGiftVo;
import com.zdjf.frame.dataaccess_api.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IUserGiftService {
	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(UserGift userGift);
	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(UserGift userGift);
	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(UserGift userGift);
	
	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserGiftVo load(UserGift userGift);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<UserGiftVo> selectForList(UserGift userGift);
	
	public abstract List getUserGiftList(Map map);
	
	public abstract int selectGiftCountByUid(Long user_id);
	
	public abstract Double  getgiftUsedNum(Long user_id);
	
	public abstract UserGift selectForObjectByGiftId(UserGift userGift);
	
	public abstract List<Map<String, Object>> selectForList(Map<String, Object> map);
	
	public abstract void updateUserGiftByGiftId(UserGift userGift);
	public abstract void deleteByGiftId(long id);

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, UserGift userGift);
	public abstract Page page(Page page, Map<String, Object> map);

	public abstract Map<Object, Object> selectForMapMonth(UserGift userGift);

	public abstract Map<Object, Object> selectForMapWeek(UserGift userGift);

	public abstract Map<Object, Object> selectForMapDay(UserGift userGift);

	public abstract Map<Object, Object> selectForMapYear(UserGift userGift);

	void addUserGift(List<Long> userIds, String giftName);

    /**
     * 红包过期实现
     */
	void updateOutOfDateUserGift();
}
