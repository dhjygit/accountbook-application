package com.jnu.accountbook.bean;

public class TypeBean {
    int id;
    int imageId;
    int sImageId;
    String typename;
    int kind;

    public TypeBean() {
    }

    public TypeBean(int id, int imageId, int sImageId, String typename, int kind) {
        this.id = id;
        this.imageId = imageId;
        this.sImageId = sImageId;
        this.typename = typename;
        this.kind = kind;
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

    public int getsImageId() {
        return sImageId;
    }

    public void setsImageId(int sImageId) {
        this.sImageId = sImageId;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }
}
