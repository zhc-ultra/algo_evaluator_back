package com.zhc.aeoj.judge.codesandbox;

import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author zhc
 * @description 代码沙箱
 * @date 2024/5/24 10:24
 **/
public interface CodeSandBox {
    /**
     * 执行代码
     *
     * @param executeCodeRequest 执行代码请求
     * @return 执行代码响应
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
