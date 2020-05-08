package com.example.tinymall.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @ClassName MathUtils
 * @Description
 * @Author jzf
 * @Date 2020-4-23 10:23
 */
public class MathUtils {
    /**
     * @return a+b
     */
    public static <T extends Number> Double add(T a, T b) {
        BigDecimal x = new BigDecimal(String.valueOf(a));
        BigDecimal y = new BigDecimal(String.valueOf(b));
        return x.add(y).doubleValue();
    }

    /**
     * @return a-b
     */
    public static <T extends Number> Double subtract(T a, T b) {
        BigDecimal x = new BigDecimal(String.valueOf(a));
        BigDecimal y = new BigDecimal(String.valueOf(b));
        return x.subtract(y).doubleValue();
    }

    /**
     * @return a*b
     */
    public static <T extends Number> Double multiply(T a, T b) {
        BigDecimal x = new BigDecimal(String.valueOf(a));
        BigDecimal y = new BigDecimal(String.valueOf(b));
        return x.multiply(y).doubleValue();
    }

    /**
     * 保留2位小数(四舍五入)
     *
     * @return a/b
     */
    public static <T extends Number> Double divide(T a, T b) {
        return divide(a, b, 2);
    }

    /**
     * @param scale 保留几位小数(四舍五入)
     * @return a/b
     */
    public static <T extends Number> Double divide(T a, T b, int scale) {
        BigDecimal x = new BigDecimal(String.valueOf(a));
        BigDecimal y = new BigDecimal(String.valueOf(b));
        return x.divide(y, scale, RoundingMode.HALF_UP).doubleValue();
    }
}
