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

public class EnumConverterTest {
    private CaseInsensitiveEnumToEnumConverter<SourceDocumentType, TargetDocumentType> caseInsensitiveEnumToEnumConverter =
            new CaseInsensitiveEnumToEnumConverter<>();
    private CaseInsensitiveStringToEnumConverter<String, ValueExposedEnum> caseInsensitiveStringToEnumConverter =
            new CaseInsensitiveStringToEnumConverter<>();

    @Test
    public void can_convert_to_target_document_type_event_the_enum_name_different_but_value_are_equal_ignore_case() {
        MoonCorpPolicy moonCorpPolicy = new MoonCorpPolicy();
        moonCorpPolicy.setDocumentType(SourceDocumentType.EXCEL_DOc);

        MapperFacade mapperFacade = mapper(MoonCorpPolicy.class, StarCorpPolicy.class);
        StarCorpPolicy starCorpPolicy = mapperFacade.map(moonCorpPolicy, StarCorpPolicy.class);

        assertThat(starCorpPolicy.getDocumentType(), is(TargetDocumentType.EXCEL_DOCUMENT));
    }


    @Test
    public void can_map_string_with_strange_uppercase_letters_to_target_document_type() {
        EarthCorpPolicy moonCorpPolicy = new EarthCorpPolicy();
        String documentTypeWithSomeUnusualUpperCase = "EXCEL DOc";
        moonCorpPolicy.setDocumentType(documentTypeWithSomeUnusualUpperCase);

        MapperFacade mapperFacade = mapper(EarthCorpPolicy.class, StarCorpPolicy.class);
        StarCorpPolicy starCorpPolicy = mapperFacade.map(moonCorpPolicy, StarCorpPolicy.class);

        assertThat(starCorpPolicy.getDocumentType(), is(TargetDocumentType.EXCEL_DOCUMENT));
    }

    private MapperFacade mapper(Class<?> aType, Class<StarCorpPolicy> bType) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(
                caseInsensitiveEnumToEnumConverter);
        mapperFactory.getConverterFactory().registerConverter(
                caseInsensitiveStringToEnumConverter);
        mapperFactory.classMap(aType, bType)
                .byDefault().register();
        return mapperFactory.getMapperFacade();
    }
}
