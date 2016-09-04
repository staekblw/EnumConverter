package com.toast.enumextension.match.base;

import com.toast.enumextension.ValueExposedEnum;

/**
 * check if the String value match the Enum Constant
 */
public interface MatchStrategy {
    <T extends ValueExposedEnum> boolean isMatch(String value, T constant);
}


