package com.jnu.accountbook.bean;

public class AccountBean {
    private int id;
    private int imageId;
    private String typename;
    private String accountType;
    private String remark;
    private float money;
    private String date;

    public AccountBean() {
    }

    public AccountBean(int id, int imageId, String typename, String accountType, String remark, float money, String date) {
        this.id = id;
        this.imageId = imageId;
        this.typename = typename;
        this.accountType = accountType;
        this.remark = remark;
        this.money = money;
        this.date = date;
    }

    public AccountBean(int imageId, String typename, String accountType, String remark, float money, String date) {
        this.imageId = imageId;
        this.typename = typename;
        this.accountType = accountType;
        this.remark = remark;
        this.money = money;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
