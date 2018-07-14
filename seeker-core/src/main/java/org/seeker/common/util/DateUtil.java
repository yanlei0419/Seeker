package org.seeker.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <strong>Title : DateUtil<br>
 * </strong> <strong>Description : </strong>日期工具类<br>
 * <strong>Create on : 2012-8-1<br>
 * </strong>
 * <p>
 * <strong>Copyright (C) <br>
 * </strong>
 * <p>
 * 
 * @version <strong>OperationRisk V1.0</strong><br>
 *          <br>
 *          <strong>修改历史:</strong><br>
 *          修改人 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class DateUtil {

	/**
	 * <code>S_FULL_TIME_FORMAT</code>-时间格式常量-yyyy-MM-dd HH:mm:ss.
	 */
	public static final String S_FULL_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * <code>S_TIME_FORMAT_YEARMONTHDAY</code>-时间格式常量-yyyy-MM-dd.
	 */
	public static final String S_TIME_FORMAT_YEARMONTHDAY = "yyyy-MM-dd";

	/**
	 * <code>S_TIME_FORMAT_YEARMONTHDAYHOUR</code>-时间格式常量-yyyy-MM-dd HH.
	 */
	public static final String S_TIME_FORMAT_YEARMONTHDAYHOUR = "yyyy-MM-dd HH";

	/**
	 * <code>S_TIME_FORMAT_YEARMONTHDAYHOURMINUTE</code>-时间格式常量-yyyy-MM-dd
	 * HH:mm.
	 */
	public static final String S_TIME_FORMAT_YEARMONTHDAYHOURMINUTE = "yyyy-MM-dd HH:mm";

	/**
	 * <code>S_TIME_FORMAT_HOURMINUTE</code>-时间格式常量-HH:mm.
	 */
	public static final String S_TIME_FORMAT_HOURMINUTE = "HH:mm";

	/**
	 * <code>S_TIME_FORMAT_HOURMINUTESECOND</code>-时间格式常量-HH:mm:ss.
	 */
	public static final String S_TIME_FORMAT_HOURMINUTESECOND = "HH:mm:ss";

	/**
	 * <code>S_COMMA_TIME_FORMAT</code>-逗号分隔的时间格式常量
	 */
	public static final String S_COMMA_TIME_FORMAT = "yyyy,MM,dd,HH,mm,ss";

	/**
	 * 按yyyy-MM-dd样式获取当前日期
	 * 
	 * @return
	 */
	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	/**
	 * 按yyyy-MM-dd HH:mm:ss样式获取当前时间
	 * 
	 * @return
	 */
	public static String getTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 根据样式获取当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String getDate(String format) {
		return new SimpleDateFormat(format).format(new Date());
	}

	/**
	 * 
	 * 方法描述
	 * 
	 * @param strDate
	 *            时间的字符串
	 * @param pattern
	 *            时间格式
	 * @param days
	 *            天数
	 * @return 指定天数加上指定天数的时间的字符串格式
	 */
	public static String AddDay(String strDate, String pattern, int days) {
		if (strDate == null || "".equals(strDate) || pattern == null || "".equals(pattern) || strDate.length() < pattern.length()) {
			return null;
		}
		String resultDate = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			Date date = dateFormat.parse(strDate.substring(0, pattern.length()));
			Calendar cale = Calendar.getInstance();
			cale.setTime(date);
			// 指定时间内加上指定天数
			cale.add(Calendar.DAY_OF_MONTH, days);
			date = cale.getTime();
			// 结果时间的字符串格式
			resultDate = dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}

	/**
	 * 得到系统当前日期时间
	 * 
	 * @return 当前日期时间
	 */
	public static Date getNow() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 页面日期字符串的处理 参数为 long的时间转化为 string 方法描述
	 * 
	 * @param str
	 *            要格式化的字符串
	 * @return 格式为：yyyy-MM-dd HH:mm:ss的String
	 */
	public static String getCheckDate(String str) {
		if (str == null || "".equals(str) || "-1".equals(str) || Long.parseLong(str) < 0) {
			return "&nbsp;";
		}

		SimpleDateFormat t_myFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		date.setTime(Long.parseLong(str));
		String returnStr = t_myFmt.format(date);
		return returnStr;
	}

	/**
	 * 日期，时间格式转换
	 * 
	 * @param datestr
	 *            待转换的字符串
	 * @param pattern
	 *            待转换的字符串的时间格式
	 * @param targetpattern
	 *            要转换的时间格式
	 * @return
	 */
	public static String convertFormat(String datestr, String pattern, String targetpattern) {

		String t_date = "";
		Date t_ddate = null;
		SimpleDateFormat t_dateFormat = new SimpleDateFormat(pattern);
		try {
			t_ddate = t_dateFormat.parse(datestr);
			t_dateFormat = new SimpleDateFormat(targetpattern);
			t_date = t_dateFormat.format(t_ddate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return t_date;
	}

	/**
	 * 以某种格式获取当前的日期数据
	 * 
	 * @param pattern －
	 *            日期格式，如果没有缺省日期格式："yyyy-MM-dd"
	 * @return 当前的日期
	 */
	public static String getCurrentDate(String pattern) {

		String t_rtn = null;

		if (null == pattern || "".equals(pattern)) {
			pattern = "yyyy-MM-dd";
		}

		// 转换日期
		SimpleDateFormat t_dateFormat = new SimpleDateFormat(pattern);
		t_rtn = t_dateFormat.format(getNow());

		return t_rtn;

	}

	/**
	 * 得到当前年份
	 * 
	 * @return 当前年份
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return 当前月份
	 */
	public static int getCurrentMonth() {
		// 用get得到的月份数比实际的小1，需要加上
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}
	/**
	 * 得到当前月份
	 * 
	 * @return 传入时间月份
	 */
	public static int getCurrentMonth(Date date) {
		// 用get得到的月份数比实际的小1，需要加上
		return date.getMonth()+ 1;
	}
	/**
	 * 得到当前日
	 * 
	 * @return 当前日
	 */
	public static int getCurrentDay() {
		return Calendar.getInstance().get(Calendar.DATE);
	}

	/**
	 * 得到当前日的开始时间
	 * 
	 * @return Date
	 */
	public static Date getCurrentDayStart() {
		Date todayDate = new Date();
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(todayDate);

		calendar.set(Calendar.AM_PM, Calendar.HOUR);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(0, Calendar.DATE);
		Date todayDateStart = calendar.getTime();
		return todayDateStart;
	}

	/**
	 * 取得当前日期以后若干天的日期。如果要得到以前的日期，参数用负数。 例如要得到上星期同一天的日期，参数则为-7
	 * 
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(int days) {
		return add(getNow(), days, Calendar.DATE);
	}

	/**
	 * 取得指定日期以后若干天的日期。如果要得到以前的日期，参数用负数。
	 * 
	 * @param date
	 *            基准日期
	 * @param days
	 *            增加的日期数
	 * @return 增加以后的日期
	 */
	public static Date addDays(Date date, int days) {
		return add(date, days, Calendar.DATE);
	}

	/**
	 * 取得当前日期以后某月的日期。如果要得到以前月份的日期，参数用负数。
	 * 
	 * @param months
	 *            增加的月份数
	 * @return 增加以后的日期
	 */
	public static Date addMonths(int months) {
		return add(getNow(), months, Calendar.MONTH);
	}

	/**
	 * 取得指定日期以后某月的日期。如果要得到以前月份的日期，参数用负数.
	 * <p>
	 * 注意，可能不是同一日子，例如2003-1-31加上一个月是2003-2-28
	 * 
	 * @param date -
	 *            基准日期.
	 * @param months -
	 *            增加的月份数.
	 * @return 增加以后的日期.
	 */
	public static Date addMonths(Date date, int months) {
		return add(date, months, Calendar.MONTH);
	}

	/**
	 * 为指定日期增加相应的天数或月数.
	 * 
	 * @param date -
	 *            基准日期.
	 * @param amount -
	 *            增加的数量.
	 * @param field -
	 *            增加的单位，年，月，日，分钟，小时,Calendar类的常量值.
	 * @return 增加以后的日期.
	 */
	public static Date add(Date date, int amount, int field) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.add(field, amount);

		return calendar.getTime();
	}

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数.
	 * 
	 * @param one -
	 *            第一个日期数，作为基准.
	 * @param two -
	 *            第二个日期数，作为比较.
	 * @return 两个日期相差天数.
	 */
	public static long diffDays(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (24 * 60 * 60 * 1000);
	}

	/**
	 * 计算两个日期相差天数。 用第一个日期减去第二个。如果前一个日期小于后一个日期，则返回负数.
	 * 
	 * @param one -
	 *            第一个日期数，作为基准.
	 * @param two -
	 *            第二个日期数，作为比较.
	 * @return 两个日期相差天数.
	 */
	public static long getDiffDays(Date one, Date two) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(one);
		c2.setTime(two);
		int sumdays = 0;// 两个日期间隔的天数
		int start = c1.get(Calendar.YEAR) >= c2.get(Calendar.YEAR) ? c2.get(Calendar.YEAR) : c1.get(Calendar.YEAR);
		int end = c2.get(Calendar.YEAR) >= c1.get(Calendar.YEAR) ? c2.get(Calendar.YEAR) : c1.get(Calendar.YEAR);
		int flag = c1.get(Calendar.YEAR) >= c2.get(Calendar.YEAR) ? 1 : -1;
		for (int i = start; i < end; i++) {
			Calendar temp = Calendar.getInstance();
			temp.set(i, 11, 31);// 设置为当前年最后一天
			sumdays += temp.get(Calendar.DAY_OF_YEAR);
		}
		if (flag == 1) {
			sumdays += c1.get(Calendar.DAY_OF_YEAR);
			sumdays -= c2.get(Calendar.DAY_OF_YEAR);
		} else {
			sumdays -= c1.get(Calendar.DAY_OF_YEAR);
			sumdays += c2.get(Calendar.DAY_OF_YEAR);
		}
		return sumdays * flag;
	}

	/**
	 * 计算两个日期相差月份数 如果前一个日期小于后一个日期，则返回负数.
	 * 
	 * @param one -
	 *            第一个日期数，作为基准
	 * @param two -
	 *            第二个日期数，作为比较
	 * @return 两个日期相差月份数.
	 */
	public static int diffMonths(Date one, Date two) {

		Calendar calendar = Calendar.getInstance();

		// 得到第一个日期的年分和月份数
		calendar.setTime(one);
		int yearOne = calendar.get(Calendar.YEAR);
		int monthOne = calendar.get(Calendar.MONDAY);

		// 得到第二个日期的年份和月份
		calendar.setTime(two);
		int yearTwo = calendar.get(Calendar.YEAR);
		int monthTwo = calendar.get(Calendar.MONDAY);

		return (yearOne - yearTwo) * 12 + (monthOne - monthTwo);
	}
	
	/**
	 * 计算两个日期相差年数 .
	 * 
	 * @param begin -
	 *            第一个日期数
	 * @param end -
	 *            第二个日期数
	 * @return 两个日期相差年数.
	 */
	public static int diffYear(String begin, String end){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate;
		Date endDate;
		int beginYear=0;
		int endYear=0;
		try {
			beginDate = df.parse(begin);
			endDate = df.parse(end);
			beginYear = beginDate.getYear();
			endYear = endDate.getYear();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
		return endYear-beginYear; 

	}

	/**
	 * 计算两个日期之间相差多少分钟
	 * 
	 * @param one -
	 *            第一个日期数，作为基准
	 * @param two -
	 *            第二个日期数，作为比较
	 * @return 两个日期相差分钟数.
	 */
	public static long diffMinute(Date one, Date two) {
		return (one.getTime() - two.getTime()) / (60 * 1000);
	}

	/**
	 * 计算两个时间之间相差多少分钟
	 * 
	 * @param one -
	 *            第一个日期数，作为基准
	 * @param two -
	 *            第二个日期数，作为比较
	 * @return 两个时间相差分钟数.
	 */
	public static long diffMinute(String one, String two) {
		return diffMinute(parse("2013-01-01 " + one), parse("2013-01-01 " + two));
	}

	/**
	 * 计算两个日期之间相差多少毫秒
	 * 
	 * @param one -
	 *            第一个日期数，作为基准
	 * @param two -
	 *            第二个日期数，作为比较
	 * @return 两个日期相差毫秒数.
	 */
	public static long diffMilliSecend(Date one, Date two) {
		return (one.getTime() - two.getTime());
	}

	/**
	 * 将一个字符串用给定的格式转换为日期类型. <br>
	 * 注意：如果返回null，则表示解析失败.
	 * 
	 * @param datestr -
	 *            需要解析的日期字符串.
	 * @param pattern -
	 *            日期字符串的格式，默认为“yyyy-MM-dd”的形式.
	 * @return 解析后的日期
	 */
	public static Date parse(String datestr, String pattern) {
		Date date = null;
		if (null != pattern && !"".equals(pattern)) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
				date = dateFormat.parse(datestr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return date;
	}

	/**
	 * 按照默认的格式将字符串的时间转换成日期.
	 * 
	 * @param datestr -
	 *            日期字符串.
	 * @return Date
	 */
	public static Date parse(String datestr) {

		try {
			if (null != datestr && datestr.length() > 0) {
				if (datestr.length() == 19) {
					return DateUtil.parse(datestr, S_FULL_TIME_FORMAT);
				} else if (datestr.length() == 16) {
					return DateUtil.parse(datestr, "yyyy-MM-dd HH:mm");// 分钟
				} else if (datestr.length() == 13) {
					return DateUtil.parse(datestr, "yyyy-MM-dd HH");// 小时
				} else if (datestr.length() == 12) {// 分钟
					return DateUtil.parse(datestr, "yyyyMMddHHmm");
				} else if (datestr.length() == 10) {
					if (datestr.indexOf("-") > 0) {
						return DateUtil.parse(datestr, "yyyy-MM-dd");// 天
					} else {
						return DateUtil.parse(datestr, "yyyyMMddHH");// 小时
					}
				} else if (datestr.length() == 8) {// 天
					return DateUtil.parse(datestr, "yyyyMMdd");
				} else if (datestr.length() == 7) {
					return DateUtil.parse(datestr, "yyyy-MM");// 月
				} else if (datestr.length() == 6) {// 月
					return DateUtil.parse(datestr, "yyyyMM");
				} else if (datestr.length() == 4) {// 年
					return DateUtil.parse(datestr, "yyyy");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 返回本月的最后一天.
	 * 
	 * @return 本月最后一天的日期.
	 */
	public static Date getMonthLastDay() {
		return getMonthLastDay(getNow());
	}

	/**
	 * 返回给定日期中的月份中的最后一天.
	 * 
	 * @param date -
	 *            基准日期.
	 * @return 该月最后一天的日期.
	 */
	public static Date getMonthLastDay(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 将日期设置为下一月第一天
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);

		// 减去1天，得到的即本月的最后一天
		calendar.add(Calendar.DATE, -1);

		return calendar.getTime();
	}

	/**
	 * 返回给定日期中的月份中的第一天.
	 * 
	 * @param date -
	 *            基准日期.
	 * @return 该月最第一天的日期.
	 */
	public static Date getMonthFirstDay(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		// 将日期设置为该月第一天
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

		return calendar.getTime();
	}

	/**
	 * 去当前时间给要返回的时间类型得到年、月、日、小时方法的详细描述.
	 * 
	 * @param timeType -
	 *            时间类型如：Calendar.YEAR.
	 * @return
	 */
	public static int dateNum(int timeType) {
		return dateNum(null, timeType);
	}

	/**
	 * 给一个时间和要返回的时间类型得到年、月、日、小时方法的详细描述.
	 * 
	 * @param date -
	 *            时间
	 * @param timeType -
	 *            时间类型如：Calendar.YEAR.
	 * @return
	 */
	public static int dateNum(Date date, int field) {
		int t_temp = 100;
		Calendar calendar = Calendar.getInstance();

		if (null != date) {
			calendar.setTime(date);
			t_temp = calendar.get(field);
		}

		return t_temp;
	}

	/**
	 * 获得当前时间前24小时的时间.
	 * 
	 * @param format -
	 *            指定返回的时间格式.
	 * @return 时间
	 */
	public static String getRecently24InHour(String format) {
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(add(now, -24, Calendar.HOUR));
	}

	/**
	 * 格式化字符串.
	 * 
	 * @param date -
	 *            要格式的日期对象.
	 * @param style -
	 *            格式返回的格式（类似yyyy-MM-dd HH:mm:ss）.
	 * @return 字符串.
	 */
	public static String format(Date date, String style) {
		DateFormat t_dateFormat = new SimpleDateFormat(style);
		return t_dateFormat.format(date);
	}
	/**
	 * 格式化字符串.
	 * 
	 * @param dateStr -
	 *            要格式的日期字符串.
	 * @param style -
	 *            格式返回的格式（类似yyyy-MM-dd HH:mm:ss）.
	 * @return 字符串.
	 */
	public static String format(String dateStr,String style){
		DateFormat t_dateFormat = new SimpleDateFormat(style);
		return t_dateFormat.format(parse(dateStr));
		
	}

	/**
	 * 获得当前日期在本年内所在的周数
	 * 
	 * @return 周数
	 */
	public static String getNowWeekNo() {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
		dateFormatter.applyPattern("w");
		return dateFormatter.format(new Date());
	}

	/**
	 * 将毫秒转换成分钟
	 * 
	 * @return 分钟
	 */
	public static long transforMillisecondToMinute(long millisecond) {
		return millisecond / 1000 / 60;
	}

	/**
	 * 获得指定天数的毫秒数
	 * 
	 * @param days -
	 *            指定天数
	 * @return 毫秒数
	 */
	public static long getDaysMillisecond(long days) {
		return days * 24 * 60 * 1000;
	}

	/**
	 * 得到当前星期几
	 * 
	 * @return 当前星期几
	 */
	public static int getCurrentDayOfWeek() {
		return Calendar.getInstance().get(Calendar.WEDNESDAY);
	}
	
	/**
	 * 根据时间字符串判断是星期几
	 * @param day 日期字符串(yyyy-MM-dd)
	 * @return 星期几
	 */
     public static int getDayOfWeek(String day){
         SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
         int week = 0;
         try {
            Date date = dateFormatter.parse(day);
            Calendar calendar = Calendar.getInstance(Locale.CHINA);
            calendar.setTime(date);
            week = calendar.get(Calendar.DAY_OF_WEEK)-1;
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return week;
     }
	/**
	 * 格式化字符串.
	 * 
	 * @param l -
	 *            要格式的日期long对象.
	 * @param style -
	 *            格式返回的格式（类似yyyy-MM-dd HH:mm:ss）.
	 * @return
	 */
	public static String format(long l, String style) {
		Date date = new Date(l);
		DateFormat t_dateFormat = new SimpleDateFormat(style);
		return t_dateFormat.format(date);
	}

	/**
	 * 判断时间是否在指定的时间段内
	 * 
	 * @param startTime -
	 *            开始时间(HH:mm:ss)
	 * @param endTime -
	 *            结束时间(HH:mm:ss)
	 * @param searchTime -
	 *            查询时间(HH:mm:ss)
	 * @return boolean
	 */
	public static boolean betweenTime(String startTime, String endTime, String searchTime) {
		// 获取时间
		String sstartTime = startTime.substring(0, 2) + startTime.substring(3, 5) + startTime.substring(6, 8);
		String sendTime = endTime.substring(0, 2) + endTime.substring(3, 5) + endTime.substring(6, 8);
		String ssearchTime = searchTime.substring(0, 2) + searchTime.substring(3, 5) + searchTime.substring(6, 8);
		// 将系统时间转换为Int型
		int startTime_i = Integer.parseInt(sstartTime);
		int endTime_i = Integer.parseInt(sendTime);
		int searchTime_i = Integer.parseInt(ssearchTime);

		if (startTime_i <= searchTime_i && searchTime_i <= endTime_i) {
			return true;
		}

		return false;

	}

	/**
	 * 是小于被比较的时间
	 * 
	 * @param searchTime -
	 *            查询时间(HH:mm:ss)
	 * @param contrastTime -
	 *            被比较的时间(HH:mm:ss)
	 * @return
	 */
	public static boolean beforeTime(String searchTime, String contrastTime) {
		// 获取时间
		String searchTime_s = searchTime.substring(0, 2) + searchTime.substring(3, 5) + searchTime.substring(6, 8);
		String contrastTime_s = contrastTime.substring(0, 2) + contrastTime.substring(3, 5) + contrastTime.substring(6, 8);
		// 将系统时间转换为Int型
		int searchTime_i = Integer.parseInt(searchTime_s);
		int contrastTime_i = Integer.parseInt(contrastTime_s);
		searchTime_s = null;
		contrastTime_s = null;
		if (searchTime_i < contrastTime_i) {
			return true;
		}
		return false;
	}

	/**
	 * 是大于被比较的时间
	 * 
	 * @param searchTime -
	 *            查询时间(HH:mm:ss)
	 * @param contrastTime -
	 *            被比较的时间(HH:mm:ss)
	 * @return
	 */
	public static boolean behindTime(String searchTime, String contrastTime) {
		// 获取时间
		String searchTime_s = searchTime.substring(0, 2) + searchTime.substring(3, 5) + searchTime.substring(6, 8);
		String contrastTime_s = contrastTime.substring(0, 2) + contrastTime.substring(3, 5) + contrastTime.substring(6, 8);
		// 将系统时间转换为Int型
		int searchTime_i = Integer.parseInt(searchTime_s);
		int contrastTime_i = Integer.parseInt(contrastTime_s);
		searchTime_s = null;
		contrastTime_s = null;
		if (searchTime_i > contrastTime_i) {
			return true;
		}
		return false;
	}

	/**
	 * 在指定的时间基础上增加分钟数
	 * 
	 * @param time -
	 *            时间(HH:mm:ss)
	 * @return
	 */
	public static String addMinute(String time, int minute) {
		int hh = Integer.parseInt(time.substring(0, 2));
		int mm = Integer.parseInt(time.substring(3, 5));
		int ss = Integer.parseInt(time.substring(6, 8));
		if ((mm + minute) >= 60) {
			hh++;
			if (hh >= 24) {
				hh = 0;
			}
			mm = mm + minute - 60;
		} else {
			mm = mm + minute;
		}
		String shh = String.valueOf(hh);
		String smm = String.valueOf(mm);
		String sss = String.valueOf(ss);
		if (shh.length() == 1) {
			shh = "0" + shh;
		}
		if (smm.length() == 1) {
			smm = "0" + smm;
		}
		if (sss.length() == 1) {
			sss = "0" + sss;
		}
		return shh + ":" + smm + ":" + sss;
	}

	
	
	/**
	 * @description 根据时间和所要计算的分钟数进行分钟数的加减 yyyy-MM-dd HH:mm:ss
	 * @date 2013年3月7日15:47:42
	 * @param time
	 * @param minute
	 * @return
	 */
	public static String countMinutes(String time, int minute){
		Calendar cl= Calendar.getInstance();
		cl.setTime(parse(time));
		cl.add(Calendar.MINUTE, minute);
		Date date2 = cl.getTime();
		return format(date2, "HH:mm:ss");
		
	}
	
	/**
	 * 在指定的时间基础上减少分钟数
	 * 
	 * @param time -
	 *            时间(HH:mm:ss)
	 * @return
	 */
	public static String cutMinute(String time, int minute) {
		int hh = Integer.parseInt(time.substring(0, 2));
		int mm = Integer.parseInt(time.substring(3, 5));
		int ss = Integer.parseInt(time.substring(6, 8));
		if ((mm - minute) < 0) {
			hh--;
			if (hh < 0) {
				hh = 23;
			}
			mm = 60 - minute - mm;
		} else {
			mm = mm - minute;
		}
		String shh = String.valueOf(hh);
		String smm = String.valueOf(mm);
		String sss = String.valueOf(ss);
		if (shh.length() == 1) {
			shh = "0" + shh;
		}
		if (smm.length() == 1) {
			smm = "0" + smm;
		}
		if (sss.length() == 1) {
			sss = "0" + sss;
		}
		return shh + ":" + smm + ":" + sss;
	}
	/**
	 * 根据出身日期计算出实际年龄
	 */
	public static String getAgeByBirthDay(String birth){
		if(birth==null){
			return "";
		}
		Date d1=new Date();
		Date d2=DateUtil.parse(birth, "yyyy-MM-dd");
		long i=(d1.getTime() - d2.getTime())/(1000*60*60*24); 
		String age=""+i/365;
		return age;
	}
	

	/**
	 * 将字符串转化为DATE
	 * 
	 * @param dtFormat
	 *            格式yyyy-MM-dd HH:mm:ss 或 yyyy-MM-dd或 yyyy-M-dd或 yyyy-M-d或
	 *            yyyy-MM-d或 yyyy-M-dd
	 * @param def
	 *            如果格式化失败返回null
	 * @return
	 */
	public static Date fmtStrToDate(String dtFormat) {
		if (dtFormat == null)
			return null;
		try {
			if (dtFormat.length() == 9 || dtFormat.length() == 8) {
				String[] dateStr = dtFormat.split("-");
				dtFormat = dateStr[0] + (dateStr[1].length() == 1 ? "-0" : "-")
						+ dateStr[1] + (dateStr[2].length() == 1 ? "-0" : "-")
						+ dateStr[2];
			}
			if (dtFormat.length() != 10 & dtFormat.length() != 19)
				return null;
			if (dtFormat.length() == 10)
				dtFormat = dtFormat + " 00:00:00";
			SimpleDateFormat dateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			return dateFormat.parse(dtFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * Description:格式化日期,如果格式化失败返回def
	 * 
	 * @param dtFormat
	 * @param def
	 * @return
	 * @since：2008-2-15 下午05:01:37
	 */
	public static Date fmtStrToDate(String dtFormat, Date def) {
		Date d = fmtStrToDate(dtFormat);
		if (d == null)
			return def;
		return d;
	}

	public static Date fmtStrToYMDate(String dtFormat) {
		if (dtFormat == null)
			return null;
		int len = dtFormat.split("-").length;
		if (len > 2) {
			return fmtStrToDate(dtFormat);
		} else if (len < 2) {
			return null;
		} else {
			String[] dateStr = dtFormat.split("-");
			dtFormat = dateStr[0] + (dateStr[1].length() == 1 ? "-0" : "-")
					+ dateStr[1] + "-01";
			return fmtStrToDate(dtFormat);
		}
	}

	public static Date fmtStrToYMDate(String dtFormat, Date def) {
		Date d = fmtStrToYMDate(dtFormat);
		if (d == null)
			return def;
		return d;
	}

	/**
	 * 返回当日短日期型
	 * 
	 * @return
	 * @since：2008-2-15 下午05:03:13
	 */
	public static Date getToDay() {
		return toShortDate(new Date());
	}

	/**
	 * 
	 * Description:格式化日期,String字符串转化为Date
	 * 
	 * @param date
	 * @param dtFormat
	 *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd
	 * @return
	 * @since：2007-7-10 上午11:24:00
	 */
	public static String fmtDateToStr(Date date, String dtFormat) {
		if (date == null)
			return "";
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
			return dateFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Description:按指定格式 格式化日期
	 * 
	 * @param date
	 * @param dtFormat
	 * @return
	 * @since：2007-12-10 上午11:25:07
	 */
	public static Date fmtStrToDate(String date, String dtFormat) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
			return dateFormat.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String fmtDateToYMDHM(Date date) {
		return fmtDateToStr(date, "yyyy-MM-dd HH:mm");
	}

	public static String fmtDateToYMD(Date date) {
		return fmtDateToStr(date, "yyyy-MM-dd");
	}

	public static String fmtDateToYM(Date date) {
		return fmtDateToStr(date, "yyyy-MM");
	}

	public static String fmtDateToM(Date date) {
		return fmtDateToStr(date, "MM");
	}

	public static String fmtDateToMD(Date date) {
		return fmtDateToStr(date, "MM-dd");
	}

	// 例：n=-2即获取前天的日期
	public static String getDate(int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, n);
		return sdf.format(cal.getTime());
	}

	/**
	 * 获取当前数据库系统日期(8位长整型)，是从数据库系统参数表中查询获得
	 * 
	 * @return (long) 当前数据库系统时间 格式为 "年月日" ，20091202
	 */
//	public static long GetDBSysDate() {
//		long SysDate = 0;
//		String sql = "from ComSysParm order by sysDate DESC";
//		DaoFactory mydao = new DaoFactory();
//		List list = mydao.query(sql, null);
//		ComSysParm com_sys_parm = new ComSysParm();
//		if (list.size() == 0) {
//			System.out.println("系统日期获取出错，请检查数据库公共参数表com_sys_parm！");
//			return 0;
//		}
//		com_sys_parm = (ComSysParm) list.get(0);
//		SysDate = com_sys_parm.getSysDate();
//		return SysDate;
//	}

	/**
	 * 
	 * Description:只保留日期中的年月日
	 * 
	 * @param date
	 * @return
	 * @since：2007-12-10 上午11:25:50
	 */
	public static Date toShortDate(Date date) {
		String strD = fmtDateToStr(date, "yyyy-MM-dd");
		return fmtStrToDate(strD);
	}

	/**
	 * 
	 * Description:只保留日期中的年月日
	 * 
	 * @param date格式要求yyyy-MM-dd
	 * @return
	 * @since：2007-12-10 上午11:26:12
	 */
	public static Date toShortDate(String date) {
		if (date != null && date.length() >= 10) {
			return fmtStrToDate(date.substring(0, 10));
		} else
			return fmtStrToDate(date);
	}

	/**
	 * 求对日
	 * 
	 * @param countMonth
	 *            :月份的个数(几个月)
	 * @param flag
	 *            :true 求前countMonth个月的对日:false 求下countMonth个月的对日
	 * @return
	 */
	public static Date getCounterglow(int countMonth, boolean before) {
		Calendar ca = Calendar.getInstance();
		return getCounterglow(ca.getTime(), before ? -countMonth : countMonth);
	}

	/**
	 * 
	 * Description: 求对日 加月用+ 减月用-
	 * 
	 * @param date
	 * @param countMonth
	 * @return
	 * @since：2007-12-13 下午03:16:47
	 */
	public static Date getCounterglow(Date date, int num) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.MONTH, num);
		return ca.getTime();
	}

	/**
	 * 
	 * Description:加一天
	 * 
	 * @param date
	 * @return
	 * @since：2007-12-13 下午02:57:38
	 */
	public static Date addDay(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		cd.add(Calendar.DAY_OF_YEAR, 1);
		return cd.getTime();
	}

	/**
	 * 
	 * Description:判断一个日期是否为工作日(非周六周日)
	 * 
	 * @param date
	 * @return
	 * @since：2007-12-13 下午03:01:35
	 */
	public static boolean isWorkDay(Date date) {
		Calendar cd = Calendar.getInstance();
		cd.setTime(date);
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek != Calendar.SUNDAY || dayOfWeek != Calendar.SATURDAY)
			return false;
		return true;
	}

	/**
	 * 
	 * Description:取一个月的最后一天
	 * 
	 * @param date1
	 * @return
	 * @since：2007-12-13 下午03:28:21
	 */
	public static Date getLastDayOfMonth(Date date1) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.set(Calendar.DAY_OF_MONTH, 1);
		date.add(Calendar.MONTH, 1);
		date.add(Calendar.DAY_OF_YEAR, -1);
		return toShortDate(date.getTime());
	}

	/**
	 * 求开始截至日期之间的天数差.
	 * 
	 * @param d1
	 *            开始日期
	 * @param d2
	 *            截至日期
	 * @return 返回相差天数
	 */
	public static int getDaysInterval(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return 0;
		Date[] d = new Date[2];
		d[0] = d1;
		d[1] = d2;
		Calendar[] cal = new Calendar[2];
		for (int i = 0; i < cal.length; i++) {
			cal[i] = Calendar.getInstance();
			cal[i].setTime(d[i]);
			cal[i].set(Calendar.HOUR_OF_DAY, 0);
			cal[i].set(Calendar.MINUTE, 0);
			cal[i].set(Calendar.SECOND, 0);
		}
		long m = cal[0].getTime().getTime();
		long n = cal[1].getTime().getTime();
		int ret = (int) Math.abs((m - n) / 1000 / 3600 / 24);
		return ret;
	}

	public static String getDayOfWeek(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		return "周" + toChNumber(cl.get(Calendar.DAY_OF_WEEK) - 1);
	}

	/**
	 * 将数字转为中文。 "0123456789"->"〇一二三四五六七八九"
	 * 
	 * @param num
	 *            长度为1,'0'-'9'的字符串
	 * @return
	 */
	private static String toChNumber(int num) {
		final String str = "〇一二三四五六七八九";
		return str.substring(num, num + 1);
	}

	/**
	 * 
	 * Description:指定日期加或减days天
	 * 
	 * @param date1日期
	 * @param days天数
	 * @return
	 * @since：2007-12-17 下午03:47:12
	 */
	public static Date addDay(Date date1, int days) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.DAY_OF_YEAR, days);
		return toShortDate(date.getTime());
	}

	/**
	 * 
	 * Description:指定日期加或减months月
	 * 
	 * @param date1
	 * @param months
	 * @return
	 * @since：2008-3-5 下午05:17:26
	 */
	public static Date addMonth(Date date1, int months) {
		Calendar date = Calendar.getInstance();
		date.setTime(date1);
		date.add(Calendar.MONTH, months);
		return date.getTime();
	}

	Calendar CurCalendar = null;

	public DateUtil() {
		CurCalendar = Calendar.getInstance();
		Date time = new Date();
		CurCalendar.setTime(time);
	}

	public void ReSetCalendar() {
		Date time = new Date();
		CurCalendar.setTime(time);
	}

	public String RelativeDate(String datetimeStr, int n) {
		int iCurYear, iCurMonth, iCurDay, iCurHour;
		String TimeStr, strCurMonth, strCurDay, strCurHour;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date cDate = new Date();
		try {
			cDate = df.parse(datetimeStr); // 时间格式化
		} catch (ParseException e) {
		}
		Date newTime = new Date(cDate.getTime());
		CurCalendar.setTime(newTime); // 设置新的时间
		CurCalendar.add(Calendar.DAY_OF_YEAR, n);
		iCurYear = CurCalendar.get(Calendar.YEAR);
		iCurMonth = CurCalendar.get(Calendar.MONTH) + 1;
		if (iCurMonth < 10) {
			strCurMonth = "0" + Integer.toString(iCurMonth);
		} else {
			strCurMonth = Integer.toString(iCurMonth);
		}
		iCurDay = CurCalendar.get(Calendar.DAY_OF_MONTH);
		if (iCurDay < 10) {
			strCurDay = "0" + Integer.toString(iCurDay);
		} else {
			strCurDay = Integer.toString(iCurDay);
		}
		TimeStr = Integer.toString(iCurYear) + "-" + strCurMonth + "-"
				+ strCurDay;
		ReSetCalendar();
		return TimeStr;
	}

	//是否为闰年
	public static boolean isLeapYear(int year) {
		if ((year % 4 == 0) && ((year % 100 != 0) | (year % 400 == 0))) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 获取当前系统时间
	 * @return  (String)  当前系统时间 格式为 "年月日"，如 "20091202"
	 */
	public static String getCurrentDay_ftp(){
		
		Calendar ca = Calendar.getInstance();
	    int year = ca.get(Calendar.YEAR);//获取年份
	    String Stryear=String.valueOf(year);
	    
	    int month=ca.get(Calendar.MONTH)+1;//获取月份  月份系统从0开始算起的
	    String Strmonth=String.valueOf(month);
	    if(month<10){
	    	Strmonth="0"+Strmonth;
	    }
	    
	    int day=ca.get(Calendar.DATE);//获取日
	    String Strday=String.valueOf(day);
	    if(day<10){
	    	Strday="0"+Strday;
	    }
	    
	    String currentDay=Stryear+Strmonth+Strday;
	    return currentDay;
	}
	/**
	 * 获取当前系统时间
	 * @return  (String)  当前系统时间 格式为 "时分秒"，如 "120154"
	 */
	public static String getCurrentTime(){
		
		Calendar ca = Calendar.getInstance();
		int hour=ca.get(Calendar.HOUR_OF_DAY);//小时
	    String Strhour=String.valueOf(hour);
	    if(hour<10){
	    	Strhour="0"+Strhour;
	    }
	    
	    int minute=ca.get(Calendar.MINUTE);//分
	    String Strminute=String.valueOf(minute);
	    if(minute<10){
	    	Strminute="0"+Strminute;
	    }
	    
	    int second=ca.get(Calendar.SECOND);//秒    
	    String Strsecond=String.valueOf(second);
	    if(second<10){
	    	Strsecond="0"+Strsecond;
	    }
	    
	    String CurrentTime=Strhour+Strminute+Strsecond;
	    return CurrentTime;
	}
}
