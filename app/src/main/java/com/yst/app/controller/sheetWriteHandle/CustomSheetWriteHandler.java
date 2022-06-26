package com.yst.app.controller.sheetWriteHandle;

import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.handler.context.SheetWriteHandlerContext;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

/**
 * 自定义拦截器.对第一列第一行和第二行的数据新增下拉框，显示 测试1 测试2
 *
 * @author Jiaju Zhuang
 */
@Slf4j
public class CustomSheetWriteHandler implements SheetWriteHandler {

    @Override
    public void afterSheetCreate(SheetWriteHandlerContext context) {
        log.info("第{}个Sheet写入成功。", context.getWriteSheetHolder().getSheetNo());

        // 区间设置 第一列第一行和第二行的数据。由于第一行是头，所以第一、二行的数据实际上是第二三行
        DataValidationHelper helper = context.getWriteSheetHolder().getSheet().getDataValidationHelper();

        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 1000, 0, 0);
        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[] {"乘用车", "商用车"});

        DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
//        dataValidation.
//        context.getWriteSheetHolder().getSheet().addValidationData(dataValidation);
        WriteSheetHolder writeSheetHolder = context.getWriteSheetHolder();
//        Sheet sheet = writeSheetHolder.getSheet();
//        sheet.addValidationData(dataValidation);
//        List<? extends DataValidation> dataValidations = sheet.getDataValidations();

    }
}
