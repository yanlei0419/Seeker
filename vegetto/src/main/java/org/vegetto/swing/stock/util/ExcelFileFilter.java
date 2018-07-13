package org.vegetto.swing.stock.util;

import java.io.File;

import javax.swing.filechooser.FileFilter;

//因为我们需要创建一个文件的过滤器，以便让文件对话框显示我们指定的文件。这里我们就以Excel文件和exe文件举例。
//那么要做到这点我们就需要重写FileFilter 类的accept来设置相关的过滤器。这个继承类你可以写成外部类、内部类甚至是
//匿名内部类。我这里写成了一个内部类的形式。因为是在main方法使用他，因此这个类也要定义成 静态的。
public class ExcelFileFilter extends FileFilter {
	String ext;

	// 构造方法的参数是我们需要过滤的文件类型。比如excel文件就是 xls,exe文件是exe.
	public ExcelFileFilter(String ext) {
		this.ext = ext;
	}

	// 这个方法就是重写 FileFilter
	// 类的方法，参数是File对象，一般这个参数是如何传入的我们可以不用关心。他返回一个布尔型。
//	如果为真表示该文件符合过滤设置，那么就会显示在当前目录下，如果为假就会被过滤掉。
	public boolean accept(File file) {
		// 首先判断该目录下的某个文件是否是目录，如果是目录则返回true，即可以显示在目录下。
		if (file.isDirectory()) {
			return true;
		}
		// 获得某个文件的文件名，然后使用lastIndexOf()来获得这个文件名字符串中'.'这个字符最后一次出现的位置。
//		并且通过它返回的一个整型来判断该文件是否符合*.*这中文件+文件名的格式，如果不符合那么就不显示这个文件。
//		如果符合，那么就将'.'字符后面的字符串提取出来与过滤的文件名相比较，如果相等则符合该文件格式，并显示出来。如果不相等那么就将其过滤掉。*/
		String fileName = file.getName();
		int index = fileName.lastIndexOf('.');
		if (index > 0 && index < fileName.length() - 1) {
			String extension = fileName.substring(index + 1).toLowerCase();
			if (extension.equals(ext))
				return true;
		}
		return false;
	}

	/*
	 * 这个方法也是重写FileFilter的方法，作用是在过滤名那里显示出相关的信息。这个与我们过滤的文件类型想匹配，通过这些信息，
	 * 可以让用户更清晰的明白需要过滤什么类型的文件。
	 */
	public String getDescription() {
		if (ext.equals("txt")) {
			return " 记事本文件(*.txt)";
		}
		if (ext.equals("java")) {
			return "java文件(*.java)";
		}
		return "";
	}
}
