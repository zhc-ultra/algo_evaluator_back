package com.zhc.aeoj.judge.codesandbox.impl;

import com.zhc.aeoj.judge.codesandbox.CodeSandBox;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author zhc
 * @description
 * @date 2024/5/24 10:33
 **/
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("第三方沙箱");
        return null;
    }
}
