package com.ms.user.service;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired // pois vamos utilizar o userRepository, fazendo um ponto de injeção
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;

    @Transactional // garante o holback caso algum desses processos de algum tipo de erro
    public UserModel save(UserModel userModel){
        // principal responsabilidade é salvar o usuario
        userModel =  userRepository.save(userModel);
        // enviar uma mensagem no email
        userProducer.publishMessageEmail(userModel);

        return userModel;
    }
}
