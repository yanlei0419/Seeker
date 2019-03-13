package org.seeker.thread.t20190312.entity;

/**
 *
 */
public class RelaTaskPo {

    private String id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务状态   0 未处理  1 已处理 2 处理异常  3 等待处理
     */
    private String taskStatus;

    /**
     * 任务依赖数量
     */
    private String taskDependCount;

    /**
     * 依赖任务名称
     */
    private String dependTaskName;

    /**
     * 任务开始时间
     */
    private String taskStartTime;

    /**
     * 任务结束时间
     */
    private String taskEndTime;

    /**
     * 版本号 用于多线程之间的修改
     */
    private String version;

    /**
     * 任务大类
     */
    private String taskType;

    /**
     *  任务排序
     */
    private Integer seq;

    /**
     * 任务顺序
     */
    private Integer taskTypeSeq;

    /**
     * 执行语句
     */
    private String sql;




    /**
     * 管理类数据
    */

    /**
     * 修改数据时间
     */
    private String updateTime;

    /**
     * 修改任务管理员
     */
    private String updateBy;

    /**
     * 创建任务管理员
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否已删除
     */
    private String isDelete;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskDependCount() {
        return taskDependCount;
    }

    public void setTaskDependCount(String taskDependCount) {
        this.taskDependCount = taskDependCount;
    }

    public String getDependTaskName() {
        return dependTaskName;
    }

    public void setDependTaskName(String dependTaskName) {
        this.dependTaskName = dependTaskName;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getTaskTypeSeq() {
        return taskTypeSeq;
    }

    public void setTaskTypeSeq(Integer taskTypeSeq) {
        this.taskTypeSeq = taskTypeSeq;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
