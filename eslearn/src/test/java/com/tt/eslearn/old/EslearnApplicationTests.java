package com.tt.eslearn.old;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;

import java.io.IOException;


public class EslearnApplicationTests {

    static RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost("localhost", 9200, "http"))
    );
    static final String userIndex = "user";

    @Test
    public  void createIndexTest() throws IOException {
        // 创建索引 - 请求对象

        CreateIndexRequest request = new CreateIndexRequest(userIndex);
// 发送请求，获取响应
        CreateIndexResponse response = client.indices().create(request,
                RequestOptions.DEFAULT);
        boolean acknowledged = response.isAcknowledged();
// 响应状态
        System.out.println("操作状态 = " + acknowledged);
    }

    @Test
    public  void delIndexTest() throws IOException {
        // 删除索引 - 请求对象
        DeleteIndexRequest request = new DeleteIndexRequest(userIndex);
// 发送请求，获取响应
        AcknowledgedResponse response = client.indices().delete(request,
                RequestOptions.DEFAULT);
// 操作结果
        System.out.println("操作结果 ： " + response.isAcknowledged());
    }

    @Test
    public void lookIndexTest() throws IOException {
        //查看索引
        // 查询索引 - 请求对象
        GetIndexRequest request = new GetIndexRequest(userIndex);
// 发送请求，获取响应
        GetIndexResponse response = client.indices().get(request,
                RequestOptions.DEFAULT);
        System.out.println("aliases:"+response.getAliases());
        System.out.println("mappings:"+response.getMappings());
        System.out.println("settings:"+response.getSettings());
    }


}
