/**
 * FileName:UserIdentity
 * Author:leeyf
 * Date:19-1-21 上午11:19
 * Description:用户权限类
 */
package com.yundingshuyuan.website.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "t_identity")
public class UserIdentity {
    /**
     * userID
     */
    @Id
    @Column(name = "user_id")
    private String id;

    /**
     * 用户权限
     */
    private Integer detail;

}
