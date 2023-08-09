package com.mindhub.homebanking;

import com.mindhub.homebanking.models.Account;
import com.mindhub.homebanking.models.Client;
import com.mindhub.homebanking.models.Transaction;
import com.mindhub.homebanking.models.TransactionType;
import com.mindhub.homebanking.repositories.AccountRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.repositories.TransactionRepository;
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
	public CommandLineRunner initData(ClientRepository clientRepository
																		, AccountRepository accountRepository
																		,	TransactionRepository transactionRepository)
	{
		return (args) ->
		{
			//instancia del primer client
			Client client =  new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client);

			//intancio las 2 cuentas account and account1
			Account account = new Account("VIN001", LocalDate.now(), 5000);
			client.addAccount(account);//agrego la cuenta account a client siempre antes de salvar en BD de la linea de ajoba
			accountRepository.save(account);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1), 7500);
			client.addAccount(account1); // agrego la cuenta account1 a client siempre antes de salvar en BD de la linea de ajoba
			accountRepository.save(account1);


			//instancia de nuevas transacciones en la account VIN001 de client
			Transaction transaction = new Transaction(TransactionType.CREDIT, 500, "sueldo", LocalDate.now());
			account.addTransaction(transaction);
			transactionRepository.save(transaction);
			Transaction transaction1 = new Transaction(TransactionType.CREDIT, 200, "Más sueldo", LocalDate.now());
			account.addTransaction(transaction1);
			transactionRepository.save(transaction1);
			Transaction transaction2 = new Transaction(TransactionType.DEBIT, 500, "pago internet", LocalDate.now());
			account.addTransaction(transaction2);
			transactionRepository.save(transaction2);

			//


			//Agrego dos clientes nuevos
			Client client1 = new Client("Fabian", "Garcia", "fabianiio@gmail.com");
			clientRepository.save(client1);

			Client client2 = new Client("Paloma", "Laguens", "palomaaaa@gmail.com");
			clientRepository.save(client2);

			//crea otro cliente en 1 sola línea!
			clientRepository.save(new Client("Paz", "Laguens", "pachula@gmail.com"));
		};
	}






}










