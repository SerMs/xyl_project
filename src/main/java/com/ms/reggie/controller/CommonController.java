package com.ms.reggie.controller;

import com.ms.reggie.util.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * <p>
 * </p>
 *
 * @author SerMs
 * @date 2022/5/8 14:58
 */
@RestController
@Slf4j
@RequestMapping("/common")
public class CommonController {
    @Value("${reggie.path}")
    private String basePath;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @CrossOrigin
    public R<String> upload(MultipartFile file, HttpServletRequest request) throws IOException {
        //file是一个临时文件,需要转存到指定位置,否则本次请求完成之后临时文件就会删除
        log.info("图片转存操作upload:{}", file.toString());

        //原始文件名
        String fileName = file.getOriginalFilename();


//        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用UUID重新生成文件名,防止文件名重复造成文件覆盖
//        String fileName = UUID.randomUUID().toString() + suffix;

        //创建一个目录对象
        File dir = new File(basePath);

        //判断目录是否存在
        if (!dir.exists()) {
            //目录不存在
            dir.mkdir();
        }

        file.transferTo(new File(basePath + fileName));
        return R.success(fileName);
    }


    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    @CrossOrigin
    public void download(String name, HttpServletResponse response) throws Exception {
        log.info("图片名字:{}", name);
        //输入流,通过输入流读取文件内容
        FileInputStream fileInputStream = new FileInputStream(new File(basePath + name));

        response.setContentType("image/jpeg");

        //输出流,通过输出流将文件写回浏览器,在浏览器回显
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, len);
            outputStream.flush();
        }
        outputStream.close();
        fileInputStream.close();
    }
}
