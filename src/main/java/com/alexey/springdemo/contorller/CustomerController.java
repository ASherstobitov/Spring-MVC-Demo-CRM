package com.alexey.springdemo.contorller;

import com.alexey.springdemo.dao.CustomerDAO;
import com.alexey.springdemo.dao.CustomerDAOImpl;
import com.alexey.springdemo.entity.Customer;
import com.alexey.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
//    need to inject our customer service
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel) {

//        get Customers form the service
        List<Customer> theCustomers = customerService.getCustomers();

//        add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";

    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
//        create model attribute to bind form data
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer theCustomer) {
//        save our customer using our service
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) {
//        get the customer from the db
            Customer theCustomer = customerService.getCustomer(theId);
//        set the customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);
//        send over to our form
        return "customer-form";
    }

    @GetMapping("/delete")
        public String delete(@RequestParam("customerId") int theId) {
//        delete customer
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }


}

