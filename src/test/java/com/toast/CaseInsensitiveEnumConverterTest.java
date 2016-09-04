package com.toast;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import com.toast.converter.CaseInsensitiveEnumToEnumConverter;
import com.toast.converter.CaseInsensitiveStringToEnumConverter;
import com.toast.enumextension.ValueExposedEnum;
import com.toast.example.model.EarthCorpPolicy;
import com.toast.example.model.MoonCorpPolicy;
import com.toast.example.model.StarCorpPolicy;
import com.toast.example.type.SourceDocumentType;
import com.toast.example.type.TargetDocumentType;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.Test;

public class CaseInsensitiveEnumConverterTest {

    @Test
    public void can_convert_to_target_document_type_event_the_enum_name_different_but_value_are_equal_ignore_case() {
        MoonCorpPolicy moonCorpPolicy = new MoonCorpPolicy();
        moonCorpPolicy.setDocumentType(SourceDocumentType.EXCEL_DOc);

        MapperFacade mapperFacade = mapper(MoonCorpPolicy.class, StarCorpPolicy.class);
        StarCorpPolicy starCorpPolicy = mapperFacade.map(moonCorpPolicy, StarCorpPolicy.class);

        assertThat(starCorpPolicy.getDocumentType(), is(TargetDocumentType.EXCEL_DOCUMENT));
    }


    @Test
    public void can_convert_string_to_target_document_type_as_long_as_string_and_enum_value_equal_ignore_case() {
        EarthCorpPolicy moonCorpPolicy = new EarthCorpPolicy();
        String documentTypeWithSomeUnusualUpperCase = "EXCEL DOc";
        moonCorpPolicy.setDocumentType(documentTypeWithSomeUnusualUpperCase);

        MapperFacade mapperFacade = mapper(EarthCorpPolicy.class, StarCorpPolicy.class);
        StarCorpPolicy starCorpPolicy = mapperFacade.map(moonCorpPolicy, StarCorpPolicy.class);

        assertThat(starCorpPolicy.getDocumentType(), is(TargetDocumentType.EXCEL_DOCUMENT));
    }

    private MapperFacade mapper(Class<?> sourceType, Class<StarCorpPolicy> targetType) {
        CaseInsensitiveEnumToEnumConverter<SourceDocumentType, TargetDocumentType> sourceDocumentTypeToTargetDocumentTypeConverter =
                new CaseInsensitiveEnumToEnumConverter<>();
        CaseInsensitiveStringToEnumConverter<String, ValueExposedEnum> stringToTargetDocumentTypeConverter =
                new CaseInsensitiveStringToEnumConverter<>();
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(
                sourceDocumentTypeToTargetDocumentTypeConverter);
        mapperFactory.getConverterFactory().registerConverter(
                stringToTargetDocumentTypeConverter);
        mapperFactory.classMap(sourceType, targetType)
                .byDefault().register();
        return mapperFactory.getMapperFacade();
    }
}
