package com.employee_modelandview_dao.dao;


import com.employee_modelandview_dao.model.Employee;
import com.employee_modelandview_dao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Transactional
public class EmployeeDAO {

    @Autowired
    EntityManager entityManager;
    @Autowired
    EmployeeService employeeService;

    public List<Employee> getAll() {
        String sql = "Select p from Employee p";
        List<Employee> employees = entityManager.createQuery(sql).getResultList();
        return employees;
    }

    public List<Employee> sortBy(String sort) {
        List<Employee> employees = getAll();
        switch (sort) {
            case "id":
                employees.sort(Comparator.comparing(Employee::getEmployeeCode));
                break;
            case "name":
                employees.sort(Comparator.comparing(Employee::getName));
                break;
            case "age":
                employees.sort(Comparator.comparing(Employee::getAge));
                break;
            case "salary":
                employees.sort(Comparator.comparing(Employee::getSalary));
                break;
        }
        return employees;
    }

    //
    public boolean save(Employee employee) {
        try {
            EntityTransaction txn = entityManager.getTransaction();
            if(!txn.isActive()){
                txn.begin();
            }
            entityManager.persist(employee);
            txn.commit();
        }catch (Exception e){
            return false;
        }
        return true;

    }

    public void delete(Employee employee) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(employee);
        txn.commit();
    }

    //
    public boolean edit(Employee employee) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(employee);
        txn.commit();
        return true;
    }

    public Employee findById(int id) {
        String sql = "Select p from Employee p where p.employeeCode = " + id;
        Employee employee = (Employee) entityManager.createQuery(sql).getSingleResult();
        return employee;
    }

    public List<Employee> findListEmployeeByName(String name) {
        List<Employee> newList = new ArrayList<>();
        List<Employee> employees = getAll();
        for (Employee employee : employees) {
            if (employeeService.removeAccent(employee.getName()).toUpperCase().contains(name)) {
                newList.add(employee);
            }
        }
        return newList;
    }
}
