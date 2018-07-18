package org.vegetto.timer;

import org.seeker.timer.MyTimerTask;

import java.util.Timer;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer t=new Timer();
		t.schedule(new MyTimerTask(), 0, 5*1000l);

	}

}
