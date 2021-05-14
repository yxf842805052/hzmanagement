package com.hz.management.service;

import com.hz.management.view.DataView;
import com.hz.management.view.StorageView;

import java.util.List;

public interface StorageService {
    DataView addStorageList(List<StorageView> storageViewList);

    DataView storageList(int pageNo, int pageSize, String query, String time);
}
