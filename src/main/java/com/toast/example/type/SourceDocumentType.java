package com.toast.example.type;

import com.toast.enumextension.ValueExposedEnum;

/**
 * Created by lbwang on 9/2/16.
 */
public enum SourceDocumentType implements ValueExposedEnum {
    WORD_DOC("word doc"), EXCEL_DOc("excel doC");
    private String value;


    SourceDocumentType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
