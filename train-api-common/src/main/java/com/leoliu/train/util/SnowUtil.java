package com.leoliu.train.util;

import cn.hutool.core.util.IdUtil;

/**
 * hutool雪花算法
 */
public class SnowUtil {
    private static long dataCenterId = 1;//数据中心
    private static long workedId = 1; //机器标识
    public static long getSnowflakeNextId(){
        return IdUtil.getSnowflake(workedId,dataCenterId).nextId();
    }
    public static String getSnowflakeNextIdStr(){
        return IdUtil.getSnowflake(workedId,dataCenterId).nextIdStr();
    }
}
