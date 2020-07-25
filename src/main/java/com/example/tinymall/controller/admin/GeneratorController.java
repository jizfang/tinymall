package com.example.tinymall.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.model.dto.QueryDTO;
import com.example.tinymall.model.dto.TableDTO;
import com.example.tinymall.service.GeneratorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javafx.scene.control.Pagination;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 功能描述:
 *
 * @Author：姬中方(jizhongfang@htmimi.com)
 * @Date: Create in 2018/4/24 16:25
 */
@RestController
@RequestMapping("/admin/generator")
@ResponseResult
@Api(tags = "代码生成")
public class GeneratorController {
    @Autowired
    private GeneratorService generatorService;

    //设置前端地址，解决跨域问题
    @Value("${front.url}")
    private String FRONT_URL;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    @ApiOperation(value = "查询所有库表", notes="查询所有库表")
    public Object list(PageQO pageQO, String tableName){
        //查询列表数据
        Page<Map<String, Object>> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        List<Map<String, Object>> list = generatorService.queryList(tableName);

        return PageVO.build(page);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/code")
    public void code(HttpServletResponse response,@RequestBody List<TableDTO> tables) throws IOException {

        byte[] data = generatorService.generatorCode(tables);

        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Content-Disposition", "attachment; filename=\"common.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
