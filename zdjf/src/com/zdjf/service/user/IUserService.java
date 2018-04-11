package com.zdjf.service.user;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.zdjf.domain.activity.AwardRec;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import com.zdjf.service.fund.IGiftService;

public interface IUserService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(User user);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(User user);

	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(User user);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(User user);

	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(User user);

	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(User user);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract UserVo load(User user);
	
	
	
	public abstract User login(User user);

	/**
	 * 按对象中的非空属性作为条件，进行查询
	 */
	public abstract List<UserVo> selectForList(User user);
	
	public abstract List<UserVo> selectForList(List<Long> idList);
	
	public List<UserVo> selectForList(Map<String, Object> map);
	
	public List<User> getUserList();

	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, User user);
	
	public abstract Page page(Page page, Map<String, Object> map);

	public abstract Map<Object, Object> selectForMapMonth(User user);

	public abstract Map<Object, Object> selectForMapWeek(User user);

	public abstract Map<Object, Object> selectForMapDay(User user);

	public abstract Map<Object, Object> selectForMapYear(User user);
	
	/**
	 * 根据手机号码查找用户信息
	 */
	public abstract User selectForObjectByPhone(String phone);
	
	public abstract User selectForObject(User user);
	
	public abstract void updateForObjectByPhone(User user);

	/**
	 * 清除通过分享获得的签到机会
	 */
	void cleanSignTimesByShare();

	/**
	 * 清除所有签到次数
	 */
	void cleanSignTimes();

	/**
	 * 累计注册用户统计
	 * @return
	 */
	long userCount();

	/**
	 * 累计为用户赚取收益
	 * @return
	 */
	Double staUserTotalIncomed();

	/**
	 * 查询用户当日已抽奖次数
	 * @param userId
	 * @return
	 */
	int selectUserAwardRecByCurrDay(Long userId);

	/**
	 * 保存用户中奖记录
	 * @param rec
	 */
	void saveUserAwardRec(AwardRec rec);
}