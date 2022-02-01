package com.bootcamp.transaction.business;

import com.bootcamp.transaction.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionService {
    Mono<Transaction> create(Transaction transaction);

    Mono<Transaction> findById(String id);

    Flux<Transaction> findAll();

    Mono<Transaction> update(Transaction transaction);

    Mono<Transaction> delete(String id);
}
