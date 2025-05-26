package by.yemelyanenko.MoneyMap.entity;

import by.yemelyanenko.MoneyMap.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "mm_user")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private UserRole role;

    private LocalDate createdAt;

    private boolean isActive;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Category> categories;

}
