package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository)
	{
		return (args) ->
		{
			Client client =  new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client);

			Account account = new Account("VIN001", LocalDate.now(), 5000);
			accountRepository.save(account);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1), 7500);
			accountRepository.save(account1);


			Client client1 = new Client("Fabian", "Garcia", "fabianiio@gmail.com");
			clientRepository.save(client1);

			Client client2 = new Client("Paloma", "Laguens", "palomaaaa@gmail.com");
			clientRepository.save(client2);
		};
	}






}










