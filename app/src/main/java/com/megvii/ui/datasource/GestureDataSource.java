package com.megvii.ui.datasource;

import com.megvii.ui.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author by licheng on 2018/7/30.
 */

public class GestureDataSource {

    public static final List<GestureResult> GESTURES = new ArrayList<>();

    /**
     *
     unknown：未定义手势
     heart_a：比心 A
     heart_b：比心 B
     heart_c：比心 C
     heart_d：比心 D
     ok：OK
     hand_open：手张开
     thumb_up：大拇指向上
     thumb_down：大拇指向下
     rock：ROCK
     namaste：合十
     palm_up：手心向上
     fist：握拳
     index_finger_up：食指朝上
     double_finger_up：双指朝上
     victory：胜利
     big_v：大 V 字
     phonecall：打电话
     beg：作揖
     thanks：感谢
     */
    static {
        GESTURES.add(new GestureResult("beg", R.drawable.gesture_1, "作揖", R.drawable.gesture_1_display));
        GESTURES.add(new GestureResult("big_v", R.drawable.gesture_2, "大V字", R.drawable.gesture_2_display));
        GESTURES.add(new GestureResult("double_finger_up", R.drawable.gesture_3, "双指朝上", R.drawable.gesture_3_display));
        GESTURES.add(new GestureResult("fist", R.drawable.gesture_4, "握拳", R.drawable.gesture_4_display));
        GESTURES.add(new GestureResult("hand_open", R.drawable.gesture_5, "手张开", R.drawable.gesture_5_display));
        GESTURES.add(new GestureResult("heart_a", R.drawable.gesture_6, "比心", R.drawable.gesture_6_display));
        GESTURES.add(new GestureResult("heart_b", R.drawable.gesture_7, "比心", R.drawable.gesture_7_display));
        GESTURES.add(new GestureResult("heart_c", R.drawable.gesture_8, "比心", R.drawable.gesture_8_display));
        GESTURES.add(new GestureResult("heart_d", R.drawable.gesture_9, "比心", R.drawable.gesture_9_display));
        GESTURES.add(new GestureResult("index_finger_up", R.drawable.gesture_10, "食指朝上", R.drawable.gesture_10_display));
        GESTURES.add(new GestureResult("namaste", R.drawable.gesture_11, "合十", R.drawable.gesture_11_display));
        GESTURES.add(new GestureResult("ok", R.drawable.gesture_12, "OK", R.drawable.gesture_12_display));
        GESTURES.add(new GestureResult("palm_up", R.drawable.gesture_13, "手心向上", R.drawable.gesture_13_display));
        GESTURES.add(new GestureResult("phonecall", R.drawable.gesture_14, "打电话", R.drawable.gesture_14_display));
        GESTURES.add(new GestureResult("rock", R.drawable.gesture_15, "ROCK", R.drawable.gesture_15_display));
        GESTURES.add(new GestureResult("thanks", R.drawable.gesture_16, "感谢", R.drawable.gesture_16_display));
        GESTURES.add(new GestureResult("thumb_down", R.drawable.gesture_17, "差评", R.drawable.gesture_17_display));
        GESTURES.add(new GestureResult("thumb_up", R.drawable.gesture_18, "好评", R.drawable.gesture_18_display));
        GESTURES.add(new GestureResult("victory", R.drawable.gesture_19, "胜利", R.drawable.gesture_19_display));
    }

    public static class GestureResult {
        private String name;
        private int resId;

        private String displayName;
        private int displayResId;

        private float confidence;

        public String getName() {
            return name;
        }

        public int getResId() {
            return resId;
        }

        public String getDisplayName() {
            return displayName;
        }

        public int getDisplayResId() {
            return displayResId;
        }

        public float getConfidence() {
            return confidence;
        }

        public void setConfidence(float confidence) {
            this.confidence = confidence;
        }

        public GestureResult(String name, int resId, String displayName, int displayResId) {
            this.name = name;
            this.resId = resId;
            this.displayName = displayName;
            this.displayResId = displayResId;
            this.confidence = 0f;
        }

        public static GestureResult findResult(String name, float confidence) {
            for (GestureResult result : GESTURES) {
                if (result.name.equals(name)) {
                    result.setConfidence(confidence);
                    return result;
                }
            }
            return new GestureResult("unknown", 0, "未定义手势", 0);
        }
    }

    // 获取属性
    public static GestureResult getGestureResult(Object model) throws NoSuchMethodException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {

        Class clazz = model.getClass();
        Field[] fields = model.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组

        List<Bean> propertyList = new ArrayList<>();

        for (Field field : fields) { //遍历所有属性
            String name = field.getName(); //获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
            Method m = clazz.getMethod("get" + name);
            float value = (float) m.invoke(model);
            Bean bean = new Bean(field.getName(), value);
            propertyList.add(bean);
        }

        Collections.sort(propertyList, new Comparator<Bean>() {
            @Override
            public int compare(Bean left, Bean right) {
                return (int) (right.confidence - left.confidence);
            }
        });
        Bean bean = propertyList.get(0);
        return GestureResult.findResult(bean.name, bean.confidence);
    }

    private static class Bean {
        private String name;
        private float confidence;

        public Bean(String name, float confidence) {
            this.name = name;
            this.confidence = confidence;
        }
    }
}
