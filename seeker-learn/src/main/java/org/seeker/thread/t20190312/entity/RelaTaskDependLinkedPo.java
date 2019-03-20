package org.seeker.thread.t20190312.entity;

public class RelaTaskDependLinkedPo {

    /**
     * 主键
     */
    private String id;
    /**
     *任务id
     */
    private String relaTaskId;
    /**
     * 依赖任务id
     */
    private String relaDependTaskId;

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

    public String getRelaTaskId() {
        return relaTaskId;
    }

    public void setRelaTaskId(String relaTaskId) {
        this.relaTaskId = relaTaskId;
    }

    public String getRelaDependTaskId() {
        return relaDependTaskId;
    }

    public void setRelaDependTaskId(String relaDependTaskId) {
        this.relaDependTaskId = relaDependTaskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
