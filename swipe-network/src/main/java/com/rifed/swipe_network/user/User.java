package com.rifed.swipe_network.user;

import com.rifed.swipe_network.role.Role;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    public String getName() {
        return "";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return this.roles
              .stream()
              .map(r->new SimpleGrantedAuthority(r.getName()))
              .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
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

    private String fullName(){return firstName + " " + lastName;}

}
