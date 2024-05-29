package com.zhc.aeoj.model.enums;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题信息枚举
 */
public enum JudgeInfoMessageEnum {
    ACCEPTED("成功", "Accepted"),
    WRONG_ANSWER("答案错误", "Wrong Answer"),
    COMPILE_ERROR("编译错误", "Compile Error"),
    MEMORY_LIMIT_EXCEEDED("内存超限", "Memory Limit Exceeded"),
    TIME_LIMIT_EXCEEDED("时间超限", "Time Limit Exceeded"),
    PRESS_LIMIT_EXCEEDED("展示错误", "PressLimitExceeded"),
    WAITING("等待判题", "waiting judge"),
    OUTPUT_LIMIT_EXCEEDED("输出超限制", "OutputLimitExceeded"),
    DANGEROUS_OPERATION("危险操作", "DangerousOperation"),
    RUNTIME_ERROR("运行错误", "RuntimeError"),
    SYSTEM_ERROR("系统错误", "SystemError");
    private final String text;

    private final String value;

    JudgeInfoMessageEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     */
    public static JudgeInfoMessageEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (JudgeInfoMessageEnum anEnum : JudgeInfoMessageEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
