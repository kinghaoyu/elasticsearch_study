package com.why.elasticsearch_study.estest.index;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * 描述:
 * ES 索引创建
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestIndexCreate {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );

        //创建  创建索引请求对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(TestEnums.TEST_INDEX_NAME.getValue());
        //用客户端 发出创建索引的请求
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println("索引操作：" +createIndexResponse.isAcknowledged());

        //关闭 es 客户端
        restHighLevelClient.close();
    }
}
