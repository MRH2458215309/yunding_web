package com.yundingshuyuan.website.service;

import com.yundingshuyuan.website.entity.Article;
import com.yundingshuyuan.website.service.support.IBaseService;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Mr.h
 */
public interface ArticleService extends IBaseService<Article,Integer> {

    /**
     * 查询文章:大家谈，技术流，思想流，学员心得
     * @parama article
     */
    List<Article> selectArticle1(Article article);

    /**
     * 查询文章:云顶院，极客团，创客团
     * @param article
     * @return
     */
    List<Article> selectArticle2(Article article);

    /**
     * 查询文章：轮播图
     * @param article
     * @return
     */
    Page<Article> selectArticle3(Article article, int pageNo, int pageSize);


    /**
     * 文章上传
     * @param article
     * @return
     */
    void articleInsert(Article article, String realPath);


    /**
     * 文章更新（不包含图片）
     * @param article
     */
    void articleUpdate(Article article);


    /**
     * 文章更新（包含图片）
     * @param article
     * @param realPath
     */
    void articleUpdateWithImage(Article article, String realPath);

    /**
     * 文章删除
     * @param article
     */
    void updateLabel(Article article);
}
