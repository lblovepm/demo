package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.common.PageParam;
import com.example.entity.Employee;
import com.example.interfaced.EmployeeRepository;
import org.elasticsearch.index.query.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    /**
     * 根据名称查询
     * @param employeeName
     * @param pageable
     * @return
     */
    @RequestMapping("/match_phrase_query")
    public JSONObject matchPhraseQuery(String employeeName, Pageable pageable){
        MatchPhraseQueryBuilder builder = QueryBuilders.matchPhraseQuery("employeeName", employeeName);

        Page<Employee> employeeList = employeeRepository.search(builder,pageable);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",employeeList);
        return resultJson;
    }

    /**
     * 根据名称查询
     * @param employeeName
     * @param pageable
     * @return
     */
    @RequestMapping("/match_query")
    public JSONObject matchQuery(String employeeName, Pageable pageable){
        MatchQueryBuilder builder = QueryBuilders.matchQuery("employeeName", employeeName);

        Page<Employee> employeeList = employeeRepository.search(builder,pageable);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",employeeList);
        return resultJson;
    }

    /**
     * 根据名称查询
     * @param employeeName
     * @param pageable
     * @return
     */
    @RequestMapping("/match_phrase_prefix_query")
    public JSONObject matchPhrasePrefixQuery(String employeeName, Pageable pageable){
        MatchPhrasePrefixQueryBuilder builder = QueryBuilders.matchPhrasePrefixQuery("employeeName", employeeName);

        Page<Employee> employeeList = employeeRepository.search(builder,pageable);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",employeeList);
        return resultJson;
    }

    /**
     * 根据名称查询
     * @param employeeName
     * @param pageable
     * @return
     */
    @RequestMapping("/multi_match_query")
    public JSONObject multiMatchQuery(String employeeName, PageParam pageable){
        MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(employeeName,"employeeName");

        System.out.println("pageNumber------------>"+pageable.getPageNumber());
        System.out.println("pageSize-------------->"+pageable.getPageSize());

        Sort sort= new Sort(Sort.Direction.DESC,"createTime");

        Page<Employee> employeeList = employeeRepository.search(builder,pageable);

        JSONObject resultJson = new JSONObject();
        resultJson.put("result",employeeList);
        return resultJson;
    }
}
