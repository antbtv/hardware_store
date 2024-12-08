package com.example.hardware_store.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "user_login")
    @NotEmpty(message = "Логин не может быть пустым")
    private String username;

    @Getter
    @Setter
    @Column(name = "user_password")
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @Getter
    @Setter
    @Column(name = "role")
    @ColumnDefault(value = "ADMIN")
    private String role;

    @ManyToMany()
    @JoinTable(name = "cart", joinColumns = @JoinColumn(name = "user_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public User(String username, String password) {
        this.username= username;
        this.password = password;
    }
}
