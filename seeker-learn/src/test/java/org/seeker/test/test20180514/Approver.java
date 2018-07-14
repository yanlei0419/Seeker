package org.seeker.test.test20180514;

// 抽象处理者（审批者类）
public abstract class Approver {

    protected Approver successor;  //定义后继处理对象

    //设置后继者
    public void setSuccessor(Approver successor) { 
        this.successor = successor;
    }

    //抽象请求处理方法
    public abstract void processRequest(PurchaseRequest request);
}