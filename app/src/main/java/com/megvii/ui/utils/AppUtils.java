package com.megvii.ui.utils;

import android.content.Context;
import android.util.Xml;

import com.megvii.facepp.api.bean.FaceLandmark;
import com.megvii.ui.bean.ApiListItem;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by licheng on 2018/7/6.
 */

public class AppUtils {

    // 获取属性
    public static List<float[]> getAttributes(Object model) throws NoSuchMethodException,
            IllegalAccessException,
            IllegalArgumentException,
            InvocationTargetException {

        List<float[]> points = new ArrayList<>();
        Class clazz = model.getClass();
        Field[] fields = model.getClass().getDeclaredFields(); //获取实体类的所有属性，返回Field数组
        for (Field field : fields) { //遍历所有属性
            String name = field.getName(); //获取属性的名字
            name = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法
            Method m = clazz.getMethod("get" + name);
            FaceLandmark.Point value = (FaceLandmark.Point) m.invoke(model);
            if (null == value) {
                continue;
            }
            points.add(new float[]{value.getX(), value.getY()});
        }
        return points;
    }

    public static List<ApiListItem> getApis(InputStream is, String startTag) throws Exception {
        List<ApiListItem> list = null;
        ApiListItem apiItem = null;

        //创建xmlPull解析器
        XmlPullParser parser = Xml.newPullParser();
        ///初始化xmlPull解析器
        parser.setInput(is, "utf-8");
        //读取文件的类型

        int type = parser.getEventType();
        //无限判断文件类型进行读取
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                //开始标签
                case XmlPullParser.START_TAG:
                    if (startTag.equals(parser.getName())) {
                        list = new ArrayList<>();
                    } else if ("Api".equals(parser.getName())) {
                        apiItem = new ApiListItem();
                    } else if ("name".equals(parser.getName())) {
                        //获取name值
                        String name = parser.nextText();
                        apiItem.setName(name);
                    } else if ("title".equals(parser.getName())) {
                        //获取nickName值
                        String title = parser.nextText();
                        apiItem.setTitle(title);
                    } else if ("desc".equals(parser.getName())) {
                        //获取nickName值
                        String desc = parser.nextText();
                        apiItem.setDesc(desc);
                    }
                    break;
                //结束标签
                case XmlPullParser.END_TAG:
                    if ("Api".equals(parser.getName())) {
                        list.add(apiItem);
                    }
                    break;
            }
            //继续往下读取标签类型
            type = parser.next();
        }
        return list;
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
