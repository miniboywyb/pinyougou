package com.pinyougou.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.common.PageResult;
import com.pinyougou.manager.service.BrandService;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.pojo.TbBrand;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private TbBrandMapper brandMapper;

    @Override
    public List<TbBrand> findAll() {
        return brandMapper.findAll();
    }

    @Override
    public PageResult findPage(int pageNum, int PageSize) {
        PageHelper.startPage(pageNum, PageSize);
        Page<TbBrand> page =(Page<TbBrand>) brandMapper.findAll();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(TbBrand tbBrand) {
        brandMapper.add(tbBrand);
    }

    @Override
    public TbBrand findOne(Long id) {
        return brandMapper.findOne(id);
    }

    @Override
    public void update(TbBrand tbBrand) {
        brandMapper.update(tbBrand);
    }

    @Override
    public void dele(Long[] ids) {
        if(ids!=null&&ids.length>0){
            for (Long id : ids) {
                brandMapper.dele(id);
            }
        }

    }

    @Override
    public PageResult search(Integer pageNum, Integer pageSize, TbBrand tbBrand) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page =(Page<TbBrand>) brandMapper.search(tbBrand);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
