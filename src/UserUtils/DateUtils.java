package UserUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {
    /**
     *
     * **/
    public static java.sql.Date utilToSql(java.util.Date d1){
        return new java.sql.Date(d1.getTime());
    }
    public static java.util.Date sqlTOUtil(java.sql.Date d2){
        return new java.util.Date(d2.getTime());

    }
    public static java.util.Date stringTOUtil(String sd) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sd);

    }
    public static String utilTOString(java.util.Date d1) throws ParseException{
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(d1);
    }
}
