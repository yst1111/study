package com.yst.app.util.easyExcel;

import com.yst.entity.pojo.UploadData;

/**
 * @creator: ly-yangst
 * @date: 2022/6/23
 */
//动态下拉框中的数据配置类
public class MyExcelSelected implements ExcelDynamicSelect{

    @Override
    public String[] getSource() {
        //查询下拉框中需要的数据
        UploadData productMapper = SpringContextUtil.getBean(UploadData.class);
        return new String[] {"乘用车", "商用车"};
        //当多列需要动态下拉框时，只需自定义类实现ExcelDynamicSelect中的方法，并在方法中查询数据即可。
//        return new String[]{};
    }
}

