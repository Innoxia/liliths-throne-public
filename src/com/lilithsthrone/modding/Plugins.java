package com.lilithsthrone.modding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Only for use in game-side code.
 * 
 * Uses reflection to find PluginLoader to avoid dependency recursion.
 */
public class Plugins {
    public static final String INVOKETARGET_MAIN_MAIN = "onMainMain";

    private static final String METHOD_GETINSTANCE = "getInstance";
    private static final String PLUGIN_LOADER_CLASSID = "com.lilithsthrone.modding.PluginLoader";
    
    private static Object instance = null;
    private static Class<?> cls = null;
    
    private static Object getInstance() {
        if(instance == null) {
            try {
                cls = Class.forName(PLUGIN_LOADER_CLASSID);
                Method m = cls.getDeclaredMethod(METHOD_GETINSTANCE);
                instance = m.invoke(null);
            } catch(Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return instance;
    }

    public static void Invoke(String methodName) {
        try {
            cls.getDeclaredMethod(methodName).invoke(getInstance());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
