package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.PageResult;
import com.pinyougou.manager.service.SpecificationService;
import com.pinyougou.pojo.TbSpecification;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    @RequestMapping("/search")
    public PageResult search(Integer pageNum, Integer pageSize, @RequestBody TbSpecification tbSpecification) {
        PageResult result=specificationService.search(pageNum, pageSize, tbSpecification);
        return result;
    }
}
