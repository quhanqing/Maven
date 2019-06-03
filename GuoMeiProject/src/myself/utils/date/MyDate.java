package myself.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

 public class MyDate {
     /**
      * 获取当前日期
      * @return
      */
     public static String getCurrentDate(){
         Calendar calendar=Calendar.getInstance();
         calendar.setTime(new Date());
         Date d=calendar.getTime();
         SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date=simpleDateFormat.format(d);
         return date;
     }

     /**
      * 获取昨天的日期
      * @return
      */
     public static String getYesterDay(){
         int days = -1;
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.add(Calendar.DAY_OF_MONTH, days);
         Date d = calendar.getTime();
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         String date = df.format(d);
         System.out.println(date);
         return date;
     }

     /**
      * 获取给定日期所在周的周一的日期
      * @param date
      * @return
      */
     public static String getMondayOfWeek(String date) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar cal = Calendar.getInstance();
         try {
             cal.setTime(df.parse(date));//将字符串解析为date类型
         } catch (ParseException e) {
             e.printStackTrace();
         }
         int d = 0;

         //若date本身为周日，则要取的时间范围为周一到周日的数据
         if(cal.get(Calendar.DAY_OF_WEEK) == 1){//周日 的值为1
             d = -6;
         }else{
             d = 2-cal.get(Calendar.DAY_OF_WEEK);
         }
//	cal.add(Calendar.DAY_OF_WEEK, d);
         cal.add(Calendar.DAY_OF_MONTH,d);
         String monday = df.format(cal.getTime());
         System.out.println(monday);
         return monday;
     }

     /**
      * 获取给定日期所在月的第一天
      * @param date
      * @return
      */
     public static String getFirstayOFMonth(String date) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar cal = Calendar.getInstance();
         try {
             cal.setTime(df.parse(date));
         } catch (ParseException e) {
             e.printStackTrace();
         }

         //设置到当月1号
         cal.set(Calendar.DATE,1);
         String firstDay = df.format(cal.getTime());
         System.out.println(firstDay);
         return firstDay;
     }

     /**
      * 判断给定日期是否为所在月的最后一天
      * @param date
      * @return
      */
     public static boolean isLastDayOfMonth(String date) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Calendar calendar = Calendar.getInstance();
         try {
             calendar.setTime(df.parse(date));
         } catch (ParseException e) {
             e.printStackTrace();
         }
         calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1)); //在当前日期上加1，得到第二天，然后判断第二天是不是1号
         if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
             return true;
         }
         return false;
     }

}

