package com.test;

import com.pojo.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/2/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml"})
public class TestManager {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testCreateAndUe() {
        List<Item> itemList = new ArrayList<Item>();
        for (long i = 1; i <= 100; i++) {
            // 创建实体对象
            Item item = new Item();
            item.setId(i);
            item.setTitle("er星手机" + i);
            item.setPrice(new BigDecimal(i));
            item.setGoodsId(1L);
            item.setImage("xxxx.jpg");
            item.setBrand("三星");
            itemList.add(item);
            itemList.add(item);
        }
        // 将对象保存到所有库
        solrTemplate.saveBeans(itemList);
        // 提交
        solrTemplate.commit();
    }

    @Test
    public void testDelete() {
        // 根据id删除
//        solrTemplate.deleteById("4");
        // 根据条件删除
        SimpleQuery query = new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
