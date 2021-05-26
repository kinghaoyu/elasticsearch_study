package com.why.elasticsearch_study.estest.doc;

import com.alibaba.fastjson.JSON;
import com.why.elasticsearch_study.bean.PersonDossierBean;
import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.Arrays;

/**
 * 描述:
 * Es 向索引中批量插入文档数据
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class EsTestDocInsertBatch {
    public static void main(String[] args) throws IOException {
        //创建 客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );

        //创建批量请求对象
        BulkRequest bulkRequest = new BulkRequest();
        //往 bulkRequest 中添加数据   **当为插入时，添加的是 IndexRequest 对象**
        PersonDossierBean personDossier = new PersonDossierBean("张三2","西安市碑林区",32, "111111");
        bulkRequest.add(new IndexRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("222")
                .source(JSON.toJSONString(personDossier), XContentType.JSON));

        PersonDossierBean personDossier1 = new PersonDossierBean("张三3","西安市碑林区3",36, "111232311");
        bulkRequest.add(new IndexRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("333")
                .source(JSON.toJSONString(personDossier1), XContentType.JSON));

        PersonDossierBean personDossier2 = new PersonDossierBean("张三4","西安市碑林区4",324, "1111114");
        bulkRequest.add(new IndexRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("444")
                .source(JSON.toJSONString(personDossier2), XContentType.JSON));

        PersonDossierBean personDossier3 = new PersonDossierBean("张三5","西安市碑林区5",352, "1111511");
        bulkRequest.add(new IndexRequest()
                .index(TestEnums.TEST_INDEX_NAME.getValue())
                .id("555")
                .source(JSON.toJSONString(personDossier3), XContentType.JSON));


        //向 es 发出请求
        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(Arrays.toString(response.getItems()));
        //关闭客户端
        client.close();
    }
}
