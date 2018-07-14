package org.seeker.txt.ve.pack;

import java.util.Scanner;

public class test {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		while (true) {

			System.out.println("请输入指令");
			String str = scan.next();
			if (str.equals("2")) {
				System.out.println("输入2");
				System.out.println("无限循环");

			} else if (str.equals("1")) {

				System.out.println("输入1  请选择3或者其他");

				while (true) {
					System.out.println("1里面的循环");
					String a = scan.next();
					if (a.equals("3")) {
						System.out.println(3);
						break;
					} else {
						System.out.println("无限循环");

					}
				}

			}
		}

	}

}
