package com.zhc.aeoj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhc.aeoj.model.dto.post.PostQueryRequest;
import com.zhc.aeoj.model.entity.Post;
import com.zhc.aeoj.model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子服务
 */
public interface PostService extends IService<Post> {

    /**
     * 校验
     */
    void validPost(Post post, boolean add);

    /**
     * 获取查询条件
     */
    QueryWrapper<Post> getQueryWrapper(PostQueryRequest postQueryRequest);

    /**
     * 从 ES 查询
     */
    Page<Post> searchFromEs(PostQueryRequest postQueryRequest);

    /**
     * 获取帖子封装
     */
    PostVO getPostVO(Post post, HttpServletRequest request);

    /**
     * 分页获取帖子封装
     */
    Page<PostVO> getPostVOPage(Page<Post> postPage, HttpServletRequest request);
}
