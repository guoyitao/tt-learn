package com.tt.eslearn.old;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class DocurmentGaojiTests extends EslearnApplicationTests{



    @BeforeClass
    public static void batchAdd() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new
                IndexRequest().index(userIndex).id("1001").source(XContentType.JSON, "name",
                "zhangsan"));

        request.add(new IndexRequest().index(userIndex).id("1002").source(XContentType.JSON, "name",
                "lisi"));

        request.add(new
                IndexRequest().index(userIndex).id("1003").source(XContentType.JSON, "name",
                "wangwu"));

//客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
//打印结果信息
        System.out.println("took:" + responses.getTook());
        System.out.println("items:" + responses.getItems());
    }

    @Test
    public void deleteDocument() throws IOException {
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("1001"));
        request.add(new DeleteRequest().index("user").id("1002"));
        request.add(new DeleteRequest().index("user").id("1003"));
//客户端发送请求，获取响应对象
        BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
//打印结果信息
        System.out.println("del took:" + responses.getTook());
        System.out.println("items:" + Arrays.toString(responses.getItems()));
    }

    @Test
    public void getAll() throws IOException {
        // 创建搜索请求对象
        SearchRequest request = new SearchRequest();
        request.indices(userIndex);
// 构建查询的请求体
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
// 查询所有数据
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(sourceBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
// 查询匹配
        SearchHits hits = response.getHits();
        System.out.println("took:" + response.getTook());
        System.out.println("timeout:" + response.isTimedOut());
        System.out.println("total:" + hits.getTotalHits());
        System.out.println("MaxScore:" + hits.getMaxScore());
        System.out.println("hits========>>");
        for (SearchHit hit : hits) {
//输出每条查询的结果信息
            System.out.println(hit.getSourceAsString());
        }
        System.out.println("<<========");
    }



}
