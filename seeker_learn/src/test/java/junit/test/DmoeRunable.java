package junit.test;

public class DmoeRunable implements Runnable {
	public int b=100;
	
	public synchronized void m1() throws InterruptedException{
		b=1000;
		Thread.sleep(5000);
		System.out.println(" m1() >>>> b  = "+b);
	}
	
	public synchronized void m2() throws InterruptedException{
		b=2000;
		Thread.sleep(2500);
		System.out.println("m2()>>>>>b  = "+b);
	}

	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws InterruptedException {
		DmoeRunable test=new DmoeRunable();
		System.out.println(test.b);

		Thread t=new Thread(test);
		t.start();
		
		test.m2();
		System.out.println(test.b);
		
	}

}
