package model.exceptions;

	// Pode ser extensão de Exception ou RuntimeException

	// RuntimeException tipo de exceção que o compilador não obriga a tratar, então sem try-catch o compliador não fala nada

	// Sendo Exception o compilador obriga a tratar

public class DomainException extends RuntimeException {
		private static final long serialVersionUID = 1L;
		
		public DomainException(String msg) {
			super(msg); // Para permitir instanciar a exceção personalizada passando uma mensagem para ela
		}

	// Quanto à exceções personalizadas, criamos um pacote de exceções a partir do model
	
	// Domain pois é uma exceção lançada pela entidade de domínio "Reservation"
	// Exception convenção pôr esse sufixo
	
	
}
