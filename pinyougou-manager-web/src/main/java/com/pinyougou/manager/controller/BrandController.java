package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.PageResult;
import com.pinyougou.common.Result;
import com.pinyougou.manager.service.BrandService;
import com.pinyougou.pojo.TbBrand;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Reference
    private BrandService brandService;

    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        List<TbBrand> list = brandService.findAll();
        return list;
    }

    @RequestMapping("/findPage")
    public PageResult findPage(int pageNum, int pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody TbBrand tbBrand) {
        try {
            brandService.add(tbBrand);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "操作失败");
        }

    }

    @RequestMapping("/findOne")
    public TbBrand findOne(Long id) {
        TbBrand brand = brandService.findOne(id);
        return brand;
    }

    @RequestMapping("/update")
    public Result update(@RequestBody TbBrand tbBrand) {
        try {
            brandService.update(tbBrand);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "操作失败");
        }
    }

    @RequestMapping("/dele")
    public Result dele(Long[] ids) {
        try {
            brandService.dele(ids);
            return new Result(true, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "操作失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(Integer pageNum, Integer pageSize, @RequestBody TbBrand tbBrand) {
        PageResult pageResult = brandService.search(pageNum, pageSize, tbBrand);
        return pageResult;
    }

    @RequestMapping("/findBrandList")
    public List<Map> findBrandList() {
        List<Map> brandMap = brandService.findBrandList();
        return brandMap;
    }
}
