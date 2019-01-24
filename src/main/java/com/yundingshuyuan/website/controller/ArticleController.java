package com.yundingshuyuan.website.controller;

import com.yundingshuyuan.website.constant.ArticleConstant;
import com.yundingshuyuan.website.constant.UserConstant;
import com.yundingshuyuan.website.controller.support.BaseController;
import com.yundingshuyuan.website.entity.Article;
import com.yundingshuyuan.website.exception.SysException;
import com.yundingshuyuan.website.service.ArticleService;
import com.yundingshuyuan.website.utils.FileUtils;
import com.yundingshuyuan.website.vo.PageVO;
import com.yundingshuyuan.website.vo.SimpleArticleVO;
import com.yundingshuyuan.website.wrapper.ResultWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.h
 */
@Api(tags = "文章查询")
@RequestMapping("/select")
@RestController
@Slf4j
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;


    /**
     * 大家谈，技术流，思想流，学员心得
     * @param article
     * @param bindingResult
     * @return
     */
    @ApiOperation("查询文章4篇")
    @GetMapping("/article4/label")
    public ResultWrapper articleListByLabel1(@Valid Article article, BindingResult bindingResult){
        validateParams(bindingResult);

        List<Article> s1 = articleService.selectArticle1(article);

        if (s1 == null){
            return ResultWrapper.failure();
        }else {
            return ResultWrapper.successWithData(s1);
        }
    }

    /**
     * 云顶院，极客团，创客团
     * @param article
     * @param bindingResult
     * @return
     */
    @ApiOperation("查询文章3篇")
    @GetMapping("/article3/label")
    public ResultWrapper articleListByLabel2(@Valid Article article, BindingResult bindingResult){
        validateParams(bindingResult);

        List<Article> s2 = articleService.selectArticle2(article);

        if (s2 == null){
            return ResultWrapper.failure();
        }else {
            return ResultWrapper.successWithData(s2);
        }
    }

    /**
     * 轮播文章
     * @param article
     * @param bindingResult
     * @return
     */
    @ApiOperation("查询轮播文章")
    @GetMapping("/article1/label")
    public ResultWrapper<PageVO<SimpleArticleVO>> articleListByLabel3(@Valid Article article, BindingResult bindingResult,
                                             int pageNo, int pageSize){
        validateParams(bindingResult);

        Page<Article> s3 = articleService.selectArticle3(article, pageNo, pageSize);
        PageVO<SimpleArticleVO> articlePageVO = new PageVO<>();
        articlePageVO.setPage(s3.getNumber());
        articlePageVO.setSize(s3.getSize());
        articlePageVO.setTotalPages(s3.getTotalPages());

        List<SimpleArticleVO> simpleArticleVOList = toVOList(s3.getContent(), SimpleArticleVO.class);

        articlePageVO.setDataList(simpleArticleVOList);


        if (s3 == null){
            return ResultWrapper.failure();
        }else {
            return ResultWrapper.successWithData(articlePageVO);
        }
    }


    /**
     * 文章上传
     * @param article
     * @param imageFile
     * @param request
     * @return
     */
    @ApiOperation("文章上传")
    @PostMapping("/upload")
    public ResultWrapper articleInsert(@Valid Article article,
                                       MultipartFile imageFile,
                                       HttpServletRequest request) {

        log.info(imageFile.getOriginalFilename());
        try {
            if (imageFile == null || article.getLabel() == null ||
                    article.getContent() == null || article.getTitle() == null){
                return ResultWrapper.failure("上传文章信息不能为空！");

            } else {
                /**
                 * 保存图片
                 */
                ResultWrapper resultWrapper= FileUtils.saveImage(request, imageFile, ArticleConstant.ARTICLE_IMAGE_REAL_PATH);

                //真实路径
                String realPath = resultWrapper.getMessage();

                articleService.articleInsert(article,realPath);

                return ResultWrapper.success();

            }

        } catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure("上传信息失败！");
        }
    }

    /**
     * 文章更新
     * @param article
     * @param imageFile
     * @param request
     * @return
     */
    @ApiOperation("文章更新")
    @PostMapping("/update")
    public ResultWrapper updateArticle(@Valid Article article,
                                       MultipartFile imageFile,
                                       HttpServletRequest request){
        try{
            if (article.getLabel() < 0 || article.getLabel() > 9){

                if (imageFile == null){
                    articleService.articleUpdate(article);
                    return ResultWrapper.success();

                } else {
                    /**
                     * 保存图片
                     */
                    ResultWrapper resultWrapper= FileUtils.saveImage(request, imageFile, ArticleConstant.ARTICLE_IMAGE_REAL_PATH);

                    //真实路径
                    String realPath = resultWrapper.getMessage();

                    articleService.articleUpdateWithImage(article,realPath);

                    return ResultWrapper.success();
                }
            } else {
                return ResultWrapper.failure("标签范围为1～9！");
            }

        } catch (Exception e){
            e.printStackTrace();
            return ResultWrapper.failure("更新文章失败！");
        }

    }


    /**
     * 删除文章
     * @param article
     * @param bindingResult
     * @return
     */
    @ApiOperation("文章删除")
    @PostMapping("/delete")
    public ResultWrapper deleteArticle(@Valid Article article, BindingResult bindingResult){
        validateParams(bindingResult);

        articleService.updateLabel(article);

        return ResultWrapper.success("删除成功！");
    }

}
