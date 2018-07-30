package com.megvii.ui.datasource;

import com.megvii.ui.R;
import com.megvii.ui.bean.FaceParameters;
import com.megvii.ui.bean.IParameters;
import com.megvii.ui.bean.IParameters.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by licheng on 2018/7/19.
 */

public class ParametersConstant {

    public static final IParameters FACE_TOKEN = new FaceParameters("face_token", "", "人脸标识", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_FILE = new FaceParameters("image_file", "", "图片", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_URL = new FaceParameters("image_url", "", "图片的 URL", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_BASE64 = new FaceParameters("image_base64", "", "base64 编码的二进制图片数据。只要内容，不带 'base64' 头部信息", new Bean[]{}, R.layout.view_parameters_input);

    public static final IParameters FACE_TOKEN_1 = new FaceParameters("face_token1", "", "人脸1 标识", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_FILE_1 = new FaceParameters("image_file1", "", "图片1", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_URL_1 = new FaceParameters("image_url1", "", "图片1的 URL", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_BASE64_1 = new FaceParameters("image_base64_1", "", "base64 编码的二进制图片数据。只要内容，不带 'base64' 头部信息", new Bean[]{}, R.layout.view_parameters_input);

    public static final IParameters FACE_TOKEN_2 = new FaceParameters("face_token2", "", "人脸2 标识", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_FILE_2 = new FaceParameters("image_file2", "", "图片2", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_URL_2 = new FaceParameters("image_url2", "", "图片2的 URL", new Bean[]{}, R.layout.view_parameters_input);
    public static final IParameters IMAGE_BASE64_2 = new FaceParameters("image_base64_2", "", "base64 编码的二进制图片数据。只要内容，不带 'base64' 头部信息", new Bean[]{}, R.layout.view_parameters_input);

    public static final IParameters RETURN_LANDMARK = new FaceParameters("return_landmark",
            "0",
            "是否检测并返回人脸关键点",
            new Bean[]{
                    new Bean("0", "不检测"),
                    new Bean("1", "检测。返回 83 个人脸关键点。"),
                    new Bean("2", "检测。返回 106 个人脸关键点。"),
            }, R.layout.view_parameters_single_choice);

    public static final IParameters RETURN_ATTRIBUTES = new FaceParameters("return_attributes",
            "none",
            "希望检测并返回的属性。",
            new Bean[]{
                    new Bean("none", "不检测属性"),
                    new Bean("gender", "性别"),
                    new Bean("age", "年龄"),
                    new Bean("smiling", "笑容"),
                    new Bean("headpose", "头部角度"),
                    new Bean("facequality", "人脸质量分析"),
                    new Bean("blur", "人脸模糊分析"),
                    new Bean("eyestatus", "眼睛状态"),
                    new Bean("emotion", "情绪识别"),
                    new Bean("ethnicity", "人种"),
                    new Bean("beauty", "颜值"),
                    new Bean("mouthstatus", "嘴部状态"),
                    new Bean("eyegaze", "眼球位置与视线方向"),
                    new Bean("skinstatus", "面部特征识别"),
            }, R.layout.view_parameters_multi_choice);


    public static final IParameters CALCULATE_ALL = new FaceParameters("calculate_all",
            "0",
            "是否检测并返回所有人脸的人脸关键点和人脸属性",
            new Bean[]{
                    new Bean("0", "否"),
                    new Bean("1", "是"),
            }, R.layout.view_parameters_single_choice);

    public static final IParameters FACESET_TOKEN = new FaceParameters("faceset_token",
            "",
            "人脸的集合库",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters DISPLAY_NAME = new FaceParameters("display_name",
            "",
            "人脸集合的名字",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters OUTER_ID = new FaceParameters("outer_id",
            "",
            "账号下全局唯一的 FaceSet 自定义标识，可以用来管理 FaceSet 对象。",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters USER_ID = new FaceParameters("user_id",
            "",
            "用户自定义的user_id",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters TAGS = new FaceParameters("tags",
            "",
            "账号下全局唯一的 FaceSet 自定义标识，可以用来管理 FaceSet 对象。",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters FACE_TOKENS = new FaceParameters("face_tokens",
            "",
            "人脸标识 face_token，可以是一个或者多个，用逗号分隔。",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters USER_DATA = new FaceParameters("user_data",
            "",
            "自定义用户信息，不大于16 KB",
            new Bean[]{

            }, R.layout.view_parameters_input);


    public static final IParameters FORCE_MERGE = new FaceParameters("force_merge",
            "0",
            "在传入 outer_id 的情况下，如果 outer_id 已经存在，是否将 face_token 加入已经存在的 FaceSet 中",
            new Bean[]{
                    new Bean("0", "否"),
                    new Bean("1", "是"),
            }, R.layout.view_parameters_single_choice);


    public static final IParameters NEW_OUTER_ID = new FaceParameters("new_outer_id",
            "",
            "在api_key下全局唯一的FaceSet自定义标识，可以用来管理FaceSet对象。",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters START = new FaceParameters("start",
            "",
            "从第 n 个 face_token 开始返回,从第 n 个 face_token 开始返回",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters CHECK_EMPTY = new FaceParameters("check_empty",
            "1",
            "删除时是否检查FaceSet中是否存在face_token",
            new Bean[]{
                    new Bean("0", "不检查"),
                    new Bean("1", "检查")
            }, R.layout.view_parameters_single_choice);

    public static final IParameters WHITENIMG = new FaceParameters("whitening",
            "100",
            "美白程度, 0不美白，100代表最高程度",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final IParameters SMOOTHING = new FaceParameters("smoothing",
            "100",
            "磨皮程度, 0不磨皮，100代表最高程度",
            new Bean[]{

            }, R.layout.view_parameters_input);

    public static final List<IParameters> FACE_SET_CREATE = new ArrayList<>();

    static {
        FACE_SET_CREATE.add(DISPLAY_NAME);
        FACE_SET_CREATE.add(OUTER_ID);
        FACE_SET_CREATE.add(TAGS);
        FACE_SET_CREATE.add(FACE_TOKENS);
        FACE_SET_CREATE.add(USER_DATA);
        FACE_SET_CREATE.add(FORCE_MERGE);
    }

    public static final List<IParameters> FACE_SET_ADD_FACE = new ArrayList<>();

    static {
        FACE_SET_ADD_FACE.add(FACESET_TOKEN);
        FACE_SET_ADD_FACE.add(FACE_TOKENS);
    }

    public static final List<IParameters> FACE_SET_SEARCH = new ArrayList<>();

    static {
        FACE_SET_SEARCH.add(FACESET_TOKEN);
    }

    public static final List<IParameters> FACE_ANALYZE = new ArrayList<>();

    static {
        FACE_ANALYZE.add(FACE_TOKENS);
        FACE_ANALYZE.add(RETURN_ATTRIBUTES);
    }

    public static final List<IParameters> FACE_GET_DETAIL = new ArrayList<>();

    static {
        FACE_GET_DETAIL.add(FACE_TOKEN);
    }

    public static final List<IParameters> FACE_SET_USER_ID = new ArrayList<>();

    static {
        FACE_SET_USER_ID.add(FACE_TOKEN);
        FACE_SET_USER_ID.add(USER_ID);
    }

    public static final List<IParameters> FACE_SET_REMOVE_FACE = new ArrayList<>();

    static {
        FACE_SET_REMOVE_FACE.add(FACESET_TOKEN);
        FACE_SET_REMOVE_FACE.add(FACE_TOKENS);
    }

    public static final List<IParameters> FACE_SET_UPDATE = new ArrayList<>();

    static {
        FACE_SET_UPDATE.add(FACESET_TOKEN);
        FACE_SET_UPDATE.add(NEW_OUTER_ID);
        FACE_SET_UPDATE.add(DISPLAY_NAME);
        FACE_SET_UPDATE.add(USER_DATA);
        FACE_SET_UPDATE.add(TAGS);
    }
}
