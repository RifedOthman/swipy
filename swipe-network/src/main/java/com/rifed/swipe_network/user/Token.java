package com.rifed.swipe_network.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    private Integer id ;
    private String token ;
    private LocalDateTime expiresAt ;
    private LocalDateTime createdAt ;
    private LocalDateTime validatedAt ;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user ;


}
