package com.utils;

import com.bean.Bill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class test {

    public static String dateToWeek(Date datetime) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周一","周二","周三","周四","周五","周六","周天"};
        Calendar cal = Calendar.getInstance();
        Date datet = null;
        datet = datetime;
        cal.setTime(datet);
        int w = cal.get(Calendar.DAY_OF_WEEK)-2;
        if(w<0){
            w=0;
        }
        return weekDays[w];
    }

    public static class sortByTime implements Comparator{

        @Override
        public int compare(Object o1, Object o2) {
            Bill s1 = (Bill) o1;
            Bill s2 = (Bill) o2;
            return s1.getTime().compareTo(s2.getTime());
        }
    }
    /*public static void main(String[] args) throws ParseException {
        test ts = new test();
        System.out.printf(ts.dateToWeek(new Date()));
    }*/
}
