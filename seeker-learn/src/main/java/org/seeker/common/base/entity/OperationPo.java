package org.seeker.common.base.entity;

/**
 * 操作日志
 */
public abstract class OperationPo extends Page{
	/**
	 * 创建
	 */
	private String createBy;
	private String createTime;
	private String createDate;
	private long createTimeL;
	private long createDateL;
	/**
	 * 修改
	 */
	private String updateBy;
	private String updateTime;
	private String updateDate;
	private long updateTimeL;
	private long updateDateL;
	/**
	 * 修改
	 */
	private String modifyBy;
	private String modifyTime;
	private String modifyDate;
	private long modifyTimeL;
	private long modifyDateL;
	/**
	 * 当前
	 */
	private String currentBy;

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCurrentBy() {
		return currentBy;
	}

	public void setCurrentBy(String currentBy) {
		this.currentBy = currentBy;
	}

	public long getCreateTimeL() {
		return createTimeL;
	}

	public void setCreateTimeL(long createTimeL) {
		this.createTimeL = createTimeL;
	}

	public long getCreateDateL() {
		return createDateL;
	}

	public void setCreateDateL(long createDateL) {
		this.createDateL = createDateL;
	}

	public long getUpdateTimeL() {
		return updateTimeL;
	}

	public void setUpdateTimeL(long updateTimeL) {
		this.updateTimeL = updateTimeL;
	}

	public long getUpdateDateL() {
		return updateDateL;
	}

	public void setUpdateDateL(long updateDateL) {
		this.updateDateL = updateDateL;
	}

	public long getModifyTimeL() {
		return modifyTimeL;
	}

	public void setModifyTimeL(long modifyTimeL) {
		this.modifyTimeL = modifyTimeL;
	}

	public long getModifyDateL() {
		return modifyDateL;
	}

	public void setModifyDateL(long modifyDateL) {
		this.modifyDateL = modifyDateL;
	}
	

}
