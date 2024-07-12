package com.ms.user.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_USERS")
@Data // para criar o getter e setter
public class UserModel implements Serializable{
    private static final long serialVersionUID = 1l; // útil para garantir a compatibilidade da serialização
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;
    private String name;
    private String email;
}
