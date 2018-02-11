package com.gdyunst.ystadmin.framework.service.domainservice.admin.impl;

import com.gdyunst.ystadmin.application.config.SystemConfig;
import com.gdyunst.ystadmin.application.constant.FilePathEnum;
import com.gdyunst.ystadmin.application.constant.ImagePathEnum;
import com.gdyunst.ystadmin.application.utils.EmptyUtil;
import com.gdyunst.ystadmin.application.utils.FileUtil;
import com.gdyunst.ystadmin.application.utils.StringUtil;
import com.gdyunst.ystadmin.framework.service.domainservice.admin.IFileUpload;
import com.gdyunst.ystadmin.framework.web.restful.admin.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 继承文件上传接口
 * @author: dengshuai
 * @date: Created in 10:54 2018/1/22
 * @modified: by autor in 10:54 2018/1/22
 */
public class FileUploadService implements IFileUpload {

    @Autowired
    SystemConfig sysConfig;

    @Override
    public Result uploadFile(MultipartFile file, FilePathEnum filePathEnum) throws IOException {
        String rootPath = sysConfig.getStaticUrl()+filePathEnum.getDir();
        String fileName = file.getName()+ StringUtil.getNum()+filePathEnum.getSuffix();
        String absPath = FileUtil.fileWrite(new ByteArrayInputStream(file.getBytes()), rootPath, fileName);

        absPath = absPath.substring(sysConfig.getStaticUrl().length());
        if (!EmptyUtil.isEmpty(absPath)) {
            return Result.successed(absPath);
        } else {
            return Result.failed("文件上传失败");
        }
    }

    @Override
    public Result uploadPicBase64(String base64File, FilePathEnum filePathEnum, ImagePathEnum imagePathEnum) {
        String rootPath = sysConfig.getStaticUrl()+filePathEnum.getDir()+imagePathEnum.getDir();
        String fileName = imagePathEnum.getType()+StringUtil.getNum()+filePathEnum.getSuffix();
        InputStream input = FileUtil.base64ToInputString(base64File);
        if (input == null) {
            return Result.failed("文件上传失败");
        }
        String absPath = FileUtil.fileWrite(input, rootPath, fileName);
        absPath = absPath.substring(sysConfig.getStaticUrl().length());
        if (!EmptyUtil.isEmpty(absPath)) {
            return Result.successed(absPath);
        } else {
            return Result.failed("Form表单上传图片失败");
        }
    }

    @Override
    public Result uploadPicForm(MultipartFile file, FilePathEnum filePathEnum, ImagePathEnum imagePathEnum) throws IOException {
        String rootPath = sysConfig.getStaticUrl()+filePathEnum.getDir()+imagePathEnum.getDir();
        String fileName = file.getName()+ StringUtil.getNum()+filePathEnum.getSuffix();
        String absPath = FileUtil.fileWrite(new ByteArrayInputStream(file.getBytes()), rootPath, fileName);
        absPath = absPath.substring(sysConfig.getStaticUrl().length());
        if (!EmptyUtil.isEmpty(absPath)) {
            return Result.successed(absPath);
        } else {
            return Result.failed("Form表单上传图片失败");
        }
    }

    @Override
    public Result doGetPicBase64(MultipartFile file) throws IOException {

        String base64Data = FileUtil.inputToBase64(file.getInputStream());
        if (!EmptyUtil.isEmpty(base64Data)) {

            return Result.successed(base64Data);
        } else {
            return Result.failed("获取文件Base64失败");
        }

    }
}
