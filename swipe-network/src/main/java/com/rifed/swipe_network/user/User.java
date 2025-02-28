package com.rifed.swipe_network.user;

import com.rifed.swipe_network.role.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDate;
import java.time.LocalDateTime ;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_user")
@EntityListeners(AuditingEntityListener.class)

public class User implements UserDetails, Principal {

    @Id
    @GeneratedValue
    private Integer id ;
    private String firstName ;
    private String lastName ;
    private LocalDate dateOfBirth ;

    @Column(unique=true)
    private String email ;
    private String password ;
    private boolean accountlocked ;
    private boolean enabled ;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles ;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate ;

    @CreatedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate ;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.roles
              .stream()
              .map(r->new SimpleGrantedAuthority(r.getName()))
              .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password; // Return the actual password
    }

    @Override
    public String getUsername() {
        return this.email; // Return the email as the username
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public String fullName(){return firstName + " " + lastName;}

    @Override
    public String getName() {
        return email;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
