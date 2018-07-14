package junit.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

public class RequestBinTest {

	public static int THREADNUM = 1000;
	public static int CARIDNUM = 17;
	public String[] CarId = new String[THREADNUM];
	public String temp = "11111111111111111";
	char c1 = temp.charAt(0);
	char c2 = temp.charAt(1);
	char c3 = temp.charAt(2);
	char c4 = temp.charAt(3);

	public static String getNumber7FromMath() {
		String pattern = "00000000000000000";
		// String pattern="00000000";
		DecimalFormat fmt = new DecimalFormat(pattern);
		long val = System.currentTimeMillis();
		return fmt.format(val);
	}

	public static void main(String[] args) {
		// RequestBinTest rb = new RequestBinTest();
		// rb.fun(THREADNUM);
		System.out.println(getNumber7FromMath());
	}

	public void fun(int count) {
		for (int i = 0; i < count; i++) {
			CarId[i] = getNumber7FromMath();
			SocketThreadA a = new SocketThreadA(CarId[i] + i);
			a.start();
			System.out.println("线程号：" + a.getId() + ",carid:" + (CarId[i] + i));
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class SocketThreadA extends Thread {
	public static int PORT = 8888;
	public String host = "127.0.0.1";
	public String id;

	public SocketThreadA(String carid) {
		id = carid;
	}

	public void run() {
		try {
			testFunction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testFunction() throws Exception {
		Socket socket = new Socket(host, PORT);
		OutputStream outputStream = socket.getOutputStream();
		InputStream inputStream = socket.getInputStream();
		String temp = "0x230x230x020x01" + id + "0x010x000x56";// 向服务端发送的数据
		byte[] init = temp.getBytes();
		outputStream.write(init);// 向服务端发送数据
		byte[] revice;
		byte[] data_front = { 
					0x01, 0x22, 0x33, 0x10, 0x11, 0x2e, 0x11, (byte) 0xe2, 0x33, 
					0x23,0x2e, 0x02, 0x11, 0x13, 0x25, 0x32, 0x02, (byte) 0xea, 
					0x00, 0x03, 0x22, (byte) 0xea, (byte) 0xaa,0x03, 0x01,0x02, 
					0x02, 0x01, 0x03, 0x00, (byte) 0x91, 0x08,0x71, 0x22, 0x01, 
					0x04, 0x55, 0x32, 0x01, 0x1c, (byte) 0xea, 0x01, 0x04, 
					(byte) 0xee,(byte) 0xfa, 0x01, (byte) 0xea,(byte) 0xea, 0x11, 
					0x11, 0x22, 0x04, (byte) 0xef, 0x07, 0x00 
				};
		byte[] data_remain = { 0x06, 0x66, 0x00, 0x06, 0x08, 0x02, 0x03, 0x03, (byte) 0xfa, 0x44, 0x23, 0x30, 0x4a, (byte) 0xaa, (byte) 0xbb, 0x45, 0x33, 0x11, 0x13 };
		byte[] latitude = Latitude(23);
		byte[] longitude = Longitude(55);
		int data_count = data_front.length + latitude.length + longitude.length + data_remain.length;
		byte[] data = new byte[data_count];
		int pre = 0;
		System.arraycopy(data_front, 0, data, pre, data_front.length);
		pre += data_front.length;
		System.arraycopy(longitude, 0, data, pre, longitude.length);
		pre += longitude.length;
		System.arraycopy(latitude, 0, data, pre, latitude.length);
		pre += latitude.length;
		System.arraycopy(data_remain, 0, data, pre, data_remain.length);
		int count = inputStream.available();

		// while(true){
			 revice = new byte[count];
			 inputStream.read(revice);// 客户端从服务端接收数据
			 outputStream.write(data);
		// }

		inputStream.close();
		outputStream.close();
	}

	public byte[] Longitude(double longitude) {
		double random = (int) (Math.random() * 1000) * 0.000001;
		longitude = longitude + random;
		DecimalFormat df = new DecimalFormat("##.000000");// 保留六位小数
		String Templongitude = String.valueOf(df.format(longitude));// 转换为字符串
		return Templongitude.getBytes();
	}

	public byte[] Latitude(double latitude) {
		double random = (int) (Math.random() * 1000) * 0.000001;
		latitude = latitude + random;
		DecimalFormat df = new DecimalFormat("##.000000");
		String Templatitude = String.valueOf(df.format(latitude));
		return Templatitude.getBytes();
	}
}
