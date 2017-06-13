package com.netease.ssm.util;

import com.netease.ssm.pojo.VideoUrlType;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by bjzhangxicheng on 2017/5/26.
 */
public class RandomUrlUtil {

    // 放大倍数
    private static final int mulriple = 1000000;

    public static int pay(List<VideoUrlType> types) {
        int lastScope = 0;
        // 洗牌，打乱url类型次序
        Collections.shuffle(types);
        Map<Integer, int[]> typeScopes = new HashMap<Integer, int[]>();

        for (VideoUrlType videoUrlType : types) {
            int typeId = videoUrlType.getId();
            // 划分区间
            int currentScope = lastScope + videoUrlType.getProbability().multiply(new BigDecimal(mulriple)).intValue();
            typeScopes.put(typeId, new int[] { lastScope + 1, currentScope });

            lastScope = currentScope;
        }

        // 获取1-1000000之间的一个随机数
        int luckyNumber = new Random().nextInt(mulriple);
        int luckyTypeId = 0;
        // 查找随机数所在的区间
        if ((null != typeScopes) && !typeScopes.isEmpty()) {
            Set<Map.Entry<Integer, int[]>> entrySets = typeScopes.entrySet();
            for (Map.Entry<Integer, int[]> m : entrySets) {
                int key = m.getKey();
                if (luckyNumber >= m.getValue()[0] && luckyNumber <= m.getValue()[1] ) {
                    luckyTypeId = key;
                    break;
                }
            }
        }

        return luckyTypeId;
    }
}
