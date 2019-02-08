package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lenovo on 2019/2/1.
 */
@Service("iFileService")
public class FileServiceImpl implements IFileService{

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //获得扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名是:{},上传的路径是:{},新文件名是:{}", fileName, path, uploadFileName);

        File fileDir = new File(path);
        if (! fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName); //拼接绝对路径

        try {
            //保存文件到upload
            file.transferTo(targetFile);
            //将targetFile上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //删除uoload里面的targetFile
            targetFile.delete();
        } catch (IOException e) {
            logger.info("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

}
