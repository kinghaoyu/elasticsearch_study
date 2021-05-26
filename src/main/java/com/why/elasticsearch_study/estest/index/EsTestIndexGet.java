package com.why.elasticsearch_study.estest.index;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

import java.io.IOException;
import java.util.Arrays;

/**
 * 描述:
 * Es 获取索引信息
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestIndexGet {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );
        //创建  获取索引请求对象
        GetIndexRequest request = new GetIndexRequest(TestEnums.TEST_INDEX_NAME.getValue());
        //用客户端 发出获取索引的请求
        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

        System.out.println(response.getAliases());
        System.out.println(Arrays.toString(response.getIndices()));
        System.out.println(response.getMappings());
        //关闭 es 客户端
        client.close();
    }
}
