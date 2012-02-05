package org.lazydevs.veetle.api.util;

import net.entropysoft.transmorph.ConverterException;
import net.entropysoft.transmorph.DefaultConverters;
import net.entropysoft.transmorph.Transmorph;
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
 * The lazy devs solution for parsing JSON values into primitives
 */
public class JSONUtil {

    private static final Logger log = Logger.getLogger(JSONUtil.class.getSimpleName());

    public static String getStringValue(JSONObject object, String key) {

        Object value = object.get(key);
        String result = null;

        try {
            result = new Transmorph(new DefaultConverters()).convert(value, String.class);
        } catch (ConverterException e) {
            log.log(Level.WARNING, "Error parsing string value: " + value);
        }

        return result;
    }

    public static Integer getIntegerValue(JSONObject object, String key) {

        Object value = object.get(key);
        Integer result = null;

        try {
            result = new Transmorph(new DefaultConverters()).convert(value, Integer.class);
        } catch (ConverterException e) {
            log.log(Level.WARNING, "Error parsing integer value: " + value);
        }

        return result;
    }

    public static Long getLongValue(JSONObject object, String key) {

        Object value = object.get(key);
        Long result = null;

        try {
            result = new Transmorph(new DefaultConverters()).convert(value, Long.class);
        } catch (ConverterException e) {
            log.log(Level.WARNING, "Error parsing long value: " + value);
        }

        return result;
    }

    public static Boolean getBooleanValue(JSONObject object, String key) {

        Object value = object.get(key);
        Boolean result = null;

        try {
            result = new Transmorph(new DefaultConverters()).convert(value, Boolean.class);
        } catch (ConverterException e) {
            log.log(Level.WARNING, "Error parsing boolean value: " + value);
        }

        return result;
    }
}
