package com.apple.controller;

import com.apple.model.Customer;
import com.apple.service.CustomerService;
import com.apple.service.ICustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerService customerService = new CustomerService();

    @GetMapping("")
//    public String index(Model model) {
//        List<Customer> customerList = customerService.findAll();
//        model.addAttribute("customers", customerList);
//        return "/index";
//    }
    public ModelAndView showAllList(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("customers", customerService.findAll());
        return modelAndView;

    }
    @GetMapping("/create")
//    public String create(Model model) {
//        model.addAttribute("customer", new Customer());
//        return "/create";
//    }
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Customer customer) {
        customer.setId((int) (Math.random() * 100));
        customerService.save(customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/edit")
//    public String edit(@PathVariable int id, Model model) {
//        model.addAttribute("customer", customerService.findById(id));
//        return "/edit";
//    }
    public ModelAndView edit(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public String update(Customer customer) {
        customerService.update(customer.getId(), customer);
        return "redirect:/customer";
    }

    @GetMapping("/{id}/delete")
//    public String delete(@PathVariable int id, Model model) {
//        model.addAttribute("customer", customerService.findById(id));
//        return "/delete";
//    }
    public ModelAndView delete(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }
    @PostMapping("/delete")
    public String delete(Customer customer, RedirectAttributes redirect) {
        customerService.remove(customer.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/customer";
    }

    @GetMapping("/{id}/view")
//    public String view(@PathVariable int id, Model model) {
//        model.addAttribute("customer", customerService.findById(id));
//        return "/view";
//    }
    public ModelAndView view(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("customer", customerService.findById(id));
        return modelAndView;
    }
}
