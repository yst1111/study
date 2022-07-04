package com.yst.app.controller.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.fastjson.JSON;
import com.yst.app.controller.sheetWriteHandle.CustomSheetWriteHandler;
import com.yst.app.util.ExcelUtils;
import com.yst.app.controller.user.DownloadData;
import com.yst.entity.dto.UploadDAO;
import com.yst.entity.pojo.UploadData;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @creator: ly-yangst
 * @date: 2022/6/22
 */
@Slf4j
@Api(tags = "easyExcel")
@RestController
@RequestMapping(value = "easyExcel")
@ComponentScan({"com.yst.fira.repo"})
public class easyExcelController {

    @Autowired
    private UploadDAO uploadDAO;

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DownloadData}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        List<DownloadData> data = data();
        String Name = "down";
        ExcelUtils.export(Name,data,response);
    }

    @GetMapping("downloadFailedUsingJson")
    public void downloadFailedUsingJson(HttpServletResponse response) throws IOException {
        String name = "downloadFailedUsingJson";
        List<DownloadData> data = data();//数据来源
        ExcelWriterBuilder write = ExcelUtils.easyExcelExport(name, DownloadData.class, response);
        write.sheet().doWrite(data);
    }

    private List<DownloadData> data() {
        List<DownloadData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DownloadData data = new DownloadData();
            data.setString("字符串" + 0);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }


    @PostMapping("upload")
    @ResponseBody
    public List<DownloadData> upload(MultipartFile file) throws IOException {
        List<DownloadData> listReadData = new ArrayList<>();
        EasyExcel.read(file.getInputStream(),DownloadData.class,new PageReadListener<DownloadData>(dataList -> {
            for (DownloadData demoData : dataList) {
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
                listReadData.add(demoData);
            }
        })).sheet().doRead();
//        log.info("解析结果:{}"+listReadData);
        return listReadData;
    }


}
