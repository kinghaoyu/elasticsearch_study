package com.why.elasticsearch_study.estest.doc;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;
import java.util.Arrays;

/**
 * 描述:
 * Es 向索引中批量删除文档数据
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocDeleteBatch {
    public static void main(String[] args) throws IOException {
        //创建 客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );

        //创建批量请求对象
        BulkRequest bulkRequest = new BulkRequest();
        //往 bulkRequest 中添加数据  **当为删除时，添加的是 DeleteRequest 对象**
        bulkRequest.add(new DeleteRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("222"));

        bulkRequest.add(new DeleteRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("333"));

        bulkRequest.add(new DeleteRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("444"));

        bulkRequest.add(new DeleteRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("555"));


        //向 es 发出请求
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(Arrays.toString(response.getItems()));
        //关闭客户端
        client.close();
    }
}
