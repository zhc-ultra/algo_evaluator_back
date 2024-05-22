package com.zhc.aeoj.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhc.aeoj.common.ErrorCode;
import com.zhc.aeoj.constant.CommonConstant;
import com.zhc.aeoj.exception.BusinessException;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitAddRequest;
import com.zhc.aeoj.model.dto.questionsubmit.QuestionSubnmitQuertRequest;
import com.zhc.aeoj.model.entity.Question;
import com.zhc.aeoj.model.entity.QuestionSubmit;
import com.zhc.aeoj.model.entity.User;
import com.zhc.aeoj.model.enums.QuestionSubmitLanguageEnum;
import com.zhc.aeoj.model.enums.QuestionSubmitStateEnum;
import com.zhc.aeoj.model.vo.QuestionSubmitVO;
import com.zhc.aeoj.service.QuestionService;
import com.zhc.aeoj.service.QuestionSubmitService;
import com.zhc.aeoj.mapper.QuestionSubmitMapper;
import com.zhc.aeoj.service.UserService;
import com.zhc.aeoj.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yinger
 * @description 针对表【question_submit(题目提交)】的数据库操作Service实现
 * @createDate 2024-05-21 09:29:37
 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;

    /**
     * 题目提交
     * @return 提交记录的id
     */
    @Override
    public long doQuestionSubmit(QuestionSubnmitAddRequest questionSubnmitAddRequest, User loginUser) {
        long questionId = questionSubnmitAddRequest.getQuestionId();

        String language = questionSubnmitAddRequest.getLanguage();
        QuestionSubmitLanguageEnum languageEnum = QuestionSubmitLanguageEnum.getEnumByValue(language);
        if (languageEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "编程语言错误");
        }


        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 是否已题目提交
        long userId = loginUser.getId();

        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUserId(userId);
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setCode(questionSubnmitAddRequest.getCode());
        questionSubmit.setLanguage(language);
        // 设置初始状态 TODO 加入枚举值
        questionSubmit.setStatus(QuestionSubmitStateEnum.WAITING.getValue());

        questionSubmit.setJudgeInfo("{}");
        boolean result = this.save(questionSubmit);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据插入异常");
        }

        return questionSubmit.getId();
    }


    /**
     * 获取查询包装类，根据前端请求对象，得到mybatis支持的查询对象
     */
    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubnmitQuertRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }

        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        Long userId = questionSubmitQueryRequest.getUserId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        // 拼接查询条件
        queryWrapper.eq(StringUtils.isNotBlank(language), "language", language);
        queryWrapper.eq(QuestionSubmitStateEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);

        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        long userId = loginUser.getId();
        // 数据脱敏
        if (userId != questionSubmit.getUserId() && !userService.isAdmin(loginUser)) {
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollUtil.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream().map(
                questionSubmit -> getQuestionSubmitVO(questionSubmit, loginUser)
        ).collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }

}




