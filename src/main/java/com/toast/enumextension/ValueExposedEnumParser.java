package com.toast.enumextension;

import com.toast.enumextension.match.ValueEqualIgnoreCase;
import com.toast.enumextension.match.base.EnumMatchStrategy;

/**
 * Created by lbwang on 9/2/16.
 */
public class ValueExposedEnumParser {
    public static <T extends ValueExposedEnum> T parseIgnoreCase(String value, Class<T> type) {
        EnumMatchStrategy ignoreCase = ValueEqualIgnoreCase.getInstance();
        return parse(value, type, ignoreCase);
    }

    static <T extends ValueExposedEnum> T parse(String value, Class<T> enumType, EnumMatchStrategy enumMatchStrategy) {
        for (T constant : enumType.getEnumConstants()) {
            if (enumMatchStrategy.isMatch(value, constant)) {
                return constant;
            }
        }
        throw new IllegalArgumentException("Unknown Type:" + value);
    }

}
