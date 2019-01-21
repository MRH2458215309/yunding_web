/**
 * FileName:InfoAddForm
 * Author:leeyf
 * Date:19-1-21 下午2:27
 * Description:信息保存表单
 */
package com.yundingshuyuan.website.form;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
public class InfoAddForm {

    @Column(name = "real_name")
    @NotEmpty

    private String realName;

    private String sex;

    private String series;

    private String direction;

    private String academy;

    private String major;

    @Column(name = "class")
    private String classroom;

    @Column(name = "native_place")
    private String nativePlace;

    private String dormitory;

    private String room;

    private String signature;
}
