package com.lilithsthrone.utils;

import java.util.List;

public class ArrayUtil {

    public static String[] convertToArray(List<String> list){
        return (String[]) list.toArray(new String[0]);
    }
    
}