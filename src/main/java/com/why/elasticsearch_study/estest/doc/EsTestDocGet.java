package com.why.elasticsearch_study.estest.doc;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * 描述:
 * es 通过ID 获取索引的文档数据
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocGet {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );
        //创建 get 请求
        GetRequest request = new GetRequest();
        request.index(TestEnums.TEST_INDEX_NAME.getValue()).id("111");

        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSource());
        client.close();
    }
}
