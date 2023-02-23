package com.employee_modelandview_dao.service;

import com.employee_modelandview_dao.dao.EmployeeDAO;
import com.employee_modelandview_dao.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

public class EmployeeService {
    @Autowired
    EmployeeDAO employeeDAO;
    public List<Employee> getAll() {
        return employeeDAO.getAll();
    }

    public void delete(int id){
        employeeDAO.delete(employeeDAO.findById(id));
    }

    public boolean edit(Employee employee){
      return  employeeDAO.edit(employee);
    }

    public boolean save(Employee employee){
        return employeeDAO.save(employee);
    }
    public String removeAccent(String s) {
        //removeAccent - hàm loại bỏ dấu tiếng Việt
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(temp).replaceAll("").replace('đ','d').replace('Đ','D');
    }
    public List<Employee> findListByName(String name) {
        name = name.trim();
        name = name.toUpperCase();
        name = removeAccent(name);
        return   employeeDAO.findListEmployeeByName(name);
    }

    public int findIndexById(int id){
        for (Employee employee : getAll()) {
            if (employee.getEmployeeCode()==id){
                return getAll().indexOf(employee);
            }
        }
        return -1;
    }

    public Employee findEmployeeById(int id) {
        for (Employee employee : getAll()) {
            if (employee.getEmployeeCode()==id){
                return employee;
            }
        }
        return null;
    }

    public List<Employee> sortBy(String sort){
        return employeeDAO.sortBy(sort);
    }
}
