package com.toast.converter;

import com.toast.enumextension.ValueExposedEnum;
import com.toast.enumextension.ValueExposedEnumParser;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

/**
 * Created by lbwang on 9/2/16.
 */
public class CaseInsensitiveEnumToEnumConverter<S extends ValueExposedEnum, T extends ValueExposedEnum>
        extends BidirectionalConverter<S, T> {
    @Override
    public boolean canConvert(Type<?> sourceType, Type<?> destinationType) {
        return ValueExposedEnum.class.isAssignableFrom(sourceType.getRawType())
                && ValueExposedEnum.class.isAssignableFrom(destinationType.getRawType());
    }

    @Override
    public T convertTo(S source, Type<T> destinationType) {
        return ValueExposedEnumParser.parseIgnoreCase(source.getValue(), destinationType.getRawType());

    }

    @Override
    public S convertFrom(T source, Type<S> destinationType) {
        return ValueExposedEnumParser.parseIgnoreCase(source.getValue(), destinationType.getRawType());
    }
}
