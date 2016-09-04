package com.toast.converter;

import com.toast.enumextension.ValueExposedEnum;
import com.toast.enumextension.ValueExposedEnumParser;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Created by lbwang on 9/2/16.
 */
public class CaseInsensitiveStringToEnumConverter<S extends String, T extends ValueExposedEnum>
        extends CustomConverter<S, T> {
    @Override
    public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
        return String.class.isAssignableFrom(sourceType.getRawType())
                || ValueExposedEnum.class.isAssignableFrom(destinationType.getRawType());
    }

    @Override
    public T convert(S source, Type<? extends T> destinationType) {
        return ValueExposedEnumParser.parseIgnoreCase(source, destinationType.getRawType());
    }
}
