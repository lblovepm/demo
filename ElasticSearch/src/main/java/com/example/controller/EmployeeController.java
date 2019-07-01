package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.Employee;
import com.example.interfaced.EmployeeRepository;
import com.google.gson.JsonObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author Mr.LB
 * @description: TODO
 * @date 2019/7/1 11:18
 */
@RestController
@RequestMapping("/es")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JestClient jestClient;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 获取索引
     * @return
     */
    @RequestMapping("/get_index")
    public JSONObject getIndex(){
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId("employee_index");
        String result = elasticsearchTemplate.index(indexQuery);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",result);
        return resultJson;
    }

    /**
     * 新增
     * @param employeeId
     * @param employeeName
     * @param age
     */
    @RequestMapping("/add")
    public JSONObject add(Integer employeeId, String employeeName, Integer age){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeName);
        employee.setAge(age);
        employee.setCreateTime(new Date());
        employee.setUpdateTime(employee.getCreateTime());
        Employee resultEmployee = employeeRepository.save(employee);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",resultEmployee);
        return resultJson;
    }

    /**
     * 更新
     * @param employeeId
     */
    @RequestMapping("/update")
    public JSONObject update(Integer employeeId,String employeeName,Integer age){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();

        employee.setEmployeeName(employeeName);
        employee.setAge(age);
        employee.setUpdateTime(new Date());
        Employee resultEmployee = employeeRepository.save(employee);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",resultEmployee);
        return resultJson;
    }

    /**
     * 删除  by id
     * @param employeeId
     */
    @RequestMapping("/delete/{employeeId}")
    public JSONObject deleteById(@PathVariable("employeeId") Integer employeeId){
        employeeRepository.deleteById(employeeId);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",true);
        return resultJson;
    }

    /**
     * 删除 by entity
     * @param employeeId
     */
    @RequestMapping("/delete")
    public JSONObject delete(Integer employeeId){
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        Employee employee = optionalEmployee.get();

        employeeRepository.delete(employee);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",true);
        return resultJson;
    }

    /**
     * 查询 by id
     * @param employeeId
     */
    @RequestMapping("/query")
    public JSONObject query(Integer employeeId){
        Iterable<Employee> employeeIterable;
        if(null == employeeId){
            employeeIterable = employeeRepository.findAll();
        }else{
            List<Integer> employeeIdList = new ArrayList<>();
            employeeIdList.add(employeeId);
            employeeIterable = employeeRepository.findAllById(employeeIdList);
        }

        Iterator<Employee> employeeIterator = employeeIterable.iterator();

        List<Employee> employeeList = new ArrayList<>();
        while (employeeIterator.hasNext()){
            Employee employee = employeeIterator.next();
            employeeList.add(employee);
        }

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",employeeList);
        return resultJson;
    }

    @RequestMapping("/query/by/condition")
    public JSONObject query(String searchCondition){
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).withPageable(pageable).build();

        return null;
    }
}
