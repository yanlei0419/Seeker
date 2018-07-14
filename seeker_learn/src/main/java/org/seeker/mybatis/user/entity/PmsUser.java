package org.vegetto.mybatis.user.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.seeker.common.util.DateUtil;
import org.vegetto.mybatis.entity.BaseEntity;


/**
 * 权限管理-用户
 */
public class PmsUser extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userNo;// 用户帐号(当用户类型为“子帐号”时使用，否则为空)
	private String mainUserId;// 主账号ID
	private String userPwd; // 登录密码
	private String userName; // 姓名
	private String mobileNo; // 手机号
	private String email;// 邮箱
	private Integer status; // 状态(100:可用，101:不可用 )
	private String userType; // 用户类型（1:超级管理员，0:普通用户），超级管理员由系统初始化时添加，不能删除
	private Date lastLoginTime;// 最后登录时间
	private Boolean isChangedPwd;// 是否更改过密码
	private Integer pwdErrorCount; // 连续输错密码次数（连续5次输错就冻结帐号）
	private Date pwdErrorTime; // 最后输错密码的时间
	private String remark; // 描述

	/**
	 * 用户帐号
	 * 
	 * @return
	 */
	public String getUserNo() {
		return userNo;
	}

	/**
	 * 用户帐号
	 * 
	 * @return
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	/**
	 * 主账号ID
	 * 
	 * @return
	 */
	public String getMainUserId() {
		return mainUserId;
	}

	/**
	 * 主账号ID
	 * 
	 * @return
	 */
	public void setMainUserId(String mainUserId) {
		this.mainUserId = mainUserId;
	}

	/**
	 * 登录密码
	 * 
	 * @return
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * 登录密码
	 * 
	 * @return
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 姓名
	 * 
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * 手机号
	 * 
	 * @return
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * 邮箱
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 邮箱
	 * 
	 * @return
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 状态(100:可用，101:不可用 )
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 状态(100:可用，101:不可用 )
	 * 
	 * @return
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 用户类型
	 * 
	 * @return
	 */
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 最后登录时间
	 * 
	 * @return
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 最后登录时间
	 * 
	 * @return
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 是否更改过密码
	 * 
	 * @return
	 */
	public Boolean getIsChangedPwd() {
		return isChangedPwd;
	}

	/**
	 * 是否更改过密码
	 * 
	 * @return
	 */
	public void setIsChangedPwd(Boolean isChangedPwd) {
		this.isChangedPwd = isChangedPwd;
	}

	/**
	 * 连续输错密码次数（连续5次输错就冻结帐号）
	 * 
	 * @return
	 */
	public Integer getPwdErrorCount() {
		return pwdErrorCount;
	}

	/**
	 * 连续输错密码次数（连续5次输错就冻结帐号）
	 * 
	 * @return
	 */
	public void setPwdErrorCount(Integer pwdErrorCount) {
		this.pwdErrorCount = pwdErrorCount;
	}

	/**
	 * 最后输错密码的时间
	 * 
	 * @return
	 */
	public Date getPwdErrorTime() {
		return pwdErrorTime;
	}

	/**
	 * 最后输错密码的时间
	 * 
	 * @return
	 */
	public void setPwdErrorTime(Date pwdErrorTime) {
		this.pwdErrorTime = pwdErrorTime;
	}

	/**
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 描述
	 * 
	 * @return
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 描述
	 * 
	 * @return
	 */
	public PmsUser() {

	}
	
	public Map<String, String> toMap(){
		Map<String, String> map=new HashMap<String, String>();
		
		map.put("0", this.getId()+"");
		map.put("1", this.getVersion()+"");
		map.put("2", DateUtil.getTime(this.getCreateTime()));
		map.put("3", this.userNo);
		map.put("4", this.userType);
		map.put("5", this.mainUserId);
		map.put("6", this.userPwd);
		map.put("7", this.userName);
		map.put("8", this.mobileNo);
		map.put("9", this.email);
		map.put("10", this.status+"");
		map.put("11", DateUtil.getTime(this.lastLoginTime));
		map.put("12", this.isChangedPwd+"");
		map.put("13", this.pwdErrorCount+"");
		map.put("14", DateUtil.getTime(this.pwdErrorTime));
		map.put("15", this.remark);
		return map;
	}

}
