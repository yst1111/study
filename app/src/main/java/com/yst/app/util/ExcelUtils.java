package com.yst.app.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.io.IoUtil;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @creator: ly-yangst
 * @date: 2022/6/23
 */
@Slf4j
public class ExcelUtils {
    public static <T> void export(String FileName, List<T> list, HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        ServletOutputStream out = response.getOutputStream();
        try {
            String fileName = generatorDocName(FileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            //此处记得关闭输出Servlet流
            IoUtil.close(out);
        } catch (Exception e) {
            log.error("生成excel报错：" + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
                out.flush();
            }
        }

    }

    public static <T> ExcelWriterBuilder easyExcelExport(String FileName, Class<T> t,  HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
//        String fileName = URLEncoder.encode("测试", "UTF-8");//.replaceAll("\\+", "%20");
        String fileName = generatorDocName(FileName);
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        ServletOutputStream outputStream = response.getOutputStream();


        ExcelWriterBuilder write = EasyExcel.write(outputStream, t);

        return write;

    }

        //文件名生成
    private static String generatorDocName(String fileName) throws UnsupportedEncodingException {
        String formatDate = new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN).format(new Date());
        log.info("formatDate: {}", formatDate);
        fileName = URLEncoder.encode(fileName + formatDate + ".xlsx", "UTF-8");
        return fileName;
    }

}
