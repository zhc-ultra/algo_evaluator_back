package com.zhc.aeoj.model.dto.question;

import lombok.Data;

/**
 * @author zhc
 * @description 判题配置
 * @date 2024/5/21 14:50
 **/
@Data
public class JudgeConfig {
    /**
     * 时间限制 ms
     */
    private Long timeLimit;
    /**
     * 内存限制 kb
     */
    private Long memoryLimit;
    /**
     * 堆栈限制 kb
     */
    private Long stackLimit;
}
