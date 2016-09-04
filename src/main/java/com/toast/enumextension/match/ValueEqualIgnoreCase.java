package com.toast.enumextension.match;

import com.toast.enumextension.match.base.EnumMatchStrategy;
import com.toast.enumextension.ValueExposedEnum;

/**
 * Created by lbwang on 9/2/16.
 */
public class ValueEqualIgnoreCase implements EnumMatchStrategy {
    private static EnumMatchStrategy instance = new ValueEqualIgnoreCase();

    public static EnumMatchStrategy getInstance() {
        return instance;
    }

    @Override
    public <T extends ValueExposedEnum> boolean isMatch(String value, T enumConstant) {
        return enumConstant.getValue().equalsIgnoreCase(value);
    }

}
