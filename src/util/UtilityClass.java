package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class UtilityClass {


    /**
     * To get the current day of the month
     * @return will return the day of the month
     */
    public static int CurrentDay() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * To get current month of the year
     * @return will return the month of the year
     */
    public static int CurrentMonth() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return (cal.get(Calendar.MONTH) + 1);
    }

    /**
     * To fet the current running year
     * @return will return the current year
     */
    public static int CurrentYear() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        return cal.get(Calendar.YEAR);
    }

    /**
     * To check if course is past or current
     * @param course whole string of course which we get from file
     * @return will return true if end date of course is in past otherwise false
     */
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

    /**
     * It is used to check the date validation<br>
     *     To check if date is bigger than start date.
     *     date is valid and belongs to future
     * @param startDate start date in dd-MM-yyyy format
     * @param endDate end date in dd-MM-yyyy format
     * @return will return the validation message based on start and end date
     */
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

    /**
     * It is used to check the date format of passed date
     * @param date date value which needs to be validated
     * @return will return true if matches the date format otherwise false
     */
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

    /**
     * This is used to generate unique id based on current time
     * @return return the generated id
     */
    public static String generateUniqueId() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }


    public static void printConsole(String msg) {
        System.out.println(msg);
    }

    public static boolean isContainDigit(String str)
    {
        if(str.matches("[0-9]+") && str.length() > 0) {
            return true;
        }
        return false;
    }
}


// https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java
