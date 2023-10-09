package com.example.demo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final   AccountRepository accountRepository;
    @PersistenceContext
    private EntityManager em;


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAccount() throws InterruptedException {
        Page<Account> accountPage = accountRepository.findAll(PageRequest.of(0, 10));
        System.out.println("findAccount 查出所有：");
        System.out.println(accountPage.getContent());

        Thread.sleep(40000);

        System.out.println("findAccount 再次查询：");
        Optional<Account> accountOptional = accountRepository.ofId(1);
        System.out.println("findAccount accountOptional:"+accountOptional.get());
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAccountByIds() throws InterruptedException {
        List<Integer> ids = accountRepository.findAllIds(PageRequest.of(0, 10));
        System.out.println("findByIds 查出所有：");
        System.out.println(ids);

        Thread.sleep(40000);

        System.out.println("findByIds 再次查询：");
        Optional<Account> accountOptional = accountRepository.ofId(1);
        System.out.println("findByIds accountOptional:"+accountOptional.get());
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAccountById() throws InterruptedException{
        Optional<Account> optionalAccount = accountRepository.findById(1);
        System.out.println("未加锁获取数据：");
        System.out.println(optionalAccount.get());

        Thread.sleep(40000);


        Optional<Account> account = accountRepository.ofId(1);
        System.out.println("findAccountById 再次获取数据");
        System.out.println(account.get());

    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAccountOfId() throws InterruptedException{
        Optional<Account> account = accountRepository.ofId(1);
        System.out.println("findAccountOfId 获取数据");
        System.out.println(account.get());


        Thread.sleep(40000);


        Optional<Account> optionalAccount = accountRepository.findById(1);
        System.out.println("findAccountOfId 未加锁获取数据：");
        System.out.println(optionalAccount.get());
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAccounts() throws InterruptedException {

        Page<Account> accountPage = accountRepository.findAll(PageRequest.of(0, 10));
        System.out.println("findAccounts 查出所有：");
        System.out.println(accountPage.getContent());

        Thread.sleep(50000);


        System.out.println("findAccounts 再次查询：");
        Optional<Account> accountOptional = accountRepository.findById(1);
        System.out.println("findAccounts accountOptional:"+accountOptional.get());
    }


    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void findAndUpdateAccount(Integer id) throws InterruptedException {
        System.out.println("findAndUpdateAccount =====");
        Account account = accountRepository.ofId(id).get();
        System.out.println("findAndUpdateAccount account"+account);

        Thread.sleep(40000);

        System.out.println("findAndUpdateAccount 再次查询：");
        Account account1 = accountRepository.ofId(id).get();
        System.out.println("account1  "+account1 );

    }
}
