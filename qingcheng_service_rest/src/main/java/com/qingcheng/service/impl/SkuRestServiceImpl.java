package com.qingcheng.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.qingcheng.dao.SkuRestMapper;
import com.qingcheng.pojo.goods.Sku;
import com.qingcheng.service.rest.SkuRestService;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuRestServiceImpl implements SkuRestService {

    @Autowired
    SkuRestMapper skuRestMapper;

    /**
     * 获取到所有sku信息并添加到elasticsearch中
     * @return
     */
    @Override
    public void saveSku() {
        //1.连接rest接口
        HttpHost httpHost = new HttpHost("127.0.0.1",9200,"http");
        RestClientBuilder clientBuilder = RestClient.builder(httpHost);
        RestHighLevelClient levelClient = new RestHighLevelClient(clientBuilder);
        //2.封装请求对象
        IndexRequest indexRequest = new IndexRequest("sku","doc","3");
        List<Sku> skuList = skuRestMapper.selectAll();
        //封装sku信息
        Map skumap = new HashMap<>();
        for (Sku sku : skuList) {
            skumap.put("id", sku.getId());
            skumap.put("sn", sku.getSn());
            skumap.put("name", sku.getName());
            skumap.put("price", sku.getPrice());
            skumap.put("num", sku.getNum());
            skumap.put("alert_num", sku.getAlertNum());
            skumap.put("image", sku.getImage());
            skumap.put("images", sku.getImages());
            skumap.put("weight", sku.getWeight());
            skumap.put("create_time", sku.getCreateTime());
            skumap.put("update_time", sku.getUpdateTime());
            skumap.put("spu_id", sku.getSpuId());
            skumap.put("category_id", sku.getCategoryId());
            skumap.put("category_name", sku.getCategoryName());
            skumap.put("brand_name", sku.getBrandName());
            skumap.put("sale_num", sku.getSaleNum());
            skumap.put("comment_num", sku.getCommentNum());
            skumap.put("status", sku.getStatus());
            skumap.put("spec",sku.getSpec());
        }
        indexRequest.source(skumap);
    }
}
