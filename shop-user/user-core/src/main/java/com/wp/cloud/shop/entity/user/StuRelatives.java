package com.wp.cloud.shop.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @Title: cloud-test--User
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-30 13:16
 */
@Entity
@Table(name="szsx_stu_relatives")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StuRelatives {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private Boolean vip;

    @Column
    private BigDecimal balance;
}