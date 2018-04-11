package com.zdjf.service.user;

import com.zdjf.dao.fund.ICoinStreamDao;
import com.zdjf.dao.fund.IUserFundStatDao;
import com.zdjf.dao.user.IUserDao;
import com.zdjf.domain.activity.AwardRec;
import com.zdjf.domain.fund.CoinStream;
import com.zdjf.domain.fund.UserFundStat;
import com.zdjf.domain.user.User;
import com.zdjf.domain.user.UserVo;
import com.zdjf.frame.dataaccess_api.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {
	//private static Log logger = LogFactory.getLog(UserService.class);
	private IUserDao userDao;

	@Autowired
	private IUserFundStatDao userFundStatDao;

	@Autowired
	private ICoinStreamDao coinStreamDao;

	public Serializable save(User user){
		return userDao.save(user);
	}

	public void delete(User user){
		userDao.delete(user);
	}
	
	
	
	public void deleteByExample(User user){
		userDao.deleteByExample(user);
	}

	public void update(User user){
		userDao.update(user);
	}
	
	public void updateIgnoreNull(User user){
		userDao.updateIgnoreNull(user);
	}
		
	public void updateByExample(User user){
		userDao.update("updateByExampleUser", user);
	}

	public UserVo load(User user){
		return (UserVo)userDao.reload(user);
	}
	
	public User login(User user){
		return (User)userDao.selectForObject("login",user);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserVo> selectForList(User user){
		return (List<UserVo>)userDao.selectForList("selectUser",user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getUserList(){
		return (List<User>) userDao.selectForList("selectUserTransferList");
	}
	
	@SuppressWarnings("unchecked")
	public List<UserVo> selectForList(Map<String, Object> map){
		String page_name=(String) map.get("page_name");
		return (List<UserVo>)userDao.selectForList(page_name,map);
	}
	
	public Page page(Page page, User user) {
		return userDao.page(page, user);
	}
	@Override
	public Page page(Page page, Map<String, Object> map) {
		return userDao.page(page, map);
	}
	
	@Override
	public Map<Object, Object> selectForMapMonth(User user) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapWeek(User user) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapDay(User user) {
		return null;
	}

	@Override
	public Map<Object, Object> selectForMapYear(User user) {
		return null;
	}
	
	@Override
	public User selectForObjectByPhone(String phone) {
		User user=new User();
		user.setUser_name(phone);
		return (User) userDao.selectForObject("selectUserByPhone",user);
	}
	@Override
	public void updateForObjectByPhone(User user) {
		userDao.update("updateForObjectByPhone", user);
	}
	
	@Autowired
	public void setIUserDao(
			@Qualifier("userDao") IUserDao  userDao) {
		this.userDao = userDao;
	}

	@Override
	public User selectForObject(User user) {
		return (User) userDao.selectForObject("selectForUser", user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserVo> selectForList(List<Long> idList) {
		return (List<UserVo>)userDao.selectForList("selectUserByIdList",idList);
	}

	@Override
	public void cleanSignTimesByShare() {
		userDao.update("cleanSignTimesByShare");
	}

	@Override
	public void cleanSignTimes() {
		userDao.update("cleanSignTimes");
	}

	@Override
	public long userCount() {
		List<Long> users = (List<Long>)userDao.selectForList("userCount");
		return users != null && users.size() > 0 && users.get(0)!= null ? users.get(0) : 0L;
	}

	@Override
	public Double staUserTotalIncomed() {
		List<Double> usersTotalIncomed = (List<Double>)userDao.selectForList("staUserTotalIncomed");
		return usersTotalIncomed != null && usersTotalIncomed.size()>0 && usersTotalIncomed.get(0) != null ? usersTotalIncomed.get(0) : 0.0;
	}

	@Override
	public int selectUserAwardRecByCurrDay(Long userId) {
		Object obj = userDao.selectForObject("selectUserAwardRecByCurrDay",userId);
		return Integer.parseInt(obj == null ? "0" : obj.toString());
	}

	@Override
	public void saveUserAwardRec(AwardRec rec) {
		userDao.save("saveUserAwardRec",rec);
	}
}
