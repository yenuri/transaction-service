package com.bootcamp.transaction.business.impl;

import com.bootcamp.transaction.business.ITransactionService;
import com.bootcamp.transaction.model.Transaction;
import com.bootcamp.transaction.repository.ITransactionRepository;
import com.bootcamp.transaction.utils.TransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private ITransactionRepository transactionRepository;
    @Override
    public Mono<Transaction> create(Transaction transaction) {
        transaction.setStatus(TransactionStatus.REGISTERED.name());
        transaction.setTransactionDate(Calendar.getInstance().getTime());
        return transactionRepository.save(transaction);
    }
    @Override
    public Mono<Transaction> findById(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll()
                .filter(p -> p.getStatus().equals(TransactionStatus.REGISTERED.name()));
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {
        return transactionRepository.findById(transaction.getId())
                .filter(p -> p.getStatus().equals(TransactionStatus.REGISTERED.name()))
                .switchIfEmpty(Mono.empty())
                .flatMap(transactionDB -> transactionRepository.save(transaction));
    }

    @Override
    public Mono<Transaction> delete(String id) {
        return transactionRepository.findById(id)
                .switchIfEmpty(Mono.empty())
                .doOnNext(p -> p.setStatus(TransactionStatus.DELETED.name()))
                .flatMap(transactionRepository::save);
    }
}
