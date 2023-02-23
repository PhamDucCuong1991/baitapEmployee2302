package com.employee_modelandview_dao.controller;

import com.employee_modelandview_dao.model.Employee;
import com.employee_modelandview_dao.service.BranchService;
import com.employee_modelandview_dao.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Comparator;
import java.util.List;

@Controller
public class Home {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BranchService branchService;

    private ModelAndView modelAndView;


    @GetMapping("/home")
    public ModelAndView showEmployeesList(){
        modelAndView = new ModelAndView("home");
        List<Employee> employeeList = employeeService.getAll();
        employeeList.sort(Comparator.comparing(Employee::getEmployeeCode));
        modelAndView.addObject("employees", employeeList);

        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(value = "name", defaultValue = "")String name, Model model){
        modelAndView = new ModelAndView("home");
        model.addAttribute("employees", employeeService.findListByName(name));
      return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable int id) {
        modelAndView = new ModelAndView("redirect:/home");
        employeeService.delete(id);
        return modelAndView;
    }

    @GetMapping("create")
    public ModelAndView showCreate(){
        modelAndView = new ModelAndView("create");
        modelAndView.addObject("branch", branchService.getAll());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(Employee employee, @RequestParam int idBranch, RedirectAttributes attributes) {
        attributes.addFlashAttribute("message", "Tạo mới thành công");
        employee.setBranch(branchService.findBranchById(idBranch));
        Boolean check = employeeService.save(employee);
        if(!check){
            attributes.addFlashAttribute("messCreateFail", "Tài khoản đã tồn tại");
            return "redirect:/create";
        }
        return "redirect:/home" ;
    }

    @GetMapping("edit")
    public ModelAndView showEdit(@RequestParam int id){
        modelAndView = new ModelAndView("edit");
        modelAndView.addObject("employee", employeeService.findEmployeeById(id));
        modelAndView.addObject("branch", branchService.getAll());
        return modelAndView;
    }

    @PostMapping("edit")
    public String edit(@ModelAttribute Employee employee, @RequestParam int idBranch, RedirectAttributes attributes){
        attributes.addFlashAttribute("messEditSuccess", "Cập nhật thành công");
        employee.setBranch(branchService.findBranchById(idBranch));
        if(!employeeService.edit(employee)){
            attributes.addFlashAttribute("messEditFail", "Thao tác sửa thất bại");
            return "redirect:/edit";
        }
        return "redirect:/home" ;
    }

    @GetMapping("/information")
    public ModelAndView showInfor(@RequestParam int id) {
        modelAndView = new ModelAndView("information");
        Employee employee = employeeService.findEmployeeById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @GetMapping("/sortBy")
    public ModelAndView sort(@RequestParam String sort){
        modelAndView.addObject("employees",employeeService.sortBy(sort));
        return modelAndView;
    }
}
