package com.zhc.aeoj.judge.codesandbox.model;

import lombok.Data;

/**
 * @author zhc
 * @description 判题信息
 * @date 2024/5/21 14:50
 **/
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 消耗内存 kb
     */
    private Long memory;
    /**
     * 消耗时间 ms
     */
    private Long time;
}
