package com.ms.email.models;

import com.ms.email.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TB_EMAILS")
@Data
// pronto para ser salvo na base de dados
public class EmailModel implements Serializable {
    private static final long serialVersionUID = 1l; // útil para garantir a compatibilidade da serialização

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID emailId;
    private UUID userId;
    private String emailFrom;
    private String emailTo;
    private String subject;
    @Column(columnDefinition = "TEXT") // conseguir guardar mais caracteres na base de dados
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
}
