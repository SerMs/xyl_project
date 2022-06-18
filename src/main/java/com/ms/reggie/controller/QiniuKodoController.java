package com.ms.reggie.controller;

import com.ms.reggie.util.QiniuKodoUtil;
import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * <p>
 * </p>
 *
 * @author SerMs
 * @date 2022/6/18 14:54
 */
@RestController
@RequestMapping("qiniukodo")
@Slf4j
public class QiniuKodoController {

    @Resource
    QiniuKodoUtil qiniuKodoUtil;


    /**
     * 图片类型上传
     *
     * @param
     */
    @PostMapping("upload")
    @CrossOrigin
    public R<String> upload(MultipartFile file) {
        log.info("图片上传:{},名称{}", file, file.getOriginalFilename());
        return R.success(qiniuKodoUtil.upload(file, file.getOriginalFilename()));
    }

    /**
     * 举空间文件列表
     */
    @RequestMapping("listSpaceFiles")
    public void listSpaceFiles() {
        qiniuKodoUtil.listSpaceFiles();
    }

    /**
     * 获取下载文件的链接
     *
     * @param fileName
     */
    @RequestMapping("getFileUrl")
    public void getFileUrl(String fileName) {
        try {
            qiniuKodoUtil.getFileUrl(fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除图片
     *
     * @param fileList
     */
    @RequestMapping("deleteFile")
    public void deleteFile(String[] fileList) {
        qiniuKodoUtil.deleteFile(fileList);
    }
}

