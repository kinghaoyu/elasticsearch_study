package com.why.elasticsearch_study.bean;

/**
 * 描述:
 * 人员档案bean
 *
 * @author why 0005412
 * @create 2021-05-25
 */
public class PersonDossierBean {
    private String name;
    private String address;
    private Integer age;
    private String tel;

    public PersonDossierBean() {
    }

    public PersonDossierBean(String name, String address, Integer age, String tel) {
        this.name = name;
        this.address = address;
        this.age = age;
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "PersonDossierBean{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", tel='" + tel + '\'' +
                '}';
    }
}
