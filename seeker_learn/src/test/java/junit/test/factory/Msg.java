package junit.test.factory;

public class Msg {
	private String msg;
	public Msg(){}
	public Msg(String msg){
		this.msg=msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String toString(){
		return msg;
	}

}
