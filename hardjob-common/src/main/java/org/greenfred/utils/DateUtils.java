package org.greenfred.utils;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DateUtils {
    private static final Object lockObject = new Object();
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new ConcurrentHashMap<>();
    private static SimpleDateFormat getSdf(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocal = sdfMap.get(pattern);
        if (threadLocal == null) {
            synchronized (lockObject) {
                threadLocal = sdfMap.get(pattern);
                if (threadLocal == null) {
                    threadLocal = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, threadLocal);
                }
            }
        }
        return threadLocal.get();
    }

    public static String format(Date date, String pattern) {
        return getSdf(pattern).format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        try {
            return getSdf(pattern).parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
