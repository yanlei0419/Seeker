package junit.test.factory;

import java.util.ArrayList;
import java.util.List;

public class Factory  {
	private List<Msg> list=new ArrayList<Msg>();
	
	
	public synchronized void add(Msg msg){
		System.out.println("add"+list.size());
		while(list.size()>=3){
			try {
				System.out.println(".........................满了,等待......................");
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.notify();
		this.list.add(msg);
		System.out.println("添加一条消息>>>>> "+msg.toString());
	}
	
	public synchronized void sum(){
		System.out.println("sum"+list.size());
		while(list.size()==0){
			try {
				System.out.println(".........................没有了,等待...............");
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		Msg  msg=this.list.get(0);
		this.list.remove(0);
		System.out.println("减少一条消息>>>>> "+msg.getMsg());
	}

}
