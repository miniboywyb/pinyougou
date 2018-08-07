package com.pinyougou.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.common.PageResult;
import com.pinyougou.manager.service.SpecificationService;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper specificationMapper;

    @Autowired
    private TbSpecificationOptionMapper specificationOptionMapper;

    @Override
    public PageResult search(Integer pageNum, Integer pageSize, TbSpecification tbSpecification) {
        PageHelper.startPage(pageNum, pageSize);
        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();
        if(tbSpecification.getSpecName()!=null){
            criteria.andSpecNameLike("%" + tbSpecification.getSpecName() + "%");
        }
        Page<TbSpecification> page = (Page<TbSpecification>) specificationMapper.selectByExample(example);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(SpecificationVo specificationVo) {
        TbSpecification specification = specificationVo.getSpecification();
        specificationMapper.insertSelective(specification);
        List<TbSpecificationOption> specificationOptionList = specificationVo.getSpecificationOptionList();
        for (TbSpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(specification.getId());
            specificationOptionMapper.insertSelective(specificationOption);
        }
    }

    @Override
    public SpecificationVo findOne(Long id) {
        SpecificationVo specificationVo = new SpecificationVo();
        TbSpecification Specification = specificationMapper.selectByPrimaryKey(id);
        specificationVo.setSpecification(Specification);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);
        List<TbSpecificationOption> specificationOptionList = specificationOptionMapper.selectByExample(example);
        specificationVo.setSpecificationOptionList(specificationOptionList);
        return specificationVo;
    }

    @Override
    public void update(SpecificationVo specificationVo) {
        TbSpecification specification = specificationVo.getSpecification();
        specificationMapper.updateByPrimaryKeySelective(specification);
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        example.createCriteria().andSpecIdEqualTo(specification.getId());
        specificationOptionMapper.deleteByExample(example);
        List<TbSpecificationOption> specificationOptionList = specificationVo.getSpecificationOptionList();
        for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
            tbSpecificationOption.setSpecId(specification.getId());
            specificationOptionMapper.insertSelective(tbSpecificationOption);
        }
    }

    @Override
    public void dele(Long[] ids) {
        for (Long id : ids) {
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            example.createCriteria().andSpecIdEqualTo(id);
            specificationOptionMapper.deleteByExample(example);
            specificationMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<Map> findSpecList() {
        return specificationMapper.findSpecList();
    }
}
