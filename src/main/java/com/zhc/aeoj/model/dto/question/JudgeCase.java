package com.zhc.aeoj.model.dto.question;

import lombok.Data;

/**
 * @author zhc
 * @description 判题用例
 * @date 2024/5/21 14:50
 **/
@Data
public class JudgeCase {
    /**
     * 用例输入
     */
    private String input;
    /**
     * 用例输出
     */
    private String output;
}
