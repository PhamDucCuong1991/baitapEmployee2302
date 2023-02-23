package com.employee_modelandview_dao.service;

import com.employee_modelandview_dao.dao.AccountDAO;
import com.employee_modelandview_dao.model.Account;
import com.employee_modelandview_dao.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    public List<Account> getAll() {
        return accountDAO.getAll();
    }

    public Account findAccountById(int id) {
        return accountDAO.findAccountById(id);
    }

    public boolean checkLogin(String username, String password) {
        Account account = accountDAO.findAccountByUsernam(username);
        if (account!=null && account.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
