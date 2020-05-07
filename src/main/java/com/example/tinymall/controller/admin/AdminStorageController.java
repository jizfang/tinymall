package com.example.tinymall.controller.admin;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.Exceptions.ParameterInvalidException;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.core.storage.StorageService;
import com.example.tinymall.core.util.ResponseUtil;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.domain.TinymallStorage;
import com.example.tinymall.service.TinymallStorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName AdminStorageController
 * @Description 对象存储
 * @Author jzf
 * @Date 2020-5-7 17:55
 */
@ResponseResult
@RestController
@RequestMapping("/admin/storage")
public class AdminStorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private TinymallStorageService tinymallStorageService;

    @GetMapping("/list")
    public Object list(String key, String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<TinymallStorage> storageList = tinymallStorageService.querySelective(key, name, page, limit, sort, order);
        return storageList;
    }

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        TinymallStorage tinymallStorage = storageService.store(file.getInputStream(), file.getSize(),
                file.getContentType(), originalFilename);
        return tinymallStorage;
    }

    @PostMapping("/read")
    public Object read(@NotNull Integer id) {
        TinymallStorage storageInfo = tinymallStorageService.findById(id);
        if (storageInfo == null) {
            return new ParameterInvalidException();
        }
        return storageInfo;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallStorage tinymallStorage) {
        if (tinymallStorageService.update(tinymallStorage) == 0) {
            return new BusinessException("更新失败");
        }
        return tinymallStorage;
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody TinymallStorage tinymallStorage) {
        String key = tinymallStorage.getKey();
        if (StringUtils.isEmpty(key)) {
            return new ParameterInvalidException();
        }
        tinymallStorageService.deleteByKey(key);
        storageService.delete(key);
        return ResponseUtil.ok();
    }
}
