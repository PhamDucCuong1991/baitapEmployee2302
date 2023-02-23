package com.employee_modelandview_dao.dao;

import com.employee_modelandview_dao.model.Account;
import com.employee_modelandview_dao.model.Branch;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class AccountDAO {
    @Autowired
    EntityManager entityManager;


    public List<Account> getAll() {
        String sql = "Select p from Account p";
        List<Account> accounts = entityManager.createQuery(sql).getResultList();
        return accounts;
    }

    public Account findAccountById(int id) {
        String sql = "Select p from Account p where p.id = " + id;
        Account account = (Account) entityManager.createQuery(sql).getSingleResult();
        return account;
    }

    public Account findAccountByUsernam(String usrename){
        String sql =  "Select p from Account p where p.username = " + usrename;
        Account account;
        try {
            account = (Account) entityManager.createQuery(sql).getSingleResult();
        }catch (Exception e){
            return null;
        }

        return account;
    }
}
