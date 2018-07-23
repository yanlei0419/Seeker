package org.seeker.thread;


import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.FutureTask;

/**
 * 闭锁
 */
public class FutureTaskTest{

    DelayQueue dq;//双向队列
    FutureTask ft;//闭锁
    CopyOnWriteArrayList cowa;//同步容器
}
