/**
 * FileName:InfoAddForm
 * Author:leeyf
 * Date:19-1-21 下午2:27
 * Description:信息保存表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author leeyf
 */
@Data
public class InfoAddForm {

    @NotEmpty
    private String realName;

    private String sex;

    private String series;

    private String direction;

    private String birthday;

    private String academy;

    private String major;

    private String classroom;

    private String nativePlace;

    private Integer dormitory;

    private Integer room;

    private String signature;
}
