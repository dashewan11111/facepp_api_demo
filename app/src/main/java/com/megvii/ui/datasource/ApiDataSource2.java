package com.megvii.ui.datasource;

import android.text.TextUtils;

import com.megvii.ui.R;
import com.megvii.ui.fragment.BodyDetectFragment;
import com.megvii.ui.fragment.BodySegmentFragment;
import com.megvii.ui.fragment.FaceAnalyzeFragment;
import com.megvii.ui.fragment.FaceBeautyFragment;
import com.megvii.ui.fragment.FaceCompareFragment;
import com.megvii.ui.fragment.FaceDetectFragment;
import com.megvii.ui.fragment.FaceGetDetailFragment;
import com.megvii.ui.fragment.FaceSearchFragment2;
import com.megvii.ui.fragment.FaceSetAddFaceFragment;
import com.megvii.ui.fragment.FaceSetCreateFragment;
import com.megvii.ui.fragment.FaceSetDetailFragment;
import com.megvii.ui.fragment.FaceSetGroupFragment;
import com.megvii.ui.fragment.FaceSetRemoveFaceFragment;
import com.megvii.ui.fragment.FaceSetUpdateFragment;
import com.megvii.ui.fragment.FaceSetUserIdFragment;
import com.megvii.ui.fragment.GestureFragment;
import com.megvii.ui.fragment.OCRBankCardFragment;
import com.megvii.ui.fragment.OCRDriverFragment;
import com.megvii.ui.fragment.OCRIDCardFragment;
import com.megvii.ui.fragment.OCRVehicleFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by licheng on 2018/7/6.
 */

public class ApiDataSource2 {

    public static class ApiItem {

        private String name;

        private String desc;

        private String title;

        private int resId;

        private String className;

        private String subFragment;

        public void setTitle(String title) {
            this.title = title;
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

        public String getClassName() {
            return className;
        }

        public String getSubFragment() {
            return subFragment;
        }

        ApiItem(String name, String title, String desc, int resId, String className) {
            this.name = name;
            this.desc = desc;
            this.title = title;
            this.resId = resId;
            this.className = className;
        }

        ApiItem(String name, String title, String desc, int resId, String className, String subFragment) {
            this.name = name;
            this.desc = desc;
            this.title = title;
            this.resId = resId;
            this.className = className;
            this.subFragment = subFragment;
        }
    }

    private static final ApiItem FACE_DETECT = new ApiItem("detect", "人脸检测 -- Detect API", "检测图片中的人脸（支持一至多张人脸），并标记出边框。您也可以对尺寸最大的5张人脸进行分析，获得面部关键点、年龄、性别、头部姿态、微笑检测、眼镜检测以及人脸质量等信息。", R.drawable.face_detect, FaceDetectFragment.class.getName());

    private static final ApiItem FACE_COMPARE = new ApiItem("compare", "人脸比对 -- Compare API", "将两张人脸进行比对，来判断是否是同一个人，并给出置信度评分。 即1:1身份验证。", R.drawable.face_compare, FaceCompareFragment.class.getName());

    private static final ApiItem FACE_SEARCH = new ApiItem("search", "人脸检索 -- Search API", "将一张人脸和N张人脸进行比对，找出最相似的一张或多张人脸。即1:N人脸搜索。", R.drawable.face_search, FaceSearchFragment2.class.getName());

    private static final ApiItem FACE_BEAUTIFY = new ApiItem("beautify", "人脸美化 -- Beautify API", "对图片进行美颜和美白。", R.drawable.face_beauty, FaceBeautyFragment.class.getName());

    private static final ApiItem FACE_ANALYZE = new ApiItem("analyze", "人脸分析 -- Face Analyze API", "通过将检测到的人脸传入Analyze API，您可以获得面部关键点、年龄、性别、头部姿态、微笑检测、眼镜检测以及人脸质量等信息。", R.drawable.face_detect, FaceAnalyzeFragment.class.getName());

    private static final ApiItem FACE_GET_DETAIL = new ApiItem("getDetail", "人脸信息 -- Face GetDetail API", "通过传入在Detect API检测出的人脸标识face_token，获取一个人脸的关联信息，包括源图片ID、归属的FaceSet。", R.drawable.face_detect, FaceGetDetailFragment.class.getName());

    private static final ApiItem FACE_SET_USER_ID = new ApiItem("setUserId", "设置UserId -- Face SetUserID API", "为检测出的某一个人脸添加标识信息，该信息会在Search接口结果中返回，用来确定用户身份。", R.drawable.face_detect, FaceSetUserIdFragment.class.getName());

    private static final ApiItem FACESET_CREATE = new ApiItem("faceset/create", "创建人脸库 -- FaceSet Create API", "创建一个人脸的集合 FaceSet，用于存储人脸标识 face_token。一个 FaceSet 能够存储 10,000 个 face_token。", R.drawable.face_detect, FaceSetCreateFragment.class.getName());

    private static final ApiItem FACESET_ADD_FACE = new ApiItem("faceset/addface",
            "添加人脸到人脸库 -- FaceSet AddFace API",
            "为一个已经创建的 FaceSet 添加人脸标识 face_token。一个 FaceSet 最多存储10,000个 face_token。",
            R.drawable.face_detect,
            FaceSetGroupFragment.class.getName(),
            FaceSetAddFaceFragment.class.getName());

    private static final ApiItem FACESET_REMOVE_FACE = new ApiItem("faceset/removeface",
            "从人脸库删除人脸 -- FaceSet RemoveFace API",
            "移除一个FaceSet中的某些或者全部face_token。",
            R.drawable.face_detect,
            FaceSetGroupFragment.class.getName(),
            FaceSetRemoveFaceFragment.class.getName());

    private static final ApiItem FACESET_UPDATE = new ApiItem(
            "faceset/update",
            "更新人脸库 -- FaceSet Update API",
            "更新一个人脸集合的属性。",
            R.drawable.face_detect,
            FaceSetGroupFragment.class.getName(),
            FaceSetUpdateFragment.class.getName());

    private static final ApiItem FACESET_GET_DETAIL = new ApiItem(
            "faceset/getdetail",
            "人脸库详情 -- FaceSet GetDetail API",
            "获取一个 FaceSet 的所有信息，包括此 FaceSet 的 faceset_token, outer_id, display_name 的信息，以及此FaceSet 中存放的 face_token 数量与列表。单次查询最多返回 100 个 face_token。如需获取全量数据，需要配合使用 start 和 next参数。",
            R.drawable.face_detect,
            FaceSetGroupFragment.class.getName(),
            FaceSetDetailFragment.class.getName());


    static final List<ApiItem> FACE_APIS = new ArrayList<>();

    static {
        FACE_APIS.add(FACE_DETECT);
        FACE_APIS.add(FACE_COMPARE);
        FACE_APIS.add(FACE_SEARCH);
        FACE_APIS.add(FACE_BEAUTIFY);
        FACE_APIS.add(FACE_ANALYZE);
        FACE_APIS.add(FACE_GET_DETAIL);
        FACE_APIS.add(FACE_SET_USER_ID);
        FACE_APIS.add(FACESET_CREATE);
        FACE_APIS.add(FACESET_ADD_FACE);
        FACE_APIS.add(FACESET_REMOVE_FACE);
        FACE_APIS.add(FACESET_UPDATE);
        FACE_APIS.add(FACESET_GET_DETAIL);
    }

    private static final ApiItem BODY_DETECT = new ApiItem("humanbodypp/v1/detect",
            "人体检测和人体属性分析 -- HumanBody Detect API",
            "检测图片内的所有人体，并且支持对检测到的人体直接进行分析，获得每个人体的各类属性信息。",
            R.drawable.body_detect, BodyDetectFragment.class.getName());

    private static final ApiItem BODY_SEGMENT = new ApiItem("humanbodypp/v1/segment",
            "人形抠像 -- HumanBody Segment API",
            "识别传入图片中人体的完整轮廓，进行人形抠像.",
            R.drawable.body_detect, BodySegmentFragment.class.getName());

    private static final ApiItem GESTURE = new ApiItem("humanbodypp/beta/gesture",
            "手势识别 -- HumanBody Gesture API",
            "检测图片中出现的所有的手部，并返回其在图片中的矩形框位置与相应的手势含义。目前可以识别 19 种手势。",
            R.drawable.gesture, GestureFragment.class.getName());

    static final List<ApiItem> BODY_APIS = new ArrayList<>();

    static {
        BODY_APIS.add(BODY_DETECT);
        BODY_APIS.add(BODY_SEGMENT);
        BODY_APIS.add(GESTURE);
    }

    private static final ApiItem OCR_ID_CARD = new ApiItem("cardpp/v1/ocridcard",
            "身份证识别 -- OCR ID Card API",
            "检测和识别中华人民共和国第二代身份证的关键字段内容，并支持返回身份证正反面信息、身份证照片分类判断结果。",
            R.drawable.icon_id_card, OCRIDCardFragment.class.getName());

    private static final ApiItem OCR_DRIVER = new ApiItem("cardpp/v2/ocrdriverlicense",
            "驾照识别 -- OCR Driver License API (V2)",
            "检测和识别中华人民共和国机动车驾驶证（以下称“驾照”）图像，并转化为结构化的文字信息。只可识别驾照正本(main sheet)正面和副本(second sheet)正面，一张照片最多可识别一个正本正面和一个副本正面。",
            R.drawable.icon_driver, OCRDriverFragment.class.getName());

    private static final ApiItem OCR_VEHICLE = new ApiItem("cardpp/v1/ocrvehiclelicense",
            "行驶证识别 -- OCR Vehicle license API",
            "检测和识别中华人民共和国机动车行驶证（以下称“行驶证”）图像为结构化的文字信息。目前只支持行驶证主页正面，不支持副页正面反面。",
            R.drawable.icon_vehicle, OCRVehicleFragment.class.getName());

    private static final ApiItem OCR_BANK_CARD = new ApiItem("cardpp/v1/ocrbankcard",
            "银行卡识别 -- OCR Bank Card API (V1)",
            "检测和识别各类银行卡，并返回银行卡卡片边框坐标、银行卡号码、所属银行及支持的金融组织服务。支持任意角度的识别。",
            R.drawable.icon_bank, OCRBankCardFragment.class.getName());


    static final List<ApiItem> OCR_APIS = new ArrayList<>();

    static {
        OCR_APIS.add(OCR_ID_CARD);
        OCR_APIS.add(OCR_DRIVER);
        OCR_APIS.add(OCR_VEHICLE);
        OCR_APIS.add(OCR_BANK_CARD);
    }

    private static final ApiItem IMAGE_SCENE = new ApiItem("imagepp/beta/detectsceneandobject",
            "场景和物体识别 -- Detect Scene And Object API",
            "进行图片分析，识别图片场景和图片主体。",
            R.drawable.gesture, GestureFragment.class.getName());

    private static final ApiItem IMAGE_TEXT = new ApiItem("imagepp/v1/recognizetext",
            "文本识别 -- OCR ID Card API",
            "进行图片分析，找出图片中出现的文字信息。",
            R.drawable.gesture, GestureFragment.class.getName());

    private static final ApiItem IMAGE_MERGE_FACE = new ApiItem("imagepp/v1/mergeface",
            "人脸融合 -- Merge Face API (V1)",
            "使用本 API，可以对模板图和融合图中的人脸进行融合操作。融合后的图片中将包含融合图中的人脸特征，以及模板图中的其他外貌特征与内容。",
            R.drawable.gesture, GestureFragment.class.getName());

    private static final ApiItem IMAGE_PLATE = new ApiItem("cardpp/v1/ocridcard",
            "身份证识别 -- OCR ID Card API",
            "调用者传入一张图片文件或图片URL，检测并返回图片中车牌框并识别车牌颜色和车牌号。当传入图片中有多个车牌时，按照车牌框大小排序依次输出。\n 注：当前版本不支持识别新能源车牌，正努力更新中。",
            R.drawable.gesture, GestureFragment.class.getName());

    static final List<ApiItem> IMAGE_APIS = new ArrayList<>();

    static {
        IMAGE_APIS.add(IMAGE_SCENE);
        IMAGE_APIS.add(IMAGE_TEXT);
        IMAGE_APIS.add(IMAGE_MERGE_FACE);
        IMAGE_APIS.add(IMAGE_PLATE);
    }

    public enum ApiGroups {
        Face("face", FACE_APIS),
        Body("body", BODY_APIS),
        OCR("ocr", OCR_APIS),
        Image("image", IMAGE_APIS);

        private String groupName;
        private List<ApiItem> apiItems;

        public String getGroupName() {
            return groupName;
        }

        ApiGroups(String groupName, List<ApiItem> apiItems) {
            this.groupName = groupName;
            this.apiItems = apiItems;
        }

        public static List<ApiItem> getApisByGroupName(String groupName) {
            for (ApiGroups group : ApiGroups.values()) {
                if (TextUtils.equals(group.groupName, groupName)) {
                    return group.apiItems;
                }
            }
            return new ArrayList<>();
        }
    }
}
