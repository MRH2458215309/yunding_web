/**
 * FileName:User
 * Author:leeyf
 * Date:19-1-20 上午10:00
 * Description:用户实体
 */
package com.yundingshuyuan.website.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "t_user")
public class User {
    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    /**
     * 用户名(手机号)
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户状态 0-正常 -1被封号
     */
    private Integer status;

    /**
     *创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

}
