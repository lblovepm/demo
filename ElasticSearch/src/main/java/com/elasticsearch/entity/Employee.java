package com.elasticsearch.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/1 11:15
 *
 * @Document中各个属性详解:
 *    1、shards: 分片,ES中所有数据均衡的存储在集群中各个节点的分片中，会影响ES的性能、安全和稳定性
 *    2、replicas: 复制，可以理解为备份分片
 */
@Getter
@Setter
@Document(indexName = "employee_index",type = "employee", shards = 1,replicas = 0, refreshInterval = "-1")
public class Employee implements Serializable {

    @Id
    private String employeeId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String employeeName;

    private Integer age;

    private Date createTime;
}
