package com.zhc.aeoj.judge.strategy;

import com.zhc.aeoj.judge.codesandbox.model.JudgeInfo;

/**
 * @author zhc
 * @description 判题策略
 * @date 2024/5/24 11:43
 **/
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);
}