package com.example.SunbaseAssignment.Controller;


import com.example.SunbaseAssignment.Dtos.RequestDTo.CustomerRequestDto;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {


    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception{
        try{
            String Result = customerService.create(customerRequestDto);
            return new ResponseEntity(Result,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception{
        try{

            String Result = customerService.update(customerRequestDto);
            return new ResponseEntity(Result,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }


    @GetMapping("/getList")
    public ResponseEntity getAll() throws Exception{
        try{
            List<Customer> customerList = customerService.getCustomerList();
            return new ResponseEntity(customerList,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity getCustomer(@PathVariable Integer id)throws Exception{
        try{
            Customer customer = customerService.getCustomer(id);
            return new ResponseEntity(customer,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.CONFLICT);
        }

    }

    @DeleteMapping("/DeleteCustomer/{id}")
    public ResponseEntity DeleteCustomer(@PathVariable Integer id)throws Exception{
        try{
            String Result= customerService.deleteCustomer(id);
            return new ResponseEntity(Result,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}
