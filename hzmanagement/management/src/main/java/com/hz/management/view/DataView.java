package com.hz.management.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataView {

    private Object data;
    private Integer code;
    private String error;

    public DataView() {
    }

    public DataView(Object data, Integer code) {
        this.data = data;
        this.code = code;
    }

    public DataView(Integer code, String error) {
        this.code = code;
        this.error = error;
    }
}
