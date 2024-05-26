package com.zhc.aeoj.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.zhc.aeoj.common.ErrorCode;
import com.zhc.aeoj.exception.BusinessException;
import com.zhc.aeoj.judge.codesandbox.CodeSandBox;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @author zhc
 * @description 远程代码沙箱
 * @date 2024/5/24 10:32
 **/
public class RemoteCodeSandBox implements CodeSandBox {
    /**
     * 定义鉴权请求头，和秘钥 保证接口安全性
     */
    private static final String AUTH_REQUEST_HEADER = "auth";

    // 使用字符创进行MD5 加密后再进行传输
    // BASE64 后 MD5
    private static final String AUTH_REQUEST_SECRET = "fda80ec5306e44a3489562f105d74527";
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = "http://127.0.0.1:3579/executeCode";
        String jsonRequest = JSONUtil.toJsonStr(executeCodeRequest);
        String response = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(jsonRequest)
                .execute()
                .body();
        if (StrUtil.isBlank(response)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "remote code sand box invoke failed, message: " + response);
        }
        return JSONUtil.toBean(response, ExecuteCodeResponse.class);
    }
}
