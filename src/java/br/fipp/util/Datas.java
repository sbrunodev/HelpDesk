package br.fipp.util;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
public final class Datas {

    private static DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));

    public Datas() {
    }

    public static String dateToString(Date data) {
        return data == null ? null : df.format(data);
    }

    public static Date stringToDate(String data) {
        try 
        {
            return df.parse(data);
        } 
        catch (ParseException ex) {
            return null;
        }
    }
}
