package com.hz.management.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.hz.management.entity.Storage;
import com.hz.management.service.StorageService;
import com.hz.management.view.DataView;
import com.hz.management.view.StorageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/add/storage/list")
    public DataView addStorageList(
            @RequestBody List<StorageView> list
    ){

        return storageService.addStorageList(list);
    }

    @GetMapping("/storage/list")
    public DataView storageList(
            @RequestParam(name = "currentPage",defaultValue = "1") int pageNo,
            @RequestParam(defaultValue = "1") int pageSize,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "") String time
    ){
        return storageService.storageList(pageNo,pageSize,query,time);
    }
}
