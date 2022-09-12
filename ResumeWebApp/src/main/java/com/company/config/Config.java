package com.company.config;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Config {
    public static Date parseDate(String date){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date udob = sdf.parse(date);
            long l = udob.getTime();
            java.sql.Date sdob = new java.sql.Date(l);
            return sdob;
        }catch (ParseException ex){
            ex.printStackTrace();
        }
        return null;
    }
}
