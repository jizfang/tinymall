package com.example.tinymall.controller.admin;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.Exceptions.ParameterInvalidException;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.storage.StorageService;
import com.example.tinymall.core.validator.Order;
import com.example.tinymall.core.validator.Sort;
import com.example.tinymall.entity.TinymallStorage;
import com.example.tinymall.service.TinymallStorageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Object list(TinymallStorage condition, PageQO pageQO) {
        pageQO.setCondition(condition);
        PageVO<TinymallStorage> storageList = tinymallStorageService.selectPage(pageQO);
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
        TinymallStorage storageInfo = tinymallStorageService.selectByPk(id);
        if (storageInfo == null) {
            return new ParameterInvalidException();
        }
        return storageInfo;
    }

    @PostMapping("/update")
    public Object update(@RequestBody TinymallStorage tinymallStorage) {
        if (tinymallStorageService.updateByPkSelective(tinymallStorage.getId(),tinymallStorage) == 0) {
            return new BusinessException("更新失败");
        }
        return tinymallStorage;
    }

    @PostMapping("/delete")
    public Object delete(@RequestBody TinymallStorage tinymallStorage) {
        String key = tinymallStorage.getStorageKey();
        if (StringUtils.isEmpty(key)) {
            return new ParameterInvalidException();
        }
        tinymallStorageService.deleteByPk(key);
        storageService.delete(key);
        return CommonResult.success();
    }
}
