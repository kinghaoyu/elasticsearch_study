package com.why.elasticsearch_study.estest.doc;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * 描述:
 * es 通过ID 删除索引的文档数据
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocDelete {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );
        //创建 delete 请求
        DeleteRequest request = new DeleteRequest();
        request.index(TestEnums.TEST_INDEX_NAME.getValue()).id("111");

        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        client.close();
    }
}
