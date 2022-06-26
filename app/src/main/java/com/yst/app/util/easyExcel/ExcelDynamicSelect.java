package com.yst.app.util.easyExcel;

/**
 * @creator: ly-yangst
 * @date: 2022/6/23
 */
public interface ExcelDynamicSelect {
    /**
     * 获取动态生成的下拉框可选数据
     * @return 动态生成的下拉框可选数据
     */
    String[] getSource();
}
