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
			//instancia del primer client
			Client client =  new Client("Melba", "Morel", "melba@mindhub.com");
			clientRepository.save(client);

			//intancio las 2 cuentas account and account1
			Account account = new Account("VIN001", LocalDate.now(), 5000);
			client.addAcount(account);//agrego la cuenta account a client siempre antes de salvar en BD de la linea de ajoba
			accountRepository.save(account);

			Account account1 = new Account("VIN002", LocalDate.now().plusDays(1), 7500);
			client.addAcount(account1); // agrego la cuenta account1 a client siempre antes de salvar en BD de la linea de ajoba
			accountRepository.save(account1);


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










