package com.ms.user.controllers;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import com.ms.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // para declarar os bens que esta criando
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/users")
    // BeanUtils fornece métodos para copiar propriedades de um objeto para outro.
    // BeanUtils.copyProperties está sendo usado para copiar as propriedades do objeto UserRecordDto para um objeto UserModel.
    // @Valid para que as validações do dto sejam realmente feitas
    // preparar um UserRecordDto para receber os dados do usuario, records são registros imutaveis que pode utilizar para essas transferencias de dados
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto){ // vai ter para o cliente um  ResponseEntity<> onde vamos retornar o UserModel
        // Cria uma nova instância de UserModel
        var userModel = new UserModel();
        // Copia as propriedades de userRecordDto para userModel
        BeanUtils.copyProperties(userRecordDto, userModel); // fazer a conversão de DTO em model
        // Chama o método save do UserService para salvar o userModel e retorna a resposta com o status HTTP 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
}
