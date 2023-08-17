package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(HomebankingApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository
																		, AccountRepository accountRepository
																		, TransactionRepository transactionRepository
																		, LoanRepository loanRepository
																		, ClientLoanRepository clientLoanRepository
																		, CardRepository cardRepository	)
	{
		return (args) ->
		{
			//instancia del primer client
				Client client =  new Client("Melba", "Morel", "melba@mindhub.com");
				clientRepository.save(client);

			//intancio las 2 cuentas: account and account1
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

			//instancia de nuevas transacciones a la acocunt1
				Transaction transaction3 = new Transaction(TransactionType.CREDIT, 14400, "Alquiler recibido", LocalDate.now());
				account1.addTransaction(transaction3);
				transactionRepository.save(transaction3);
				Transaction transaction4 = new Transaction(TransactionType.CREDIT, 600, "recibo de palangana vendida", LocalDate.now());
				account1.addTransaction(transaction4);
				transactionRepository.save(transaction4);

			//creación de datos de prueba de loan
				Loan loan = new Loan("Hipotecario", 500000, List.of(12, 24, 36,48,60));
				loanRepository.save(loan);
				Loan loan1 = new Loan("Personal", 100000, List.of(24, 12, 24));
				loanRepository.save(loan1);
				Loan loan2 = new Loan("Automotriz", 300000, List.of(6, 12, 24,36));
				loanRepository.save(loan2);

			//PRUEBA de clientLoan
				ClientLoan clientLoan = new ClientLoan(400000, 60);
				client.addClientLoan(clientLoan);
				loan.addClientLoans(clientLoan);
				clientLoanRepository.save(clientLoan);

				ClientLoan clientLoan1 = new ClientLoan(50000, 12);
			  client.addClientLoan(clientLoan1);
				loan2.addClientLoans(clientLoan1);
				clientLoanRepository.save(clientLoan1);

			//PRUEBA de Cards a MELBA
				Card card = new Card( client.getFirstName() + " " + client.getLastName()
															, CardType.DEBIT
															, CardColor.GOLD
															,"1234-3214-6541-7894"
															, 456
															, LocalDate.now()
															, LocalDate.now().plusYears(5));
				client.addCards(card);
				cardRepository.save(card);

				Card card1 = new Card(client.getFirstName() + " " + client.getLastName()
						                  , CardType.CREDIT
														  , CardColor.TITANIUM
															, "7894-4568-1546-3541"
															, 789
															, LocalDate.now()
															, LocalDate.now().plusYears(5));
				client.addCards(card1);
				cardRepository.save(card1);

			//Agrego dos clientes nuevos
				Client client1 = new Client("Fabian", "Garcia", "fabianiio@gmail.com");
				clientRepository.save(client1);

			//Card para Client1
				Card card2 = new Card(client1.getFirstName() + " " + client1.getLastName()
															, CardType.CREDIT
															,	CardColor.SILVER
															,	"6547-1235-6549-1472"
															, 147
															, LocalDate.now()
															, LocalDate.now().plusYears(5));
				client1.addCards(card2);
				cardRepository.save(card2);

				Client client2 = new Client("Paloma", "Laguens", "palomaaaa@gmail.com");
				clientRepository.save(client2);

			//crea otro cliente en 1 sola línea!
				clientRepository.save(new Client("Paz", "Laguens", "pachula@gmail.com"));
		};
	}
}










