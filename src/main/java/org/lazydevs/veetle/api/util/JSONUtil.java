package org.lazydevs.veetle.api.util;

import org.json.simple.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Sascha
 * Date: 28.05.11
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 *
 * Utility methods for parsing several primitive types from JSON strings
 */
public class JSONUtil {

    private static final Logger log = Logger.getLogger(JSONUtil.class.getSimpleName());

    public static String getStringValue(JSONObject object, String key) {

        return String.valueOf(object.get(key));
    }

    public static Integer getIntegerValue(JSONObject object, String key) {

        Object value = object.get(key);
        Integer result = null;

        try {
            result = Integer.parseInt(String.valueOf(value));
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Error parsing integer value: " + value);
        }

        return result;
    }

    public static Long getLongValue(JSONObject object, String key) {

        Object value = object.get(key);
        Long result = null;

        try {
            result = Long.parseLong(String.valueOf(value));
        } catch (NumberFormatException e) {
            log.log(Level.WARNING, "Error parsing long value: " + value);
        }

        return result;
    }

    public static Boolean getBooleanValue(JSONObject object, String key) {

        return Boolean.parseBoolean(String.valueOf(object.get(key)));
    }
}
