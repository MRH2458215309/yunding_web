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


    @Modifying
    @Query(value = "update t_article a set" +
            "case when :#{#article.getTitle()} is not null then a.title = :#{#article.getTitle()}," +
            "case when :#{#article.getImage()} is not null then a.image = :#{#article.getImage()}, " +
            "case when :#{#article.getContent()} is not null then a.content = :#{#article.getContent()}," +
            "case when :#{#article.getLabel()} is not null then a.label = :#{#article.getLabel()}," +
            "case when :#{article.getUpdatedAt()} is not null then a.updatedAt = :#{#article.getUpdatedAt()}," +
            "where a.id = :#{#article.getId()}"
            ,nativeQuery = true)
    List<Article> update(Article article);

    @Modifying
    @Query(value = "update t_article a set" +
            "a.title = case when :#{#article.title} is null then :#{#article.title} else :#{#article.title} end ," +
            "a.content= case when :#{#article.content} is null then :#{#article.content} else :#{#article.content} end ," +
            "a.label= case when :#{#article.label} is null then :#{#article.label} else :#{#article.label} end ," +
            "a.updatedAt= case when :#{article.updatedAt} is null then :#{#article.updatedAt} else :#{#article.updatedAt} end ," +
            "where a.id = :#{#article.id}"
            ,nativeQuery = true)
    List<Article> updateWithoutImage(@Param("article") Article article);

}

