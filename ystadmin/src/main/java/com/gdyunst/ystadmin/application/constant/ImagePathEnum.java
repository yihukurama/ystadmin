package com.gdyunst.ystadmin.application.constant;

/**
 * 功能描述: 服务器存储图片类型(路径)
 *
 * @Author: 蔡伟浩
 * @Date: 2017/1/13 14:19
 */
public enum ImagePathEnum {
    USER_IMG_AVATAR(14, "用户头像","/USER_IMG_AVATAR"),
    OTHER_IMG(199, "other", "其他"),

    ;

    //类型
    private int type;
    //标识
    private String tag;
    //说明
    private String dir;

    ImagePathEnum(int type, String tag, String dir) {
        this.type = type;
        this.tag = tag;
        this.dir = dir;
    }

    public int getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }

    public String getDir() {
        return dir;
    }

    public static ImagePathEnum buildByType(Integer type) {
        if (type == null) {
            return null;
        }
        for (ImagePathEnum ImagePathEnum : ImagePathEnum.values()) {
            if (ImagePathEnum.getType() == type) {
                return ImagePathEnum;
            }
        }
        return null;
    }


}
