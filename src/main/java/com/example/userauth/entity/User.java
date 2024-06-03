package com.example.userauth.entity;

import com.example.userauth.data.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize =1
    )
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "student_sequence")
    private Long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean isLocked;
    private Boolean enabled;
}
