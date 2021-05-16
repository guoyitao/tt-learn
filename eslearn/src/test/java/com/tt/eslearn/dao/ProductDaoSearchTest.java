package com.tt.eslearn.dao;

import com.tt.eslearn.pojo.Product;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.AvgAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDaoSearchTest {

    @Autowired
    private ProductDao productDao;

    @Autowired
    public ElasticsearchOperations elasticsearchOperations;

    @Test
    public void testSearch(){
        List<Product> products = productDao.findByTitle("小米");
        for (Product book : products) {
            System.out.println(book);
        }
    }

    @Test
    public void testSearchPaging(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        PageRequest q = PageRequest.of(0,10, sort);
        List<Product> products = productDao.findByCategory("小米", q);
        for (Product book : products) {
            System.out.println(book);
        }
    }

    @Test
    public void testSearchAvg(){

        AvgAggregationBuilder aggBuilder = AggregationBuilders.avg("avg_price").field("price");

        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("category", "小米");

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(queryBuilder);
        nativeSearchQueryBuilder.addAggregation(aggBuilder);
        SearchHits<Product> product = elasticsearchOperations.search(nativeSearchQueryBuilder.build(), Product.class, IndexCoordinates
                .of("product"/*索引名*/));
        for (Aggregation aggregation : product.getAggregations()) {
            System.out.printf("%s  avg:%s \n", aggregation.getName(),((ParsedAvg) aggregation).getValue());
        }

        for (SearchHit<Product> productSearchHit : product) {
            System.out.println(productSearchHit.getContent());
        }

    }
}
