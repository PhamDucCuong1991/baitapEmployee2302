package com.employee_modelandview_dao.service;

import com.employee_modelandview_dao.dao.BranchDAO;
import com.employee_modelandview_dao.model.Branch;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BranchService {
    @Autowired
    BranchDAO branchDAO;

    public List<Branch> getAll() {
        return branchDAO.getAll();
    }

    public Branch findBranchById(int id){
        return branchDAO.findBranchById(id);
    }
}
