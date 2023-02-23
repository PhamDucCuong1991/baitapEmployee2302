package com.employee_modelandview_dao.dao;


import com.employee_modelandview_dao.model.Branch;
import com.employee_modelandview_dao.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class BranchDAO {
    @Autowired
    EntityManager entityManager;


    //
    public List<Branch> getAll() {
        String sql = "Select p from Branch p";
        List<Branch> branch = entityManager.createQuery(sql).getResultList();
        return branch;
    }

    public Branch findBranchById(int id) {
        String sql = "Select p from Branch p where p.id = " + id;
        Branch branch = (Branch) entityManager.createQuery(sql).getSingleResult();
        return branch;
    }
}
