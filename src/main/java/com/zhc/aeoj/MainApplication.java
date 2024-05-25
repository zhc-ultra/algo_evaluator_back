package com.zhc.aeoj;

import com.zhc.aeoj.judge.codesandbox.CodeSandBox;
import com.zhc.aeoj.judge.codesandbox.CodeSandBoxFactory;
import com.zhc.aeoj.judge.codesandbox.CodeSandBoxProxy;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeRequest;
import com.zhc.aeoj.judge.codesandbox.model.ExecuteCodeResponse;
import com.zhc.aeoj.model.enums.QuestionSubmitLanguageEnum;
import com.zhc.aeoj.utils.SpringContextUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

/**
 * 主类（项目启动入口）
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@MapperScan("com.zhc.aeoj.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class MainApplication {
    @Value("${codesandbox.type:example}")
    String type;

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MainApplication.class, args);
        SpringContextUtils scu = new SpringContextUtils();
        scu.setApplicationContext(applicationContext);
    }
}