package com.pinyougou.manager.service;

import com.pinyougou.common.PageResult;
import com.pinyougou.pojo.SpecificationVo;
import com.pinyougou.pojo.TbSpecification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    PageResult search(Integer pageNum, Integer pageSize, TbSpecification tbSpecification);

    void add(SpecificationVo specificationVo);

    SpecificationVo findOne(Long id);

    void update(SpecificationVo specificationVo);

    void dele(Long[] ids);

    List<Map> findSpecList();
}
