package com.yst.app.util;

/**
 * @creator: ly-yangst
 * @date: 2022/6/23
 */

import org.apache.poi.util.IOUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class FilePathUtil {


    public static InputStream getResourcesFileInputStream(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
    }

    public static String getPath() {
        return FilePathUtil.class.getResource("/").getPath();
    }

    public static File createNewFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
        }
        return file;
    }

    public static File readFile(String pathName) {
        return new File(getPath() + pathName);
    }

    public static File readUserHomeFile(String pathName) {
        return new File(System.getProperty("user.home") + File.separator + pathName);
    }

    public static File deleteFile(String pathName) {
        File file = new File(getPath() + pathName);
        if (file.exists()) {
            file.delete();
        }
        return file;
    }

    //excel文件输出
    public static void fileExport(String pathName, HttpServletResponse response) throws UnsupportedEncodingException {
        String FileTotalPath = new File(getPath() + pathName).toString();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/vnd.ms-excel");
        String fileName = URLEncoder.encode(FileTotalPath, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        File file = new File(FileTotalPath);

        if(file.exists()&&file.isFile()){
            try {
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream();//获取response的输出流对象
                IOUtils.copy(inputStream,outputStream);
                //刷新输出流
                outputStream.flush();
                //关闭输出流
                outputStream.close();
                inputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}

