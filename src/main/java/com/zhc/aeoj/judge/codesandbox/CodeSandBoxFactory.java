package com.zhc.aeoj.judge.codesandbox;

import com.zhc.aeoj.judge.codesandbox.impl.ExampleCodeSandBox;
import com.zhc.aeoj.judge.codesandbox.impl.RemoteCodeSandBox;
import com.zhc.aeoj.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * @author zhc
 * @description 代码沙箱工厂(更具字符串参数创建指定的代码沙箱示例)
 * @date 2024/5/24 10:39
 **/
public class CodeSandBoxFactory {
    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }

}
