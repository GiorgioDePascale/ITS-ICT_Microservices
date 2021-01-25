package org.example.borrow.controllers;

import org.example.borrow.models.Borrow;
import org.example.borrow.repos.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/v2/borrows")
public class BorrowController {

    @Value("${kafka.sms.message}")
    private String message;

    @Autowired
    private final BorrowRepository borrowRepository;

    public BorrowController(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.PUT)
    public Borrow addNewBorrow(@Valid @RequestBody Borrow borrow) {
        return borrowRepository.save(borrow);
    }


    // READ
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
    public Borrow getBorrow(@PathVariable Long borrowId) {
        Optional<Borrow> borrowOptional = borrowRepository.findById(borrowId);
        if(borrowOptional.isPresent()){
            return borrowOptional.get();
        }else{
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Borrow> getAllOrders() {
        List<Borrow> result = new ArrayList<Borrow>();
        Iterable<Borrow> iterable = borrowRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }


    // UPDATE
    @RequestMapping(value = "/{orderId}", method = RequestMethod.POST)
    public Borrow modifyBorrow(@RequestBody Borrow borrow, @RequestBody String borrowId ) {
        return borrowRepository.save(borrow);
    }


    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBorrows() {
        borrowRepository.deleteAll();
    }

    @RequestMapping(value = "/{borrowId}", method = RequestMethod.DELETE)
    public void deleteBorrow(@PathVariable Long borrowId) {
        borrowRepository.deleteById(borrowId);
    }
}
