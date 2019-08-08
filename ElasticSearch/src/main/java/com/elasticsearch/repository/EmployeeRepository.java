package com.elasticsearch.repository;

import com.elasticsearch.entity.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeRepository extends ElasticsearchRepository<Employee,String> {

}
