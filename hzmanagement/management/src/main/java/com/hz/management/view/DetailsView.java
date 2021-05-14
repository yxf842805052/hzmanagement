package com.hz.management.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class DetailsView implements Serializable {
    private String specification;
    private String unit;
    private Integer amount;
    private boolean status;


    public DetailsView() {
    }
}
