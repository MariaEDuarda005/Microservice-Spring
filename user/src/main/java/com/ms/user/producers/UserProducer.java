package com.ms.user.producers;

import com.ms.user.dtos.EmailDTO;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
// dentro dessa classe que será feito o envio dessas mensagens
public class UserProducer {

    @Autowired // criar um ponto de injeção do de rabbittemplate
    RabbitTemplate rabbitTemplate;

    // caminho da propriedade para acessar o valor
    @Value(value = "${broker.queue.email.name}") // exchance do tipo default: chave routing key é o mesmo nome da fila queue
    private String routingKey;

    // metodo que vai publicar as mensagens na fila
    public void publishMessageEmail(UserModel userModel){
        var emailDto = new EmailDTO();  // ao entrar nesse metodo a primeira coisa que é pra fazer é criar a instancia de email dto
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!"); // vai receber um email com esse titulo
        emailDto.setText(userModel.getName() + ", seja bem vindo(a)! \nAgradecemos seu cadastro, aproveite ");

        rabbitTemplate.convertAndSend("", routingKey, emailDto); // com esse metodo precisamos passar 3 informações
        // o exchange que vai receber esta mensagem dentro do broker
        // a routingKey para o exchance conseguir fazer o direcionamento para a respectiva fila
        // passar o email dto que acabou de popular
    }
}
