package junit.test.factory;

import java.util.Random;

public class Producer implements Runnable {
	Factory fa = null;

	public Producer() {

	}

	public Producer(Factory fa) {
		this.fa = fa;
	}

	public void run() {
		while (true) {
			String[] str = { "1", "2", "3", "4", "5", "6", "7" };
			Random ra = new Random();

			Msg msg = new Msg(str[ra.nextInt(6) + 1]);
			fa.add(msg);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
