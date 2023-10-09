package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountApi {


    private final AccountService accountService;
    private final AccountRepository accountRepository;


    @GetMapping("/test")
    public void getAccountTest() throws InterruptedException {
        List<Integer> ids = accountRepository.findAllIds(PageRequest.of(0, 10));
        System.out.println("getAccountTest 查出所有：");
        System.out.println(ids);

        Account account = accountRepository.ofId(1).get();
        System.out.println("getAccountTest account:"+account);
        accountService.findAndUpdateAccount(account.getId());

    }



    @GetMapping("/account")
    public void getAccount() throws InterruptedException {
        accountService.findAccount();

    }

    @GetMapping("/accounts")
    public void getAccounts() throws InterruptedException {
        accountService.findAccounts();

    }


    @GetMapping("/accountIds")
    public void getAccountByIds() throws InterruptedException {
        accountService.findAccountByIds();
    }


    @GetMapping("/accountById")
    public void getAccountById() throws InterruptedException {
        accountService.findAccountById();
    }



    @GetMapping("/accountOfId")
    public void getAccountOfId() throws InterruptedException {
        accountService.findAccountOfId();
    }


}
