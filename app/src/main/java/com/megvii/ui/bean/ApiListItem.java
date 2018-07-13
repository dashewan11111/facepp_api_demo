package com.megvii.ui.bean;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiListItem {

    private String name;

    private String desc;

    private String title;

    private int resId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "ApiListItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", title='" + title + '\'' +
                ", resId=" + resId +
                '}';
    }

}
