package com.qianfeng.smsplatform.webmaster.util;

import lombok.Data;

import java.util.List;

/**
 * 前端页面表格用到的数据格式
 *
 * @param <T>
 */
@Data
public class TableData<T> {
    private long total;//总条数
    private List<T> rows;//表格数据

    public TableData() {

    }

    public TableData(long total, List<T> data) {
        this.total = total;
        this.rows = data;
    }
}
