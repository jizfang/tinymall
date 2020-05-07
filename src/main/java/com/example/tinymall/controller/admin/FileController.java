package com.example.tinymall.controller.admin;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.Exceptions.InternalServerException;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.core.storage.StorageService;
import com.example.tinymall.core.util.ResponseUtil;
import com.example.tinymall.domain.TinymallStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description
 * @Author jzf
 * @Date 2020-5-7 15:10
 */
@ResponseResult
@RestController
@RequestMapping("/image")
@Slf4j
@ResponseStatus(HttpStatus.OK)
public class FileController {

    @Autowired
    private StorageService storageService;

    @Value("${image.path.prefix}")
    private String IMAGE_PREFIX;

    @RequestMapping("/upload")
    public Object uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        //获取项目classes/static的地址
        String staticPath = "D:/upload";
        //获取文件名
        String fileName = file.getOriginalFilename();

        // 图片存储目录及图片名称
        String url_path = "images/" + fileName;
        //图片保存路径
        String savePath = staticPath + "/" + url_path;
        log.info("图片保存地址：{}",savePath);
        // 访问路径=静态资源路径+文件目录路径
        String visitPath = url_path;
        log.info("图片访问uri：{}",visitPath);

        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            //将临时存储的文件移动到真实存储路径下
            file.transferTo(saveFile);
        } catch (IOException e) {
            log.error("上传文件出错，错误信息：{}",e);
            throw new BusinessException("上传文件出错");
        }

        return IMAGE_PREFIX + visitPath;
    }

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        TinymallStorage tinymallStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return tinymallStorage;
    }
}
