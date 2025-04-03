package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class application {

	public static void main(String[] args) {
		// Não mais throws ParseException porque ela será tratada, não propagada
		
		// Problema exemplo
		
		/*
		 * Faça um programa para ler os dados de uma reserva de hotel (número do quarto, data de entrada e data de saída) e
		 * mostrar os dados da reserva, inclusive sua duração em dias. Em seguida, ler novas datas de entrada e saída, atualizar
		 * a reserva, e mostrar novamente a reserva com os dados atualizados. O programa não deve aceitar dados inválidos para a reserva,
		 * conforme as seguintes regras:
		 * - Alterações de reserva só podem ocorrer para datas futuras
		 * - A data de saída deve ser maior que a data de entrada
		 * 
		 * Exemplos:
		 * Room number: 8021
		 * Check-in date (dd/MM/yyy): 23/09/2019
		 * Check-out date (dd/MM/yyyy): 26/09/2019
		 * Reservation: Room 8021, check-in: 23/03/2019, check-out 26/09/2019, 3 nights
		 * 
		 * Enter data to update the reservation:
		 * Check-in date (dd/MM/yyyy): 24/09/2019
		 * Check-out date (dd/MM/yyyy): 29/09/2019
		 * Reservation: Room 8021, check-in: 24/09/2019, check-out 29/09/2019, 5 nights
		 * 
		 * ############################################################################
		 * 
		 * Room number: 8021
		 * Check-in date (dd/MM/yyyy): 23/09/2019
		 * Check-out date (dd/MM/yyyy): 21/09/2019
		 * Error in reservation: Check-out date must be after check-in date
		 * 
		 * #############################################################################
		 * 
		 * Room number: 8021
		 * Check-in date (dd/MM/yyyy): 23/09/2019
		 * Check-out date (dd/MM/yyyy): 26/09/2019
		 * Reservation: Room 8021, check-in: 23/09/2019, check-out 26/09/2019, 3 nights
		 * 
		 * Enter data to update the reservation:
		 * Check-in date (dd/MM/yyyy): 24/09/2015
		 * Check-out date (dd/MM/yyyy): 29/09/2015
		 * Error in reservation: Reservation dates for update must be future dates
		 * 
		 * ##############################################################################
		 * 
		 * Room number: 8021
		 * Check-in date (dd/MM/yyyy): 23/09/2019
		 * Check-out date (dd/MM/yyyy): 29/09/2019
		 * Reservation: Room 8021, check-in: 23/09/2019, check-out 26/09/2019, 3 nights
		 * 
		 * Room number: 8021
		 * Check-in date (dd/MM/yyyy): 24/09/2020
		 * Check-out date (dd/MM/yyyy): 22/09/2020
		 * Error in reservation: Check-out dates must be after check-in date
		 * 
		 * Faremos 3 soluções
		 * 1ª - muito ruim - lógica de validação no programa principal
		 * 2ª - ruim - método retornando string
		 * 3ª - boa - tratamento de exceções
		 * */
		
		/*
		 * A versão passada era com uma correção muito ruim, porque a lógica de validação da reserva nós colocamos no programa principal
		 * e não na reserva. Problema grave de delegação.
		 * Quem é responsável de saber sobre a reserva é a própria reserva, e não outra classe
		 * 
		 * Agora faremos a resolução 2, que seria a ruim. Vamos delegar a lógica de validação para a classe "reserva". E a operação
		 * da classe reserva, quando ocorrer um erro, ela vai retornar um string com a mensagem de erro.
		 * */

		Scanner sc = new Scanner(System.in);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try { 
			System.out.printf("Room number: ");
			int roomNumber = sc.nextInt();
			System.out.printf("Check-in date (dd/MM/yyyy): ");
			Date checkIn = sdf.parse(sc.next()); // Entra a data como texto e SimpleDateFormat converte em um Date.
			System.out.printf("Check-out date (dd/MM/yyyy): ");
			Date checkOut = sdf.parse(sc.next());
	
			/*
			 * Ainda temos um pequeno problema aqui, temos uma validação feita no programa principal, que é na hora de instanciar o objeto
			 * Por enquanto ainda não dá pra tirar, ela deveria ser uma validação no construtor, e não tem como botar ele para retornar String.
			 * */
			
			// Agora com a solução boa, vamos eliminar a gambiarra da String error, bem como if else com if else depois.	
	
			Reservation reservation = new Reservation(roomNumber, checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
	
			System.out.println();
			System.out.println("Enter data to update the reservation:");
			System.out.printf("Check-in date (dd/MM/yyyy): ");
			checkIn = sdf.parse(sc.next()); // Entra a data como texto e SimpleDateFormat converte em um Date.
			System.out.printf("Check-out date (dd/MM/yyyy): ");
			checkOut = sdf.parse(sc.next());
			
			reservation.updateDates(checkIn, checkOut);
			System.out.println("Reservation: " + reservation);
		}
		catch (ParseException e) {  // Podemos por o ParseException na hora de converter a data
			System.out.println("Invalid date format");
		} // Agora novo bloco capturando o erro presente no Reservation.java
		catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage()); // Message é a mensagem que foi passada na hora de instanciar exceção
		}
		catch(RuntimeException e) { // Catch com tipo de exceção genérico, para que faça upcasting pra RuntimeException e mandar msg genérica
			System.out.println("Unexpected error");
		}
		
		sc.close();
		
	}

}
