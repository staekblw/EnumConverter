package com.toast.enumextension;

import com.toast.enumextension.match.IgnoreCase;
import com.toast.enumextension.match.base.MatchStrategy;

/**
 * Created by lbwang on 9/2/16.
 */
public class ValueExposedEnumParser {
    public static <T extends ValueExposedEnum> T parseIgnoreCase(String value, Class<T> type) {
        MatchStrategy ignoreCase = new IgnoreCase();
        return parse(value, type, ignoreCase);
    }

    static <T extends ValueExposedEnum> T parse(String value, Class<T> enumType, MatchStrategy matchStrategy) {
        for (T constant : enumType.getEnumConstants()) {
            if (matchStrategy.isMatch(value, constant)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Unknown Type:" + value);
    }

}
