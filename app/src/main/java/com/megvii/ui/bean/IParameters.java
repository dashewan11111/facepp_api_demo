package com.megvii.ui.bean;

import java.io.Serializable;

/**
 * @author by licheng on 2018/7/19.
 */

public interface IParameters extends Cloneable, Serializable {

    String getKey();

    String getValue();

    void setValue(String value);

    String getDesc();

    Bean[] getValuePool();

    int getLayoutId();

    class Bean implements Serializable, Cloneable {

        private static final long serialVersionUID = 8281240999881393294L;
        String value;
        String desc;

        public Bean(String value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public String getValue() {
            return value;
        }

        public String getDesc() {
            return desc;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}
