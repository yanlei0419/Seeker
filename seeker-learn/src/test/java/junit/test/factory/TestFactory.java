package junit.test.factory;


public class TestFactory {
	public static void main(String[] args) throws Exception{
			int i=1;
			Factory f=new Factory();
			Producer p = new Producer(f);  
			Consumer c = new Consumer(f);  
			Thread tp = new Thread(p);  
			tp.setName("生产者");
			Thread tc = new Thread(c);  
			tc.setName("消费者");
			tp.start();
			tc.start();
			i++;
	}
}
