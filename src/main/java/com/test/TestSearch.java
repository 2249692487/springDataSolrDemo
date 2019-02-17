package com.test;

import com.pojo.Item;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 描述：待描述
 * </p>
 *
 * @author QinLiNa
 * @data 2019/2/16
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-solr.xml"})
public class TestSearch {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testSearch() {
        //创建查询对象, 这里查询所有
        //Query query = new SimpleQuery("*:*");

        //创建查询对象
        Query query = new SimpleQuery();
        /**
         * 根据指定的域指定的关键字查询
         */
        //创建条件对象
        //将条件对象放入查询对象中
        Criteria criteria = new Criteria("item_title").contains("手机");
        query.addCriteria(criteria);
        /**
         * 分页
         */
        //从第几条开始查询
        query.setOffset(0);
        //每页查询多少条数据
        query.setRows(100);

        /**
         * 设置根据价格过滤查询
         */
        //创建过滤查询对象
        FilterQuery filterQuery = new SimpleFilterQuery();
        //创建过滤条件对象
        Criteria filterCriteria = new Criteria("item_price").between(20, 50);
        //将过滤条件对象放入过滤查询对象中
        filterQuery.addCriteria(filterCriteria);
        //过滤查询对象放入查询对象中
        query.addFilterQuery(filterQuery);
        /**
         * 查询并解析结果
         */
        //分页查询, 第一个参数是查询对象, 第二个参数: 返回的数据集合中泛型的类型
        ScoredPage<Item> items = solrTemplate.queryForPage(query, Item.class);

        //总页数
        System.out.println("==============总页数============" + items.getTotalPages());
        //查询到的总条数
        System.out.println("==============总条数============" + items.getTotalElements());
        //每页显示的条数
        System.out.println("==============每页显示的条数====" + items.getNumberOfElements());
        //查询到的数据的集合
        System.out.println("==============集合==============" + items.getContent());

        if (items != null) {
            for (Item item : items.getContent()) {
                System.out.println("=====id=====" + item.getId());
                System.out.println("=====title=====" + item.getTitle());
                System.out.println("=====price=====" + item.getPrice());
                System.out.println("===============================================================");
            }
        }
    }
}
