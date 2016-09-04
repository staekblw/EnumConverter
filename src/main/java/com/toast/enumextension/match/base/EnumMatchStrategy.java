package com.toast.enumextension.match.base;

import com.toast.enumextension.ValueExposedEnum;

/**
 * check if the String value match the Enum Constant
 */
public interface EnumMatchStrategy {
    <T extends ValueExposedEnum> boolean isMatch(String value, T enumConstant);
}


