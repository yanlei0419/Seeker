package org.seeker.test.test20180713.demo08;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

//读线程
class ThreadRead extends Thread {

    private PipedInputStream input;

    public ThreadRead(PipedInputStream input) {
        super();
        this.input = input;
    }

    public void readMethod(PipedInputStream input) {
        try {
            System.out.println("read  :");
            byte[] byteArray = new byte[20];
            int readLength = input.read(byteArray);
            while (readLength != -1) {
                String newData = new String(byteArray, 0, readLength);
                System.out.print(newData);
                readLength = input.read(byteArray);
            }
            System.out.println();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.readMethod(input);
    }
}

//写线程
class ThreadWrite extends Thread {

    private PipedOutputStream out;

    public ThreadWrite(PipedOutputStream out) {
        super();
        this.out = out;
    }

    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write :");
            for (int i = 0; i < 30; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.writeMethod(out);
    }
}

//测试
public class Run {

    public static void main(String[] args) {
        try {
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            // inputStream.connect(outputStream);   // 效果相同
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(inputStream);
            threadRead.start();

            Thread.sleep(2000);

            ThreadWrite threadWrite = new ThreadWrite(outputStream);
            threadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}/* Output: 
        read  :
        write :
        123456789101112131415161718192021222324252627282930
        123456789101112131415161718192021222324252627282930
 *///:~