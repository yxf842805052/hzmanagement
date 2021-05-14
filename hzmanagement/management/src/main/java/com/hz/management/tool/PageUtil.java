package com.hz.management.tool;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageUtil<T> {
    private int pageSize; // 每页显示多少条记录

    private int pageNo; //当前第几页数据

    private int total; // 一共多少条记录

    private int totalPage; // 一共多少页

    private List<T> data; //要显示的数据

    public PageUtil(int pageNo, int pageSize, int total, List<T> sourceList) {
        if (sourceList == null || sourceList.isEmpty()) {
            return;
        }

        // 总记录条数
        this.total = total;

        // 每页显示多少条记录
        this.pageSize = pageSize;

        //获取总页数
        this.totalPage = this.total / this.pageSize;
        if (this.total % this.pageSize != 0) {
            this.totalPage = this.totalPage + 1;
        }

        // 当前第几页数据
        this.pageNo = this.totalPage < pageNo ? this.totalPage : pageNo;

//        // 起始索引
//        int fromIndex = this.pageSize * (this.pageNo - 1);
//
//        // 结束索引
//        int toIndex = this.pageSize * this.pageNo > this.total ? this.total : this.pageSize * this.total;

        this.data = sourceList;
    }

    private PageUtil() {
    }

    public PageUtil(int pageSize, int pageNo, int total, int totalPage,
                    List<T> data) {
        super();
        this.pageSize = pageSize;
        this.pageNo = pageNo;
        this.total = total;
        this.totalPage = totalPage;
        this.data = data;
    }

}
