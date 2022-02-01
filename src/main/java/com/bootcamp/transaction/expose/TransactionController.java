package com.bootcamp.transaction.expose;

import com.bootcamp.transaction.business.ITransactionService;
import com.bootcamp.transaction.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequestMapping(value = "/api/transactions")
@RestController
public class TransactionController {
    @Autowired
    private ITransactionService transactionService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> create (@RequestBody Transaction transaction){
        log.info("<<<<< Create Transaction >>>>>");
        return transactionService.create(transaction);
    }

    @GetMapping("/{id}")
    public Mono<Transaction> findById(@PathVariable String id){
        log.info("<<<<< Find One Transaction >>>>>");
        return transactionService.findById(id);
    }

    @GetMapping("")
    public Flux<Transaction> findAll(){
        log.info("<<<<< Find All Transaction >>>>>");
        return transactionService.findAll();
    }

    @PutMapping("")
    public Mono<ResponseEntity<Transaction>> update(@RequestBody Transaction transaction){
        log.info("<<<<< Update Transaction >>>>>");
        return transactionService.update(transaction)
                .flatMap(transactionUpdate -> Mono.just(ResponseEntity.ok(transactionUpdate)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @DeleteMapping("/{id}")
    public Mono<Transaction> delete(@PathVariable String id){
        log.info("<<<<< Delete Transaction >>>>>");
        return transactionService.delete(id);
    }

}
