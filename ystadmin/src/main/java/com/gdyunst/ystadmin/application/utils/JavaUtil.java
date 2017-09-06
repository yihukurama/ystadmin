package com.gdyunst.ystadmin.application.utils;

import java.util.Collection;

public class JavaUtil {

    /**
     * 功能描述:判断集合是否为null或者空
     * @param c
     * @return
     * @Author:dengshuai
     * @Date:2017年9月1日 下午1:40:28
     */
    public static Boolean isEmpty(Collection<?> c){
        if(c == null){
            return true;
        }

        if(c.size() == 0){
            return true;
        }
        
        return false;
    }
}
