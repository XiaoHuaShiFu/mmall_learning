package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lenovo on 2019/2/1.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);

}
