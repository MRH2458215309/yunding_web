package com.yundingshuyuan.website.service.Impl;

import com.yundingshuyuan.website.entity.Article;
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


}





