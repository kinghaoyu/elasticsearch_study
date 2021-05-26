package com.why.elasticsearch_study.estest.doc;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 描述:
 * Es 根据索引和id 部分更新 doc 文档信息
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocUpdate {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );

        UpdateRequest request = new UpdateRequest();
        request.index(TestEnums.TEST_INDEX_NAME.getValue()).id("111");
        // doc 可以简单的理解为： 就是一条数据的意思
        request.doc(XContentType.JSON,"name", "李四111");
        //向 es 发起请求
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response.getResult());
        //关闭客户端
        client.close();
    }
}
