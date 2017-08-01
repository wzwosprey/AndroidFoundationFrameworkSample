package com.example.wangzw.mybase.greendao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wangzw on 2017/8/1.
 */
@Entity
public class UserInfoGreenDaoBean extends BaseGreenDaoBean {
    @Id
    @NotNull
    @Unique
    @Property(nameInDb = "user_id")
    private String userId;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "gender")
    private Integer gender;

    @Property(nameInDb = "age")
    private Integer age;

    @Property(nameInDb = "avatar")
    private String avatar;
    
    @Property(nameInDb = "phone")
    private String phone;

    @Property(nameInDb = "address")
    private String address;

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getGender() {
        return this.gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Generated(hash = 114272821)
    public UserInfoGreenDaoBean(@NotNull String userId, String name,
            Integer gender, Integer age, String avatar, String phone, String address) {
        this.userId = userId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.avatar = avatar;
        this.phone = phone;
        this.address = address;
    }

    @Generated(hash = 496055463)
    public UserInfoGreenDaoBean() {
    }
}
