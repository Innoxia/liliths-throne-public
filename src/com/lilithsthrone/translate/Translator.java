/*
 * F5Loser
 * Copyright (c) 2021-2022 All Rights Reserved
 */
package com.lilithsthrone.translate;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/**
 * @Author yuhang
 * @Date 2022-11-10 15:22
 * @Description
 */
public class Translator {

    private static final Set<String> SYMBOL_CHECK_SET = Sets.newHashSet(
            ":",
            "!",
            ".",
            ",",
            "_",
            "#",
            "@",
            "=",
            "-",
            "`",
            "~",
            "^",
            "/",
            "%",
            "&",
            "*",
            "+",
            "|",
            "$"
    );

    private static final Map<String, String> MONTH_CONVERT_MAP = Maps.newHashMap();

    private static final Map<String, String> FULL_CONVERT_MAP = Maps.newHashMap();
    private static final Splitter SPLITTER_SPACE = Splitter.on(" ").omitEmptyStrings();

    static {
        MONTH_CONVERT_MAP.put("January", "一月");
        MONTH_CONVERT_MAP.put("February", "二月");
        MONTH_CONVERT_MAP.put("March", "三月");
        MONTH_CONVERT_MAP.put("April", "四月");
        MONTH_CONVERT_MAP.put("May", "五月");
        MONTH_CONVERT_MAP.put("June", "六月");
        MONTH_CONVERT_MAP.put("July", "七月");
        MONTH_CONVERT_MAP.put("August", "八月");
        MONTH_CONVERT_MAP.put("September", "九月");
        MONTH_CONVERT_MAP.put("October", "十月");
        MONTH_CONVERT_MAP.put("November", "十一月");
        MONTH_CONVERT_MAP.put("December", "十二月");

        FULL_CONVERT_MAP.put("ON", "ON");
        FULL_CONVERT_MAP.put("OFF", "OFF");
    }

    public static String translate(String domString) {
        if (false) {
            return domString.replace("\u200B", "");
        }
        if (StrUtil.isBlank(domString)) {
            return domString;
        }

        try {
            Document parse = Jsoup.parse(domString);
            Element body = parse.select("body").get(0);
            translate(body);
            return body.toString()
                    .replace("\"", "'")
                    .replaceAll("[\r\n]", "")
                    ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return domString;
    }


    public static void translate(org.jsoup.nodes.Node node) {
        String parentName = Optional.ofNullable(node.parent())
                .map(org.jsoup.nodes.Node::nodeName).orElse("");
        if ("script".equals(parentName)) {
            return;
        }
        if (node instanceof TextNode textNode) {
            AtomicReference<String> text = new AtomicReference<>(textNode.text());
            if (StrUtil.isNotBlank(text.get()) && translatePreCheck(text)) {
                textNode.text(doTrans(text));
            } else {
                textNode.text(text.get());
            }
        }

        List<org.jsoup.nodes.Node> childNodes = node.childNodes();
        for (org.jsoup.nodes.Node childNode : childNodes) {
            translate(childNode);
        }
    }

    private static boolean translatePreCheck(AtomicReference<String> value) {
        if (StrUtil.isBlankOrUndefined(value.get())) {
            return false;
        }
        if (value.get().length() <= 2) {
            return false;
        }
        if (NumberUtil.isNumber(value.get())) {
            return false;
        }
        if (FULL_CONVERT_MAP.containsKey(value.get())) {
            value.set(FULL_CONVERT_MAP.get(value.get()));
            return false;
        }
        if (value.get().startsWith("Current time:")) {
            return false;
        }
        if (value.get().length() < 10 && (value.get().startsWith("CTRL") || value.get().startsWith("SHIFT"))) {
            return false;
        }
        if (StrUtil.isAllCharMatch(StrUtil.cleanBlank(value.get()),
                str -> SYMBOL_CHECK_SET.contains(str.toString()) ||
                        NumberUtil.isNumber(str.toString()))) {
            return false;
        }
        List<String> strings = SPLITTER_SPACE.splitToList(value.get());
        if (strings.size() == 2 && NumberUtil.isNumber(strings.get(0))) {
            return false;
        }
        if (strings.size() == 3 && NumberUtil.isInteger(strings.get(2)) && MONTH_CONVERT_MAP.containsKey(
                strings.get(1))) {
            value.set(String.join(" ", strings.get(0), MONTH_CONVERT_MAP.get(strings.get(1)), strings.get(2)));
            return false;
        }

        if (Pattern.compile("[\u4e00-\u9fa5]").matcher(value.get()).find()) {
            return false;
        }
        return true;
    }

    private static String doTrans(AtomicReference<String> value) {
        String originText = value.get();
        try {
            return originText + "T";
        } catch (Exception e) {
            System.out.println("translate error: " + originText);
            e.printStackTrace();
            return originText;
        }
    }

}
