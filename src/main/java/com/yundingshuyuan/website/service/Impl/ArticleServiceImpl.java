package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.entity.Article;
import com.yundingshuyuan.website.enums.ArticleCodeEnum;
import com.yundingshuyuan.website.enums.ErrorCodeEnum;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.repository.ArticleRepository;
import com.yundingshuyuan.website.service.ArticleService;
import com.yundingshuyuan.website.service.support.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.h
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article, Integer> implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    /**
     * 查询文章:大家谈，技术流，思想流，学员心得
     * @param article
     */
    @Override
    public List<Article> selectArticle1(Article article) {

        List<Article> articleOptional = articleRepository.findByLabelOrderByIdDesc(article.getLabel());

        if(articleOptional.isEmpty()){
            throw new SysException(ErrorCodeEnum.PARAM_ERROR);
        }

        return articleOptional;
    }

    /**
     * 云顶院，极客团，创客团
     * @param article
     * @return
     */
    @Override
    public List<Article> selectArticle2(Article article) {
        List<Article> articleOptional = articleRepository.findByLabelOrderByIdDesc2(article.getLabel());

        if (articleOptional.isEmpty()){
            throw new SysException(ErrorCodeEnum.PARAM_ERROR);
        }

        return articleOptional;
    }

    /**
     * 轮播文章
     * @param article
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Article> selectArticle3(Article article, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize,Sort.Direction.DESC,"id");
        Page<Article> articles = articleRepository.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("label").as(Integer.class),article.getLabel());
                query.where(p1);
                return query.getRestriction();
            }
        },pageable);
        return articles;
    }

    /**
     * 文章上传
     * @param article
     * @return
     */
    @Override
    public void articleInsert(Article article, String realPath) {

        Article article1 = new Article();
        article1.setTitle(article.getTitle());
        article1.setContent(article.getContent());
        article1.setLabel(article.getLabel());
        article1.setImage(realPath);
        article1.setBrowseNumber(0);
        article1.setLikeNumber(0);
        article1.setCommentNumber(0);
        article1.setCreatedAt(new Date());
        article1.setUpdatedAt(new Date());

        if (article1 == null) {
            throw new SysException(ArticleCodeEnum.ARTICLE_NONE);
        }

        articleRepository.save(article1);

    }

    /**
     * 文章更新（不包含图片）
     * @param article
     */
    @Override
    public void articleUpdate(Article article) {

        Article article1 = new Article();
        article1.setId(article.getId());
        article1.setImage(article.getImage());
        article1.setTitle(article.getTitle());
        article1.setContent(article.getContent());
        article1.setLabel(article.getLabel());
        article1.setUpdatedAt(new Date());
        System.out.println(article1);

        if (article1 == null){
            throw new SysException(ArticleCodeEnum.ARTICLE_NONE);
        }

        articleRepository.update(article1);

    }

    /**
     * 文章更新（包含图片）
     * @param article
     * @param realPath
     */
    @Override
    public void articleUpdateWithImage(Article article, String realPath) {


        Article article1 = new Article();
        article1.setId(article.getId());
        article1.setTitle(article.getTitle());
        article1.setLabel(article.getLabel());
        article1.setContent(article.getContent());
        article1.setImage(realPath);
        article1.setUpdatedAt(new Date());
        System.out.println(article1);

        if (article1 == null) {
            throw new SysException(ArticleCodeEnum.ARTICLE_NONE);
        }

        articleRepository.update(article1);

    }


}





