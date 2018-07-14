package org.vegetto.common.util;

import java.awt.Component;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 * 公共方法
 * 
 */
public class CommonFunctions {
	/**
	 * //public static String ProjectPath=System.getProperty("user.dir"); /**
	 * 数据库查询结果公用List
	 */
	public static List list = null;

	// 标准函数解释语句
	/**
	 * 获取当前系统时间字符串，并用于文件或文件夹的命名，所以不包括文件名不支持的字符
	 * 
	 * @return (String) 当前系统时间 格式为 "年月日-时分秒" ，如 20091202-204506
	 */
	public static String GetCurrentTimeInFileName() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		String Stryear = String.valueOf(year);

		int month = ca.get(Calendar.MONTH);// 获取月份
		String Strmonth = String.valueOf(month);
		if (month < 10) {
			Strmonth = "0" + Strmonth;
		}

		int day = ca.get(Calendar.DATE);// 获取日
		String Strday = String.valueOf(day);
		if (day < 10) {
			Strday = "0" + Strday;
		}

		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String Strhour = String.valueOf(hour);
		if (hour < 10) {
			Strhour = "0" + Strhour;
		}

		int minute = ca.get(Calendar.MINUTE);// 分
		String Strminute = String.valueOf(minute);
		if (minute < 10) {
			Strminute = "0" + Strminute;
		}

		int second = ca.get(Calendar.SECOND);// 秒
		String Strsecond = String.valueOf(second);
		if (second < 10) {
			Strsecond = "0" + Strsecond;
		}

		String CurrentTimeInFileName = Stryear + Strmonth + Strday + "-" + Strhour + Strminute + Strsecond;
		return CurrentTimeInFileName;
	}

	/**
	 * 获取当前系统日期(8位长整型)，是调用计算机系统函数获取计算机时间
	 * 
	 * @return (long) 当前系统时间 格式为 "年月日" ，20091202
	 */
	public static long GetCurrentDateInLong() {
		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		String Stryear = String.valueOf(year);

		int month = ca.get(Calendar.MONTH) + 1;// 获取月份
		String Strmonth = String.valueOf(month);
		if (month < 10) {
			Strmonth = "0" + Strmonth;
		}

		int day = ca.get(Calendar.DATE);// 获取日
		String Strday = String.valueOf(day);
		if (day < 10) {
			Strday = "0" + Strday;
		}

		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String Strhour = String.valueOf(hour);
		if (hour < 10) {
			Strhour = "0" + Strhour;
		}

		int minute = ca.get(Calendar.MINUTE);// 分
		String Strminute = String.valueOf(minute);
		if (minute < 10) {
			Strminute = "0" + Strminute;
		}

		int second = ca.get(Calendar.SECOND);// 秒
		String Strsecond = String.valueOf(second);
		if (second < 10) {
			Strsecond = "0" + Strsecond;
		}

		String CurrentDateStr = Stryear + Strmonth + Strday;
		long CurrentTimeInLong = Long.parseLong(CurrentDateStr);
		return CurrentTimeInLong;
	}

	// 标准函数解释语句
	/**
	 * 获取当前系统时间
	 * 
	 * @return (String) 当前系统时间 格式为 "年-月-日 时:分:秒"，如 "2009-12-02 20:45:06"
	 */
	public static String GetCurrentTime() {
		/*
		 * //SimpleDateFormat bartDateFormat = new
		 * SimpleDateFormat("yyyy'-'MM'-'dd' 'hh':'mm':'ss"); DateFormat
		 * dateformat = DateFormat.getDateTimeInstance(); Calendar date =
		 * Calendar.getInstance(); String
		 * CurrentTime=dateformat.format(date.getTime());
		 * //System.out.println("######当前时间为： "+CurrentTime);
		 */

		Calendar ca = Calendar.getInstance();
		int year = ca.get(Calendar.YEAR);// 获取年份
		String Stryear = String.valueOf(year);

		int month = ca.get(Calendar.MONTH) + 1;// 获取月份 月份系统从0开始算起的
		String Strmonth = String.valueOf(month);
		if (month < 10) {
			Strmonth = "0" + Strmonth;
		}

		int day = ca.get(Calendar.DATE);// 获取日
		String Strday = String.valueOf(day);
		if (day < 10) {
			Strday = "0" + Strday;
		}

		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String Strhour = String.valueOf(hour);
		if (hour < 10) {
			Strhour = "0" + Strhour;
		}

		int minute = ca.get(Calendar.MINUTE);// 分
		String Strminute = String.valueOf(minute);
		if (minute < 10) {
			Strminute = "0" + Strminute;
		}

		int second = ca.get(Calendar.SECOND);// 秒
		String Strsecond = String.valueOf(second);
		if (second < 10) {
			Strsecond = "0" + Strsecond;
		}

		String CurrentTime = Stryear + "-" + Strmonth + "-" + Strday + " " + Strhour + ":" + Strminute + ":" + Strsecond;
		return CurrentTime;
	}

	/**
	 * 获取同一天内两时间字符串的时间差 以秒为单位, 时间字符串格式为2009-12-02 20:45:06
	 * 
	 * 
	 */
	public static int GetCostTimeInSecond(String BeginTime, String EndTime) {
		int CostTimeInSecond = 0;
		BeginTime = BeginTime.substring(BeginTime.length() - 8);
		EndTime = EndTime.substring(EndTime.length() - 8);
		int IntShi_BeginTime = Integer.parseInt(BeginTime.substring(0, 2)), IntFen_BeginTime = Integer.parseInt(BeginTime.substring(3, 5)), IntMiao_BeginTime = Integer.parseInt(BeginTime.substring(6)), IntShi_EndTime = Integer.parseInt(EndTime.substring(0, 2)), IntFen_EndTime = Integer.parseInt(EndTime.substring(3, 5)), IntMiao_EndTime = Integer.parseInt(EndTime.substring(6));
		int DeltaShi = IntShi_EndTime - IntShi_BeginTime, DeltaFen = IntFen_EndTime - IntFen_BeginTime, DeltaMiao = IntMiao_EndTime - IntMiao_BeginTime;
		// System.out.println("###########"+IntMiao_EndTime);
		CostTimeInSecond = DeltaShi * 3600 + DeltaFen * 60 + DeltaMiao;
		return CostTimeInSecond;
	}

	/**
	 * 修改日期天数
	 * 
	 * @param baseDate
	 *            基准日期String (格式yyyy-MM-dd)
	 * @param amount
	 *            要增加的天数（负数为减）
	 * @return 基准日期增加或减少若干天后的日期
	 */
	public static String dateModify(String baseDate, int amount) {
		StringBuffer sb = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 将字符串格式化
		Date dt = sdf.parse(baseDate, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象

		Calendar c = Calendar.getInstance(); // 初始化一个Calendar
		try {
			c.setTime(dt); // 设置基准日期
		} catch (Exception e) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyy-MM-dd！");
			return null;
			// e.printStackTrace();
		}

		c.add(Calendar.DATE, amount); // 你要加减的日期
		Date dt1 = c.getTime(); // 从Calendar对象获得基准日期增减后的日期
		sb = sdf.format(dt1, sb, new FieldPosition(0)); // 得到结果字符串

		return sb.toString();
	}

	/**
	 * 修改日期天数
	 * 
	 * @param baseDate
	 *            基准日期String (格式yyyyMMdd)
	 * @param amount
	 *            要增加的天数（负数为减）
	 * @return 基准日期增加或减少若干天后的日期
	 */

	public static String dateModifyD(String baseDate, int amount) {
		StringBuffer sb = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 将字符串格式化
		Date dt = sdf.parse(baseDate, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象

		Calendar c = Calendar.getInstance(); // 初始化一个Calendar
		try {
			c.setTime(dt); // 设置基准日期
		} catch (Exception e) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyyMMdd！");
			return null;
			// e.printStackTrace();
		}

		c.add(Calendar.DATE, amount); // 你要加减的日期
		Date dt1 = c.getTime(); // 从Calendar对象获得基准日期增减后的日期
		sb = sdf.format(dt1, sb, new FieldPosition(0)); // 得到结果字符串

		return sb.toString();
	}

	/**
	 * 修改如干月,并处理到月底
	 * 
	 * @param baseDate
	 *            基准日期String (格式yyyyMMdd)
	 * @param amount
	 *            要增加的月（负数为减）
	 * @return 基准日期增加或减少若干月后的日期
	 */
	public static String dateModifyM(String baseDate, int amount) {
		// 处理到月底
		String date2_nm = dateModifyM_normal(baseDate, amount + 1);
		long d = Long.valueOf(date2_nm);
		d = d / 100 * 100 + 1;
		d = pub_base_deadlineD(d, -1);
		return String.valueOf(d);
	}

	/**
	 * 修改如干月
	 * 
	 * @param baseDate
	 *            基准日期String (格式yyyyMMdd)
	 * @param amount
	 *            要增加的月（负数为减）
	 * @return 基准日期增加或减少若干月后的日期
	 */
	public static String dateModifyM_normal(String baseDate, int amount) {
		StringBuffer sb = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 将字符串格式化
		Date dt = sdf.parse(baseDate, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象

		Calendar c = Calendar.getInstance(); // 初始化一个Calendar
		try {
			c.setTime(dt); // 设置基准日期
		} catch (Exception e) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyyMMdd！");
			return null;
			// e.printStackTrace();
		}

		c.add(Calendar.MONTH, amount); // 你要加减的日期
		Date dt1 = c.getTime(); // 从Calendar对象获得基准日期增减后的日期
		sb = sdf.format(dt1, sb, new FieldPosition(0)); // 得到结果字符串

		return sb.toString();
	}

	/**
	 * 修改如干年
	 * 
	 * @param baseDate
	 *            基准日期String (格式yyyyMMdd)
	 * @param amount
	 *            要增加的年（负数为减）
	 * @return 基准日期增加或减少若干年后的日期
	 */
	public static String dateModifyY(String baseDate, int amount) {
		StringBuffer sb = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 将字符串格式化
		Date dt = sdf.parse(baseDate, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象

		Calendar c = Calendar.getInstance(); // 初始化一个Calendar
		try {
			c.setTime(dt); // 设置基准日期
		} catch (Exception e) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyyMMdd！");
			return null;
			// e.printStackTrace();
		}

		c.add(Calendar.YEAR, amount); // 你要加减的日期
		Date dt1 = c.getTime(); // 从Calendar对象获得基准日期增减后的日期
		sb = sdf.format(dt1, sb, new FieldPosition(0)); // 得到结果字符串

		return sb.toString();
	}

	/**
	 * 按天数修改日期
	 * 
	 * @param baseDateLong
	 *            (long) 基准日期long 8位 (格式yyyyMMdd)
	 * @param changeDays
	 *            (int) 要增加的天数（负数为减）
	 * @return (long) 基准日期增加或减少若干天后的日期(格式yyyyMMdd)
	 */
	public static long pub_base_deadlineD(long baseDateLong, int changeDays) {
		long ResultDateLong = 0;

		String baseDate = String.valueOf(baseDateLong);
		if (baseDate.length() != 8) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyyMMdd！");
			return 0;
		}
		StringBuffer sb = new StringBuffer();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 将字符串格式化
		Date dt = sdf.parse(baseDate, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象

		Calendar c = Calendar.getInstance(); // 初始化一个Calendar

		try {
			c.setTime(dt); // 设置基准日期
		} catch (Exception e) {
			System.out.println("输入日期" + baseDate + "格式错误，这不符合格式yyyyMMdd！");
			return 0;
			// e.printStackTrace();
		}

		c.add(Calendar.DATE, changeDays); // 你要加减的日期
		Date dt1 = c.getTime(); // 从Calendar对象获得基准日期增减后的日期
		sb = sdf.format(dt1, sb, new FieldPosition(0)); // 得到结果字符串
		String ResultDateStr = sb.toString();

		ResultDateLong = Long.parseLong(ResultDateStr);
		System.out.println(baseDateLong + "变化 " + changeDays + " 天后结果为:" + ResultDateLong);
		return ResultDateLong;
	}

	/**
	 * 返回两日期之间相差的月份 ----DateLong1-DateLong2之间相差的月份
	 * 
	 * @param DateLong1
	 *            (long) 被减数日期long 8位 (格式yyyyMMdd)
	 * @param DateLong2
	 *            (long) 减数日期long 8位 (格式yyyyMMdd)
	 * @return DateLong1-DateLong2之间相差的月份
	 */
	public static int monthsSubtract(long DateLong1, long DateLong2) {
		int m1 = (int) (DateLong1 / 100 % 100);
		int m2 = (int) (DateLong2 / 100 % 100);
		int y1 = (int) (DateLong1 / 10000);
		int y2 = (int) (DateLong2 / 10000);
		int delta_m = (y1 - y2) * 12 + (m1 - m2);
		return delta_m;
	}

	/**
	 * 返回两日期相减的结果天数，负数表示dateStr1在dateStr2前。dateStr1格式为"yyyyMMdd"
	 * 
	 * @param dateStr1
	 *            String 被减数日期字符串
	 * @param dateStr2
	 *            String 减数日期字符串
	 * @return int dateStr1-dateStr2的天数
	 */
	public static int daysSubtract(String dateStr1, String dateStr2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); // 将字符串格式化
		Date dt1 = sdf.parse(dateStr1, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象
		Date dt2 = sdf.parse(dateStr2, new ParsePosition(0)); // 由格式化后的字符串产生一个Date对象
		long mills = dt1.getTime() - dt2.getTime();
		return (int) (mills / 1000 / 3600 / 24);
	}

	/**
	 * 文件选择器
	 * 
	 * @param DialogMode
	 *            (int) 文件选择器模式，是打开文件还是保存文件(0表打开，其他代表保存)
	 * @param FileDescription
	 *            (String) 通俗文件名(如：ExcelFile)
	 * @param FileSuffix
	 *            (String) 文件后缀名(如：xls)
	 * @param parent
	 *            (Component) 该文件选择器窗体的父窗体
	 * @return ExcelFilePath (String) 选择结果的文件全路径,若选择“取消”则返回空字符串""
	 */
	public static String FileChooser(int DialogMode, String FileDescription, String FileSuffix, Component parent) {
		JFileChooser fc = new JFileChooser();
		String DialogTitle = "";
		if (DialogMode == 0) {
			DialogTitle = "打开" + FileDescription;
		} else {
			DialogTitle = "保存" + FileDescription;
		}
		final String FileSuffixLowerCase = FileSuffix.toLowerCase(), FileSuffixUpperCase = FileSuffix.toUpperCase();
		final String Description = FileDescription + "s";
		fc.setDialogTitle(DialogTitle);
		fc.setFileFilter(new FileFilter() {
			public boolean accept(File f) {
				return f.isDirectory() || f.getPath().endsWith(FileSuffixLowerCase) || f.getPath().endsWith(FileSuffixUpperCase);
			}

			public String getDescription() {
				return Description;
			}
		});
		int intRetVal = 0;
		if (DialogMode == 0) {
			intRetVal = fc.showOpenDialog(parent);
		} else {
			intRetVal = fc.showSaveDialog(parent);
		}

		if (intRetVal == JFileChooser.APPROVE_OPTION) {
			String ExcelFilePath = fc.getSelectedFile().getPath();
			// System.out.println("要"+DialogTitle+"的路径为: "+ExcelFilePath);
			return ExcelFilePath;
		} else {
			return "";
		}
	}

	/**
	 * 小数保留位数截取 并四舍五入,最高支持保留9位小数，大于9位时不做任何处理 直接返回
	 * 
	 * @param d
	 *            (double) 要进行截位的小数
	 * @param n
	 *            (int) 保留的小数位数
	 * @return 截位后的结果
	 */
	public static double doublecut(double d, int n) {
		if (d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || d == Double.NaN) {
			return Double.NaN;
		}

		if (n >= 10) {
			return d;
		}
		boolean isLowZero = false;
		if (d < 0) {
			d = -d;
			isLowZero = true;
		}
		long jishu = (int) Math.pow(10, n);
		long longd = (long) (d * jishu);
		if (d * jishu >= (longd + 0.5)) {
			longd++;
		}
		d = longd / (double) jishu;

		if (isLowZero) {
			d = -d;
		}

		return d;
	}

	/**
	 * 小数保留位数截取 并四舍五入,最高支持保留9位小数，大于9位时不做任何处理 直接返回
	 * 
	 * @param d
	 *            (double) 要进行截位的小数
	 * @param n
	 *            (int) 保留的小数位数
	 * @return 截位后的String结果
	 */
	public static String doubleFormat(double d, int n) {
		if (d == Double.POSITIVE_INFINITY || d == Double.NEGATIVE_INFINITY || d == Double.NaN) {
			return "NaN";
		}

		if (n >= 10) {
			return String.valueOf(d);
		}
		boolean isLowZero = false;
		if (d < 0) {
			d = -d;
			isLowZero = true;
		}
		long jishu = (int) Math.pow(10, n);
		long longd = (long) (d * jishu);
		if (d * jishu >= (longd + 0.5)) {
			longd++;
		}
		d = longd / (double) jishu;

		if (isLowZero) {
			d = -d;
		}
		if (n >= 1) {
			String mark = "0.";
			for (int i = 0; i < n; i++) {
				mark += "0";
			}
			DecimalFormat df = new DecimalFormat(mark);
			return df.format(d);
		}

		return String.valueOf(d);
	}

	/*
	 * public static String getSuper_item_no(String item_no){ String
	 * super_item_no=""; String
	 * sql="select superItemNo from ItemToAcc where itemNo='"+item_no+"'"; List
	 * list=mydao.query(sql, null); if(list.size()==0){
	 * System.out.println("项目对应科目表里不存在项目 "+item_no); return null; }
	 * super_item_no=(String)list.get(0); return super_item_no; }
	 */
	/**
	 * 获取当前系统时分秒，是调用计算机系统函数获取计算机时分秒
	 * 
	 * @return 当前系统时间 格式为 "hhmmss"
	 */
	public static String getCurrentTime() {
		Calendar ca = Calendar.getInstance();

		int hour = ca.get(Calendar.HOUR_OF_DAY);// 小时
		String Strhour = String.valueOf(hour);
		if (hour < 10) {
			Strhour = "0" + Strhour;
		}

		int minute = ca.get(Calendar.MINUTE);// 分
		String Strminute = String.valueOf(minute);
		if (minute < 10) {
			Strminute = "0" + Strminute;
		}

		int second = ca.get(Calendar.SECOND);// 秒
		String Strsecond = String.valueOf(second);
		if (second < 10) {
			Strsecond = "0" + Strsecond;
		}

		String CurrentDateStr = Strhour + Strminute + Strsecond;
		return CurrentDateStr;
	}

	/**
	 * 获取两时间字符串的时间差 以秒为单位, 时间字符串格式为20091202204506
	 * 
	 * @param BeginTime
	 *            开始时间
	 * @param EndTime
	 *            结束时间
	 * 
	 */
	public static long GetTimeInSecond(String BeginTime, String EndTime) {
		String beginTime = BeginTime.substring(0, 4) + "-" + BeginTime.substring(4, 6) + "-" + BeginTime.substring(6, 8) + " " + BeginTime.substring(8, 10) + ":" + BeginTime.substring(10, 12) + ":" + BeginTime.substring(12, 14);
		String endTime = EndTime.substring(0, 4) + "-" + EndTime.substring(4, 6) + "-" + EndTime.substring(6, 8) + " " + EndTime.substring(8, 10) + ":" + EndTime.substring(10, 12) + ":" + EndTime.substring(12, 14);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long times = 0;
		try {
			Date d1 = df.parse(endTime);
			Date d2 = df.parse(beginTime);
			long diff = d1.getTime() - d2.getTime();
			times = diff / 1000;
		} catch (Exception e) {
		}
		return times;
	}

	/**
	 * 获取当前月所有的天数
	 * 
	 * @param yyyymm
	 * @return
	 * @throws ParseException
	 */
	public static int getDaysOfMonth(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Calendar calendar = new GregorianCalendar();
		Date date;
		try {
			date = sdf.parse(strDate);
			calendar.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;

	}

	public static void main(String[] args) {
		System.out.println(GetTimeInSecond("20111321", "20111321"));
	}
}
