package com.hz.management.view;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class StorageView implements Serializable {
    private String stock;
    private List<DetailsView> details;

    public StorageView() {
    }

    public StorageView(String stock, List<DetailsView> detailsList) {
        this.stock = stock;
        this.details = detailsList;
    }
}
