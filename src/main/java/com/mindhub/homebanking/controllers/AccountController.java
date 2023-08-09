package com.mindhub.homebanking.controllers;

import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.dtos.TransactionDTO;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/accounts")
    public List<TransactionDTO> getTransactions()
    {
        List<Transaction> allTransactions = transactionRepository.findAll();

        // funcion map() recorre
        List<TransactionDTO> converToListDto = allTransactions
                .stream()
                .map(transaction -> new TransactionDTO(transaction))
                .collect(Collectors.toList());
        return converToListDto;
    }

    @GetMapping(" /accounts/{id}")
    public TransactionDTO getTransactionById(@PathVariable Long id)
    {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        return new TransactionDTO(transactionOptional.get());
    }
}
