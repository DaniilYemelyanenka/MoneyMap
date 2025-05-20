package by.yemelyanenko.MoneyMap.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "mm_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String role;

    private LocalDate createdAt;

    private boolean isActive;


}
