package com.pinyougou.manager.service;

import com.pinyougou.common.PageResult;
import com.pinyougou.pojo.TbSpecification;

public interface SpecificationService {
    PageResult search(Integer pageNum, Integer pageSize, TbSpecification tbSpecification);
}
