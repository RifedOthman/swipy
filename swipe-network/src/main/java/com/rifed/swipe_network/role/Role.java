package com.rifed.swipe_network.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rifed.swipe_network.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role {

    @Id
    @GeneratedValue
    private String role;
    @Column(unique = true)
    private String name ;

    @ManyToMany(mappedBy="roles")
    @JsonIgnore
    private List<User> users ;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate ;
    @CreatedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate ;


}
