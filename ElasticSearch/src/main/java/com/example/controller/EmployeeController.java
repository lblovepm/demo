package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import com.example.utils.ElasticSearchClientUtil;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;
import java.util.*;

/**
 * @author Mr.LB
 * @description: Elastic Search
 * @date 2019/7/1 11:18
 */
@RestController
@RequestMapping("/es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 新增员工
     * @param employeeName
     * @param age
     */
    @RequestMapping("/add_employee")
    public JSONObject addEmployee(String employeeName,Integer age){

        Employee employee = new Employee();
        employee.setEmployeeId(UUID.randomUUID().toString());
        employee.setEmployeeName(employeeName);
        employee.setAge(age);
        employee.setCreateTime(new Date());

        employeeRepository.save(employee);

        JSONObject resultJson = new JSONObject();
        resultJson.put("errCode","0");

        return resultJson;
    }

    /**
     * 查询员工列表
     *
     * @param employeeName  员工姓名
     * @param startAge      开始年龄
     * @param endAge        结束年龄
     * @param pageNumber    页面
     * @param pageSize      页面容量
     * @return
     */
    @RequestMapping("/query_employee")
    public JSONObject queryEmployee(String employeeName,Integer startAge,Integer endAge,Integer pageNumber,Integer pageSize){
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();

        //分页【es中分页查询是从0开始,所以这里需要减1】
        nativeSearchQueryBuilder.withPageable(PageRequest.of(pageNumber-1,pageSize));
        //排序
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("age").order(SortOrder.DESC));

        //employeeName模糊查询
        if(!StringUtils.isEmpty(employeeName)){
            FuzzyQueryBuilder employeeNameFuzzyQueryBuilder = QueryBuilders.fuzzyQuery("employeeName", employeeName);
            nativeSearchQueryBuilder.withQuery(employeeNameFuzzyQueryBuilder);
        }

        //age范围区间查询
        if(null != startAge || null != endAge){
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
            if(null != startAge){
                rangeQueryBuilder.gte(startAge);
            }
            if(null != endAge){
                rangeQueryBuilder.lte(endAge);
            }
            nativeSearchQueryBuilder.withQuery(rangeQueryBuilder);
        }

        List<Employee> employeeList = new ArrayList<>();

        Iterable<Employee> employeeIterable = employeeRepository.search(nativeSearchQueryBuilder.build());
        Iterator<Employee> employeeIterator = employeeIterable.iterator();
        while (employeeIterator.hasNext()){
            employeeList.add(employeeIterator.next());
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("errCode","0");
        resultJson.put("employeeList",employeeList);

        return resultJson;
    }

    /**
     * 通过索引名删除索引
     * @param indexName
     * @return
     * @throws UnknownHostException
     */
    @RequestMapping("/delete_index")
    public JSONObject deleteIndex(String indexName) throws UnknownHostException {
        Client client = ElasticSearchClientUtil.getClientInstance();

        //产生一个允许从索引中执行action或操作的client
        IndicesAdminClient indicesAdminClient = client.admin().indices();
        //产生一个允许从集群中执行action或操作的client
//        ClusterAdminClient clusterAdminClient = client.admin().cluster();

        JSONObject resultJson = new JSONObject();

        DeleteIndexResponse deleteIndexResponse;
        try {
            deleteIndexResponse = indicesAdminClient.prepareDelete(indexName).execute().actionGet();

            resultJson.put("errCode","0");
            resultJson.put("result",deleteIndexResponse.isAcknowledged());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return resultJson;
    }

    /**
     * 清除所有数据
     * @return
     */
    @RequestMapping("/delete_all_data")
    public JSONObject deleteAllData(){

        employeeRepository.deleteAll();

        JSONObject resultJson = new JSONObject();
        resultJson.put("errCode","0");
        resultJson.put("result","数据已清除完");

        return resultJson;
    }

}
