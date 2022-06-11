package top.yms.mas.utils;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    private static final String PATTERN1 = "yyyy-MM-dd";
    private static final String PATTERN2 = "yyyy-MM-dd HH:mm";
    private static final String PATTERN3 = "yyyy-MM";
    private static final String PATTERN4 = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN5 = "yyyy.MM.dd HH:mm:ss";
    private static final String PATTERN6 = "yyyy年MM月dd日HH时";

    private final static int [] MONTH = {0,31,28,31,30,31,30,31,31,30,31,30,31};


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateTime(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static void main(String[] args) {
        String str = "2018-04-02 00:00:00";
        System.out.println(strToDateTime(str));
    }


    public static String getDateStr(Date date) {
        if (date == null) return " ";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN4);
        String timeStampStr = simpleDateFormat.format(date);
        return timeStampStr;
    }


    private static String match(String pattern) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String timeStampStr = simpleDateFormat.format(date);
        return timeStampStr;
    }

    public static String getYYYY_MM_DD() {
        return match(PATTERN1);
    }

    public static String getYYYY_MM_DD_HH_MM() {
        return match(PATTERN2);
    }

    public static String getYYYY_MM() {
        return match(PATTERN3);
    }

    public static String
    getYYYY_MM_DD_HH_MM_SS() {
        return match(PATTERN4);
    }

    /***
     * get yyyy.MM.dd HH:mm:ss
     * @returna
     */
    public static String getYYYY_MM_DD_HH_MM_SS_() {
        return match(PATTERN5);
    }

    /***
     * get yyyy年MM月dd日HH时
     * @return
     */
    public static String getYYYY_MM_DD_HH_MM_SS__() {
        return match(PATTERN6);
    }

    /****************************Calendar***********************************************/

    private static Calendar CALR = Calendar.getInstance();

    public static String getYear() {
        Integer year = CALR.get(Calendar.YEAR);
        return year.toString();
    }

    /**
     *  获取某年某月的实际总 共天数
     * 如果month数据异常 返回 -1
     * @param year
     * @param month
     * @return
     */
    public static int getDays(int year, int month) {
        if (month <1 || month > 12) return -1;

        if (year %4 == 0 || year%400 ==0) {
            return 29;
        }
        return MONTH[month];
    }

}


