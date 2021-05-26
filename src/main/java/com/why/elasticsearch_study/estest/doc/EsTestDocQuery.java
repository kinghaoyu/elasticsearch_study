package com.why.elasticsearch_study.estest.doc;

import com.why.elasticsearch_study.enums.TestEnums;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;

/**
 * 描述:
 * es 高级查询--> 全量查询, 条件查询，分页，过滤，排序, 组合查询
 *
 * @author why 0005412
 * @create 2021-05-26
 */
public class EsTestDocQuery {
    public static void main(String[] args) throws IOException {
        //创建 es 客户端对象
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(TestEnums.LOCAL_HOST.getValue(), 9200, TestEnums.HTTP_VALUE.getValue()))
        );


        System.out.println("-----------------全量查询-------------");
        SearchRequest searchRequest = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());

        searchRequest.source(new SearchSourceBuilder().query(QueryBuilders.matchAllQuery()));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(response.getHits().getTotalHits());
        for (SearchHit hit:
                response.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================全量查询=============");


        System.out.println("-----------------条件查询-------------");
        SearchRequest searchRequest1 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());

        searchRequest1.source(new SearchSourceBuilder().query(QueryBuilders.termQuery("name", "2")));

        SearchResponse response1 = client.search(searchRequest1, RequestOptions.DEFAULT);

        System.out.println(response1.getHits().getTotalHits());
        for (SearchHit hit:
                response1.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================条件查询=============");



        System.out.println("-----------------分页查询, 排序-------------");
        SearchRequest searchRequest3 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());
        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //分页查询 from 类似于 start 从 0 开始， size 类似于 pageSize
        builder.from(0);
        builder.size(3);
        builder.sort("age", SortOrder.DESC);
        searchRequest3.source(builder);

        SearchResponse response3 = client.search(searchRequest3, RequestOptions.DEFAULT);

        System.out.println(response3.getHits().getTotalHits());
        for (SearchHit hit:
                response3.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================分页查询, 排序=============");


        System.out.println("-----------------过滤-------------");
        SearchRequest searchRequest4 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());
        SearchSourceBuilder builder4 = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //分页查询 from 类似于 start 从 0 开始， size 类似于 pageSize
        builder4.from(0);
        builder4.size(3);
        builder4.sort("age", SortOrder.DESC);

        //过滤字段
        String[] include = {"name"};
        String[] exclude = {};
        builder4.fetchSource(include, exclude);

        searchRequest4.source(builder4);

        SearchResponse response4 = client.search(searchRequest4, RequestOptions.DEFAULT);

        System.out.println(response4.getHits().getTotalHits());
        for (SearchHit hit:
                response4.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================过滤=============");


        System.out.println("-----------------组合查询-------------");

        //组合查询是将 QueryBuilders.boolQuery() 返回的对象进行封装条件

        SearchRequest searchRequest5 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());
        SearchSourceBuilder builder5 = new SearchSourceBuilder();
        //开始封装 QueryBuilder 对象================
        // 1. 如果是 范围查询的话使用  QueryBuilders.rangeQuery("age")
        // 2. 如果是 模糊查询的话使用  QueryBuilders.fuzzyQuery("name", "wangwu").fuzziness(Fuzziness.ONE); 这里的 ONE 的意思是：差一个字符是可以匹配到的
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        //如果是 或的关系的话，就使用 boolQueryBuilder.should()
        boolQueryBuilder.must(QueryBuilders.termQuery("name", "三"));
        boolQueryBuilder.must(QueryBuilders.termQuery("age", 32));
        //将其 设置回 SearchSourceBuilder 对象中
        builder5.query(boolQueryBuilder);
        //=================================================
        //分页查询 from 类似于 start 从 0 开始， size 类似于 pageSize
        builder5.from(0);
        builder5.size(3);
        builder5.sort("age", SortOrder.DESC);

        //过滤字段
        String[] include1 = {"name"};
        String[] exclude1 = {};
        builder5.fetchSource(include1, exclude1);

        searchRequest5.source(builder5);

        SearchResponse response5 = client.search(searchRequest5, RequestOptions.DEFAULT);

        System.out.println(response5.getHits().getTotalHits());
        for (SearchHit hit:
                response5.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================组合查询=============");


        System.out.println("-----------------高亮-------------");
        SearchRequest searchRequest6 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());
        SearchSourceBuilder builder6 = new SearchSourceBuilder().query(QueryBuilders.matchQuery("name", "2"));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<font color='red'>");
        highlightBuilder.postTags("</font>");
        highlightBuilder.field("name");
        builder6.highlighter(highlightBuilder);

        searchRequest6.source(builder6);

        SearchResponse response6 = client.search(searchRequest6, RequestOptions.DEFAULT);

        System.out.println(response6.getHits().getTotalHits());
        for (SearchHit hit:
                response6.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println("==================高亮=============");


        System.out.println("-----------------聚合查询,分组查询-------------");
        SearchRequest searchRequest7 = new SearchRequest(TestEnums.TEST_INDEX_NAME.getValue());
        SearchSourceBuilder builder7 = new SearchSourceBuilder();
        //分组查询到话：AggregationBuilders.terms("nameGroup").field("name");
        AggregationBuilder aggBuilder = AggregationBuilders.max("maxAge").field("age");
        builder7.aggregation(aggBuilder);

        searchRequest7.source(builder7);

        SearchResponse response7 = client.search(searchRequest7, RequestOptions.DEFAULT);

        System.out.println(response7.getHits().getTotalHits());
        for (SearchHit hit:
                response7.getHits()) {
            System.out.println(hit.toString());
        }
        System.out.println(response7.getAggregations().asList().get(0));
        System.out.println("==================聚合查询,分组查询=============");

        
        //关闭 客户端对象
        client.close();
    }
}
