package org.seeker.timer;

import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask{

	/** *
	 * Timer t=new Timer();
		方法执行后0s 启动 ,没5秒直行一次
		t.schedule(new MyTimerTask(), 0, 5*1000l);
	 */
	@Override
	public void run() {
		//必须继承TimerTask,并使用Timer类
		System.out.println("测试timer类,");
	}

}
