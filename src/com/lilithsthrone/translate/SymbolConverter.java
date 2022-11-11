/*
 * F5Loser
 * Copyright (c) 2021-2022 All Rights Reserved
 */
package com.lilithsthrone.translate;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import javafx.util.Pair;

import java.util.List;

/**
 * @Author yuhang
 * @Date 2022-11-10 16:15
 * @Description
 */
public class SymbolConverter {

    private static final List<Pair<String, String>> REGISTER_SYMBOL = Lists.newArrayList(
            //            new Pair<>("<", "&lt;"),
            //            new Pair<>(">", "&gt;"),
            new Pair<>("&", "&amp;")
            //            new Pair<>("'", "&apos;"),
            //            new Pair<>("\"", "&quot;")
    );

    public static String convertStr(String str) {
        if (StrUtil.isBlank(str)) {
            return str;
        }
        String result = str;
        for (Pair<String, String> pair : REGISTER_SYMBOL) {
            result = result.replace(pair.getKey(), pair.getValue());
        }
        return result;
    }

    public static String revertStr(String str) {
        if (StrUtil.isBlank(str)) {
            return str;
        }
        String result = str;
        for (Pair<String, String> pair : REGISTER_SYMBOL) {
            result = result.replace(pair.getValue(), pair.getKey());
        }
        return result;
    }

}
