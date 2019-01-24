package com.yundingshuyuan.website.repository;

import com.yundingshuyuan.website.entity.Article;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Mr.h
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer>, PagingAndSortingRepository<Article, Integer> {

    /**
     * 大家谈，技术流，思想流，学员心得
     * @param label
     * @return
     */
    @Modifying
    @Query(value = "select * from t_article t where t.label = ?1 order by t.id DESC limit 4",nativeQuery = true)
    List<Article> findByLabelOrderByIdDesc(Integer label);

    /**
     * 云顶院，极客团，创客团
     * @param label
     * @return
     */
    @Modifying
    @Query(value = "select * from t_article t where t.label = ?1 order by t.id DESC limit 3",nativeQuery = true)
    List<Article> findByLabelOrderByIdDesc2(Integer label);


    /**
     * 轮播文章
     * @param label
     * @param pageable
     * @return
     */
    Page<Article> findAll(Specification<Article> label, Pageable pageable);


    /**
     * 文章更新
     * @param article
     */
    @Modifying
    @Transactional
    @Query(value = "update t_article a set " +
            "a.title = case when :#{#article.title} is null then a.title else :#{#article.getTitle()} end ," +
            "a.image = case when :#{#article.image} is null then a.image else :#{#article.getImage()} end , " +
            "a.content = case when :#{#article.content} is null then a.content else :#{#article.getContent()} end ," +
            "a.label = case when :#{#article.label} is null then a.label else :#{#article.getLabel()} end ," +
            "a.updated_at = case when :#{#article.updatedAt} is null then a.updated_at else :#{#article.getUpdatedAt()} end " +
            "where a.id = :#{#article.id}"
            ,nativeQuery = true)
    void update(@Param("article") Article article);


}

