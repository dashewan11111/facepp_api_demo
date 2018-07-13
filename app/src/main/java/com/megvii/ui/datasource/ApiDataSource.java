package com.megvii.ui.datasource;

import com.megvii.ui.R;

/**
 * @author by licheng on 2018/7/6.
 */

public enum ApiDataSource {

    FACE_DETECT(0, "detect", "人脸检测 -- Detect API", "检测图片中的人脸（支持一至多张人脸），并标记出边框。您也可以对尺寸最大的5张人脸进行分析，获得面部关键点、年龄、性别、头部姿态、微笑检测、眼镜检测以及人脸质量等信息。", R.drawable.face_detect),

    FACE_COMPARE(1, "compare", "人脸比对 -- Compare API", "将两张人脸进行比对，来判断是否是同一个人，并给出置信度评分。 即1:1身份验证。", R.drawable.face_compare),

    FACE_SEARCH(2, "search", "人脸检索 -- Search API", "将一张人脸和N张人脸进行比对，找出最相似的一张或多张人脸。即1:N人脸搜索。", R.drawable.face_search),

    FACE_BEAUTIFY(3, "beautify", "人脸美化 -- Beautify API", "对图片进行美颜和美白。", R.drawable.face_beauty),

    FACE_ANALYZE(4, "analyze", "人脸分析 -- Face Analyze API", "通过将检测到的人脸传入Analyze API，您可以获得面部关键点、年龄、性别、头部姿态、微笑检测、眼镜检测以及人脸质量等信息。", R.drawable.face_detect),

    FACE_GET_DETAIL(5, "getDetail", "人脸信息 -- Face GetDetail API", "通过传入在Detect API检测出的人脸标识face_token，获取一个人脸的关联信息，包括源图片ID、归属的FaceSet。", R.drawable.face_detect),

    FACE_SET_USER_ID(6, "setUserId", "设置UserId -- Face SetUserID API", "为检测出的某一个人脸添加标识信息，该信息会在Search接口结果中返回，用来确定用户身份。", R.drawable.face_detect),

    FACESET_CREATE(7, "faceset/create", "创建人脸库 -- FaceSet Create API", "创建一个人脸的集合 FaceSet，用于存储人脸标识 face_token。一个 FaceSet 能够存储 10,000 个 face_token。", R.drawable.face_detect),

    FACESET_ADD_FACE(8, "faceset/addface", "添加人脸到人脸库 -- FaceSet AddFace API", "为一个已经创建的 FaceSet 添加人脸标识 face_token。一个 FaceSet 最多存储10,000个 face_token。", R.drawable.face_detect),

    FACESET_REMOVE_FACE(9, "faceset/removeface", "从人脸库删除人脸 -- FaceSet RemoveFace API", "移除一个FaceSet中的某些或者全部face_token。", R.drawable.face_detect),

    FACESET_UPDATE(10, "faceset/update", "更新人脸库 -- FaceSet Update API", "更新一个人脸集合的属性。", R.drawable.face_detect),

    FACESET_GET_DETAIL(11, "faceset/getdetail", "人脸库详情 -- FaceSet GetDetail API", "获取一个 FaceSet 的所有信息，包括此 FaceSet 的 faceset_token, outer_id, display_name 的信息，以及此FaceSet 中存放的 face_token 数量与列表。单次查询最多返回 100 个 face_token。如需获取全量数据，需要配合使用 start 和 next参数。", R.drawable.face_detect);

    private int index;

    private String name;

    private String desc;

    private String title;

    private int resId;

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getTitle() {
        return title;
    }

    public int getResId() {
        return resId;
    }

    ApiDataSource(int index, String name, String title, String desc, int resId) {
        this.index = index;
        this.name = name;
        this.desc = desc;
        this.title = title;
        this.resId = resId;
    }

    public static ApiDataSource getApiByIndex(int position) {
        for (ApiDataSource api : ApiDataSource.values()) {
            if (api.index == position) {
                return api;
            }
        }
        return null;
    }
}
