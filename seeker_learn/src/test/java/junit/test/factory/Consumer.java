package junit.test.factory;


public class Consumer implements Runnable {
	Factory fa = null;

	public Consumer() {

	}

	public Consumer(Factory fa) {
		this.fa = fa;
	}

	public void run() {
		while (true) {
			fa.sum();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
