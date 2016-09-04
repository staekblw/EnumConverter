package com.toast.example.type;

import com.toast.enumextension.ValueExposedEnum;

/**
 * Created by lbwang on 9/2/16.
 */
public enum TargetDocumentType implements ValueExposedEnum {
    WORD_DOC("word doc"), EXCEL_DOCUMENT("excel doc");
    private String value;


    TargetDocumentType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
