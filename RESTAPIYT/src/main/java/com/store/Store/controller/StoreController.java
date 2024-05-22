package com.store.Store.controller;

import com.store.Store.model.Address;
import com.store.Store.repository.MySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StoreController {

    @Autowired
    MySqlRepository mySqlRepository;

    @GetMapping("/all")
    public List<Address> getAll() {
        return mySqlRepository.findAll();
    }

    @GetMapping("/all/{id}")
    // @PathVariable indicates that the parameter is a path variable
    public Address getById(@PathVariable("id") Integer id) {
        return mySqlRepository.findById(id).get();
    }

    @DeleteMapping("/remove/{id}")
    public boolean removeById(@PathVariable("id") Integer id) {
        if(mySqlRepository.findById(id).isPresent()){
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("update/{id}")
    // @PathVariable indicates that the parameter is a path variable
    // @RequestBody indicates that the parameter is a request body
    public Address updateAddress(@PathVariable("id") Integer id, @RequestBody Map<String, String> body) {
        Address current = mySqlRepository.findById(id).get();
        current.setNumber(Integer.parseInt(body.get("number")));
        current.setStreet(body.get("street"));
        current.setPostcode(body.get("postcode"));
        mySqlRepository.save(current);
        return current;
    }

//    @PutMapping("/update/{id}")
//    public Address updateAddress(@PathVariable("id") Integer id, @RequestBody Address newAddress) {
//        Optional<Address> optionalAddress = mySqlRepository.findById(id);
//
//        if (optionalAddress.isPresent()) {
//            Address current = optionalAddress.get();
//            current.setNumber(newAddress.getNumber());
//            current.setStreet(newAddress.getStreet());
//            current.setPostcode(newAddress.getPostcode());
//            mySqlRepository.save(current);
//            return current;
//        } else {
//            // If address with given id doesn't exist, you might want to handle this case
//            // You can throw an exception or return a default address or null
//            // For now, let's return null
//            return null;
//        }
//    }

    @PostMapping("/add")
    public Address addAddress(@RequestBody Map<String, String> body) {
        Integer number = Integer.parseInt(body.get("number"));
        String street = body.get("street");
        String postcode = body.get("postcode");
        Address address = new Address(number, street, postcode);
       return mySqlRepository.save(address);
    }
}
