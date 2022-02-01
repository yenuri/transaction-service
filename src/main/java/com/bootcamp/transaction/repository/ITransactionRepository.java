package com.bootcamp.transaction.repository;

import com.bootcamp.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepository extends ReactiveMongoRepository<Transaction, String> {

}
