package top.yms.mas.utils;


import java.util.Map;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.google.common.collect.Maps;

/**
 * 记录线程信息
 *
 * @author esr
 *
 */
public class LocalThreadUtils {

    private static ThreadLocal<Map<String, Object>> threadLocalValue = new TransmittableThreadLocal<>();
    //

    public static final void set(Map<String, Object> values) {
        threadLocalValue.set(values);
    }

    public static final Map<String, Object> get() {
        Map<String, Object> m = threadLocalValue.get();
        if (m == null)
            m = Maps.newHashMap();
        return m;
    }

    public static final void remove() {
        threadLocalValue.get().clear();
        threadLocalValue.remove();
    }
}
