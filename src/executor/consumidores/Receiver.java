package executor.consumidores;

import executor.configuracao.Configuracao;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Receiver {
	
	public static void main(String[] args) {
		  //Set up queue, exchanges and bindings
		  RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());
		  
		  for (int i = 0; i < 100; i++) {
			  System.out.println(template.receive("myQueue").toString());
		  }
		  

		}
}
