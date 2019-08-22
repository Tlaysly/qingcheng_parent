package com.qingcheng.service.impl;

import com.qingcheng.service.goods.CategoryService;
import com.qingcheng.service.goods.SkuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Init implements InitializingBean {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SkuService skuService;

    @Override
    public void afterPropertiesSet() throws Exception {
        categoryService.saveCategoryTreeToRedis();
        skuService.saveAllPriceToRedis();
    }
}
