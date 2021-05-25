package com.why.elasticsearch_study.doc;

import com.alibaba.fastjson.JSON;
import com.why.elasticsearch_study.bean.PersonDossierBean;
import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * 描述:
 * Es 向索引中插入文档数据
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocInsert {
    public static void main(String[] args) throws IOException {
        //创建 客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );

        //创建 index 请求对象
        IndexRequest indexRequest = new IndexRequest(TestEnums.TEST_INDEX_NAME.getValue());
        //指定要创建对象的 id
        indexRequest.id("111");

        //封装 请求对象
        PersonDossierBean personDossier = new PersonDossierBean("张三","西安市雁塔区",33, "111111");
        //向 es 中插入数据必须将 对象 转换为 json 格式
        String personJson = JSON.toJSONString(personDossier);
        indexRequest.source(personJson, XContentType.JSON);


        //向 es 发出请求
        IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
        //关闭客户端
        client.close();
    }
}
