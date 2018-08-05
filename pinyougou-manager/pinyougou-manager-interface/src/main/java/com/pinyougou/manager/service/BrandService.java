package com.pinyougou.manager.service;

import com.pinyougou.common.PageResult;
import com.pinyougou.pojo.TbBrand;

import java.util.List;

public interface BrandService {
    List<TbBrand> findAll();

    PageResult findPage(int pageNum,int pageSize);

    void add(TbBrand tbBrand);

    TbBrand findOne(Long id);

    void update(TbBrand tbBrand);

    void dele(Long[] ids);

    PageResult search(Integer pageNum, Integer pageSize, TbBrand tbBrand);
}

