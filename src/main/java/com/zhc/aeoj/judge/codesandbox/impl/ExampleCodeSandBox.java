package com.zhc.aeoj.judge.codesandbox.impl;

import com.zhc.aeoj.judge.codesandbox.CodeSandBox;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhc.aeoj.judge.codesandbox.model.JudgeInfo;
import com.zhc.aeoj.model.enums.JudgeInfoMessageEnum;
import com.zhc.aeoj.model.enums.QuestionSubmitStateEnum;

import java.util.List;

/**
 * @author zhc
 * @description 示例代码沙箱
 * @date 2024/5/24 10:32
 **/

public class ExampleCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> inputList = executeCodeRequest.getInputList();
//        String code = executeCodeRequest.getCode();
//        String language = executeCodeRequest.getLanguage();

        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutputList(inputList);
        executeCodeResponse.setMessage("测试执行成功");
        executeCodeResponse.setStatus(QuestionSubmitStateEnum.SUCCEED.getValue());

        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);

        executeCodeResponse.setJudgeInfo(judgeInfo);

        return executeCodeResponse;
    }
}
