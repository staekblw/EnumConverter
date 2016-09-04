package com.toast.enumextension.match;

import com.toast.enumextension.match.base.MatchStrategy;
import com.toast.enumextension.ValueExposedEnum;

/**
 * Created by lbwang on 9/2/16.
 */
public class IgnoreCase implements MatchStrategy {
    @Override
    public <T extends ValueExposedEnum> boolean isMatch(String value, T constant) {
        return constant.getValue().equalsIgnoreCase(value);
    }
}
