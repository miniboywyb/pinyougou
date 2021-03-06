package com.pinyougou.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.common.PageResult;
import com.pinyougou.manager.service.ItemCatService;
import com.pinyougou.mapper.TbItemCatMapper;
import com.pinyougou.pojo.TbItemCat;
import com.pinyougou.pojo.TbItemCatExample;
import com.pinyougou.pojo.TbItemCatExample.Criteria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbItemCat> findAll() {
        return itemCatMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }

    /**
     * 修改
     */
    @Override
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public String delete(Long[] ids, Integer ifSearch) {
        String message = "";
        if (ifSearch == 1) {
            for (Long id : ids) {
                TbItemCatExample example = new TbItemCatExample();
                example.createCriteria().andParentIdEqualTo(id);
                List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
                if (itemCats.size() > 0) {
                    TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(id);
                    message += itemCat.getName();
                    message += ",";
                } else {
                    itemCatMapper.deleteByPrimaryKey(id);
                }
            }
        } else {
            for (Long id : ids) {
                deleteAll(id);
            }
        }
        return message;
    }

    public void deleteAll(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(id);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        if (itemCats.size() > 0) {
            for (TbItemCat itemCat : itemCats) {
                deleteAll(itemCat.getId());
            }
        }
        itemCatMapper.deleteByPrimaryKey(id);

    }

    @Override
    public PageResult findPage(TbItemCat itemCat, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example = new TbItemCatExample();
        Criteria criteria = example.createCriteria();

        if (itemCat != null) {
            if (itemCat.getName() != null && itemCat.getName().length() > 0) {
                criteria.andNameLike("%" + itemCat.getName() + "%");
            }

        }

        Page<TbItemCat> page = (Page<TbItemCat>) itemCatMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<TbItemCat> itemCatList = itemCatMapper.selectByExample(example);
        return itemCatList;
    }

}
