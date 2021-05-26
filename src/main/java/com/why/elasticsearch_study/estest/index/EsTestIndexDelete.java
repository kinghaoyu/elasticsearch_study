package com.why.elasticsearch_study.estest.index;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * 描述:
 * Es 删除索引
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestIndexDelete {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );
        //创建  删除索引请求对象
        DeleteIndexRequest request = new DeleteIndexRequest(TestEnums.TEST_INDEX_NAME.getValue());
        //用客户端 发出删除索引的请求
        AcknowledgedResponse response = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println("相应信息："+response.isAcknowledged());
        //关闭 es 客户端
        restHighLevelClient.close();
    }
}
