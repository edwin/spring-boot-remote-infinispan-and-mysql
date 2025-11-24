package com.edw.service;

import com.edw.bean.Employee;
import com.edw.repository.EmployeeRepository;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <pre>
 *  com.edw.service.EmployeeService
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 23 Nov 2025 15:08
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    private RemoteCacheManager remoteCacheManager;

    public EmployeeService(@Autowired EmployeeRepository employeeRepository, @Autowired RemoteCacheManager remoteCacheManager) {
        this.employeeRepository = employeeRepository;
        this.remoteCacheManager = remoteCacheManager;
    }

    @Cacheable(value = "query-cache", key = "#id")
    public Employee getEmployee(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "query-cache", key = "#root.methodName + '-' + #firstname + '-' +  #lastname")
    public List<Employee> searchEmployeeByFirstnameAndLastname(String firstname, String lastname) {
        return employeeRepository.findTop10ByFirstnameLikeAndLastnameLike(firstname + "%", lastname + "%");
    }

    @Cacheable(value = "query-cache", key = "#root.methodName + '-' +  #gender")
    public List<Employee> searchEmployeeByGender(String gender) {
        return employeeRepository.findTop10ByGenderIgnoreCase(gender);
    }

    public Map<Object, Object> searchAllCache() {
        RemoteCache<Object, Object> cache = remoteCacheManager.getCache("query-cache");
        Map<Object, Object> cacheEntries = new HashMap<>();
        Set<Object> keys = cache.keySet();
        for (Object key : keys) {
            cacheEntries.put(key, cache.get(key));
        }
        return cacheEntries;
    }
}
