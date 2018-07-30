package com.megvii.ui.bean;

/**
 * @author by licheng on 2018/7/19.
 */

public class FaceParameters implements IParameters {

    private static final long serialVersionUID = 3894019928664637167L;
    private String key;
    private String value;
    private String desc;
    private Bean[] valuePool;
    private int layoutId;

    public FaceParameters(String key, String value, String desc, Bean[] valuePool, int layoutId) {
        this.key = key;
        this.value = value;
        this.desc = desc;
        this.valuePool = valuePool;
        this.layoutId = layoutId;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public Bean[] getValuePool() {
        return this.valuePool;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getLayoutId() {
        return this.layoutId;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
