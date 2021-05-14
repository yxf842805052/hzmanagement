package com.hz.management.tool;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BeanCopyUtil extends BeanUtils {
    /**
     * 集合数据的拷贝     * @param sources: 数据源类     * @param target: 目标类::new(eg: UserVO::new)     * @return
     */
    public static List copyListProperties(List sources, Supplier target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 带回调函数的集合数据的拷贝(可自定义字段拷贝规则)     * @param sources: 数据源类     * @param target: 目标类::new(eg: UserVO::new)     * @param callBack: 回调函数     * @return
     */
    public static List copyListProperties(List sources, Supplier target, BeanCopyUtilCallBack callBack) {
        List list = new ArrayList<Object>(sources.size());
        for (Object source : sources) {
            Object t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) { // 回调
                 callBack.callBack(source, t);
            }
        }
        return list;
    }
}