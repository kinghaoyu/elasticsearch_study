package com.why.elasticsearch_study.enums;

/**
 * 测试常用 常量信息 枚举类
 * @author why
 */
public enum TestEnums {
    /**
     * localhost
     */
    LOCAL_HOST("localhost", "本机地址"),
    /**
     * http
     */
    HTTP_VALUE("http", "http"),
    /**
     * 人员档案索引名称
     */
    TEST_INDEX_NAME("person_dossier", "人员档案索引名称")
    ;


    /**
     * 枚举值
     */
    private String value;
    /**
     * 注释
     */
    private String details;

    TestEnums(String value, String details) {
        this.value = value;
        this.details = details;
    }

    public String getValue() {
        return value;
    }

    public String getDetails() {
        return details;
    }
}
