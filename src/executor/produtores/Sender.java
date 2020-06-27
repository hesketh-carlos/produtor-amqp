package executor.produtores;

import executor.configuracao.Configuracao;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class Sender {

	public static void main(String[] args) {

		  final String queue = "myQueue";
		  final String exchange = "myExchange";
		  final String routingKey = "";

		  //Set up queue, exchanges and bindings
		  RabbitAdmin admin = new RabbitAdmin(Configuracao.getConnection());
		  Queue queue = new Queue("myQueue");
		  admin.declareQueue(queue);
		  TopicExchange exchange = new TopicExchange("myExchange");
		  admin.declareExchange(exchange);
		  admin.declareBinding(
  	      BindingBuilder.bind(queue).to(exchange).with("foo.*"));


		  RabbitTemplate template = new RabbitTemplate(Configuracao.getConnection());

		  for (int i = 0; i < 100; i++) {
			  template.convertAndSend("myExchange", "foo.bar", "Hello CloudAMQP! "+ i);
		  }
		  

		}
	
}
