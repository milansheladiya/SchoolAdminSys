package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class UtilityClass {


    public static int CurrentDay() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int CurrentMonth() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return (cal.get(Calendar.MONTH) + 1);
    }

    public static int CurrentYear() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.YEAR);
    }

    public static boolean isPastCourse(String course) {
        String[] courseStringArr = course.split(":");
        String endDate = courseStringArr[3];
        String[] EDate = endDate.split("-"); // date format : DD-MM-YYYY;
        if (Integer.parseInt(EDate[0]) <= CurrentDay() && Integer.parseInt(EDate[1]) <= CurrentMonth()
                && Integer.parseInt(EDate[2]) <= CurrentYear()) {
            return true;
        }
        return false;
    }

    public static String dateRangeCheck(String startDate, String endDate) {
        try {
            Date current = new Date();
            String myFormatString = "dd-MM-yyyy";
            SimpleDateFormat df = new SimpleDateFormat(myFormatString);
            Date sDate = df.parse(startDate);
            Date eDate = df.parse(endDate);

            Long sl = sDate.getTime();
            Long el = eDate.getTime();

            Date sNext = new Date(sl);
            Date eNext = new Date(el);
            //compare both dates
            if (sNext.after(current) && eNext.after(current)) {
                if (eNext.after(sNext)) {
                    return "Confirm";
                } else {
                    return "End date must be bigger than start date";
                }
            } else {
                return "Date must be belongs to future";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "Confirm";
    }

    public static boolean dateFormatCheck(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            sdf.parse(date);
            sdf.setLenient(false);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String generateUniqueId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


    public static void printConsole(String msg) {
        System.out.println(msg);
    }

}


// https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
