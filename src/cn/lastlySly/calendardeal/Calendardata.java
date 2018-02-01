package cn.lastlySly.calendardeal;

import java.util.Calendar;

/**
 * 日历逻辑
 * @author lastlySly
 * @GitHub https://github.com/lastlySly
 * @create 2016-05-31 21:48
 **/
public class Calendardata {

    String day[];
    Calendar calendar=Calendar.getInstance();
    int day1=calendar.get(Calendar.DAY_OF_MONTH);

    int year=calendar.get(Calendar.YEAR),month=calendar.get(Calendar.MONTH)+1;
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setDay(int day) {
        this.day1 = day;
    }
    public int getDay() {
        return day1;
    }
    public String[] getCalendar(){

        String a[]=new String[42];

        calendar.set(year, month-1,1);
        int 星期几=calendar.get(Calendar.DAY_OF_WEEK)-1;
        int day=0;
        if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
            day=31;
        }
        if(month==4 || month==6 || month==9 || month==11){
            day=30;
        }
        if(month==2){
            if((year%4==0 && year%100!=0) || year%400==0){
                day=29;
            }else{
                day=28;
            }
        }
        for(int i=星期几,n=1;i<星期几+day;i++){
            a[i]=String.valueOf(n);
            n++;
        }
        return a;
    }
}
