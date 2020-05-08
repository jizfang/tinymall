package com.example.tinymall.core.utils;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.enums.ResultCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName AssertUtils
 * @Description 断言工具，替代if判断
 * @Author jzf
 * @Date 2020-5-8 15:14
 */
public class AssertUtils {
    /**
     * 断言一个条件为True, 如果条件为False就抛bizCode对应的异常
     *
     * @param expression
     * @param bizCode
     */
    public static void isTrue(boolean expression, ResultCode bizCode) {
        if (!expression) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个条件为False, 如果条件为True就抛bizCode对应的异常
     *
     * @param expression
     * @param bizCode
     */
    public static void isFalse(boolean expression, ResultCode bizCode) {
        if (expression) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个对象为空, 如果非空就抛bizCode对应的异常
     *
     * @param object
     * @param bizCode
     */
    public static void isNull(Object object, ResultCode bizCode) {
        if (object != null) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个对象不为空, 如果空就抛bizCode对应的异常
     *
     * @param object
     * @param bizCode
     */
    public static void notNull(Object object, ResultCode bizCode) {
        if (object == null) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个字符串为空, 如果不为空就抛bizCode对应的异常
     *
     * @param content
     * @param bizCode
     */
    public static void isBlank(String content, ResultCode bizCode) {
        if (StringUtils.isNotBlank(content)) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个字符串不为空, 如果为空就抛bizCode对应的异常
     *
     * @param content
     * @param bizCode
     */
    public static void notBlank(String content, ResultCode bizCode) {
        if (StringUtils.isBlank(content)) {
            throw new BusinessException(bizCode);
        }
    }

    /**
     * 断言一个条件为True, 如果条件为False就抛bizMsg
     *
     * @param expression
     * @param bizMsg
     */
    public static void isTrue(boolean expression, String bizMsg) {
        if (!expression) {
            throw new BusinessException(bizMsg);
        }
    }

    /**
     * 断言一个条件为False, 如果条件为True就抛bizMsg
     *
     * @param expression
     * @param bizMsg
     */
    public static void isFalse(boolean expression, String bizMsg) {
        if (expression) {
            throw new BusinessException(bizMsg);
        }
    }

    /**
     * 断言一个对象为空, 如果非空就抛bizMsg
     *
     * @param object
     * @param bizMsg
     */
    public static void isNull(Object object, String bizMsg) {
        if (object != null) {
            throw new BusinessException(bizMsg);
        }
    }

    /**
     * 断言一个对象不为空, 如果空就抛bizMsg
     *
     * @param object
     * @param bizMsg
     */
    public static void notNull(Object object, String bizMsg) {
        if (object == null) {
            throw new BusinessException(bizMsg);
        }
    }

    /**
     * 断言一个字符串为空, 如果不为空就抛bizMsg
     *
     * @param content
     * @param bizMsg
     */
    public static void isBlank(String content, String bizMsg) {
        if (StringUtils.isNotBlank(content)) {
            throw new BusinessException(bizMsg);
        }
    }

    /**
     * 断言一个字符串不为空, 如果为空就抛bizMsg
     *
     * @param content
     * @param bizMsg
     */
    public static void notBlank(String content, String bizMsg) {
        if (StringUtils.isBlank(content)) {
            throw new BusinessException(bizMsg);
        }
    }
}
