package org.vegetto.mybatis.user.biz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.vegetto.common.base.mybatis.entity.PageBean;
import org.vegetto.common.base.mybatis.entity.PageParam;
import org.vegetto.mybatis.user.dao.PmsUserDao;
import org.vegetto.mybatis.user.entity.PmsUser;


/**
 * 
 * @描述: 用户表--服务层接口 .
 */
@Service("pmsUserBiz")
public class PmsUserBiz {

	@Resource
	private PmsUserDao pmsUserDao;
	
	/**
	 * 保存用户信息.
	 * @param pmsUser
	 */
	public void create(PmsUser pmsUser) {
		pmsUserDao.insert(pmsUser);
	}
	
	/**
	 * 根据ID获取用户信息.
	 * @param userId
	 * @return
	 */
	public PmsUser getById(Long userId) {
		return pmsUserDao.getById(userId);
	}

	/**
	 * 根据登录名取得用户对象
	 */
	public PmsUser findUserByUserNo(String userNo) {
		return pmsUserDao.findByUserNo(userNo);
	}

	/**
	 * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除.
	 * 
	 * @param id
	 *            用户ID.
	 */
	public void deleteUserById(long userId) {
		PmsUser pmsUser = pmsUserDao.getById(userId);
		if (pmsUser != null) {
			if ("1".equals(pmsUser.getUserType())) {
				throw new RuntimeException("【" + pmsUser.getUserNo() + "】为超级管理员，不能删除！");
			}
			pmsUserDao.deleteById(pmsUser.getId());
		}
	}

	
	/**
	 * 更新用户信息.
	 * @param user
	 */
	public void update(PmsUser user) {
		pmsUserDao.update(user);
	}
	
	/**
	 * 根据用户ID更新用户密码.
	 * 
	 * @param userId
	 * @param newPwd
	 *            (已进行SHA1加密)
	 */
	public void updateUserPwd(Long userId, String newPwd, boolean isTrue) {
		PmsUser pmsUser = pmsUserDao.getById(userId);
		pmsUser.setUserPwd(newPwd);
		pmsUser.setPwdErrorCount(0); // 密码错误次数重置为0
		pmsUser.setIsChangedPwd(isTrue); // 设置密码为已修改过
		pmsUserDao.update(pmsUser);
	}



	/**
	 * 查询并分页列出用户信息.
	 * @param pageParam
	 * @param paramMap
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap) {
		return pmsUserDao.listPage(pageParam, paramMap);
	}
	
	
	public long createBatch(List<PmsUser> list){
		int result=0;
		for (PmsUser pmsUser : list) {
			result+=pmsUserDao.insert(pmsUser);
		}
		return result;
	}
	public List<PmsUser> getAll(){
		return this.pmsUserDao.findAll();
	}



}