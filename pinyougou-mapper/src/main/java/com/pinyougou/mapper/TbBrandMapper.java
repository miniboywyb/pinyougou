package com.pinyougou.mapper;

import com.github.pagehelper.Page;
import com.pinyougou.pojo.TbBrand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbBrandMapper {

    @Select("select id,name,first_char as firstChar from tb_brand")
    List<TbBrand> findAll();

    void add(TbBrand tbBrand);

    TbBrand findOne(Long id);

    void update(TbBrand tbBrand);

    void dele(Long id);

    List<TbBrand> search(TbBrand tbBrand);
}
