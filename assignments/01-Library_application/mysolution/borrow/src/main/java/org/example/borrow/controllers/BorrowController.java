package org.example.borrow.controllers;

import org.example.borrow.models.Borrow;
import org.example.borrow.repos.BorrowRepository;
import lombok.extern.slf4j.Slf4j;
import org.example.borrow.services.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestController
@Slf4j
@RequestMapping(value = "/v2/borrows")
public class BorrowController {

    @Autowired
    NotificationClient notificationClient;

    @Value("${kafka.sms.message}")
    private String message;

    @Autowired
    private final BorrowRepository borrowRepository;

    public BorrowController(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
    }

    //set borrowing
    @RequestMapping(method = RequestMethod.PUT)
    public Borrow setBorrow(@RequestBody Borrow borrow){
        notificationClient.sendSMS(borrow);
        System.out.println(borrow);
        log.info("set borrow");
        return borrowRepository.save(borrow);

    }

    //get borrowing
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
    public Borrow getBorrow(@PathVariable long borrowId){
        Optional<Borrow> borrowOpt = borrowRepository.findById(borrowId);
        if(borrowOpt.isPresent()){
            log.info("Get borrow " + borrowId);
            return borrowOpt.get();
        }else{
            log.error("Not found");
            return null;
        }
    }

    //get all
    @RequestMapping(method = RequestMethod.GET)
    public Collection<Borrow> getAllBorrowing() {
        log.info("Get all");
        List<Borrow> result = new ArrayList<Borrow>();
        Iterable<Borrow> iterable = borrowRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }

    //edit borrow
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.POST)
    public Borrow editBorrowing(@RequestBody Borrow borrow, @PathVariable long borrowId){
        log.info("Edit ");
        return borrowRepository.save(borrow);
    }

    //delete by ID
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.DELETE)
    public void deleteBorrow(@PathVariable long borrowId){
        log.info("Delete " + borrowId);
        borrowRepository.deleteById(borrowId);
        log.info("deleted " + borrowId);
    }

    //delete all borrow
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBorrow() {
        log.info("Deleting");
        borrowRepository.deleteAll();
        log.info("deleted ");
    }

}
