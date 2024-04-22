package dev.wandessonsoares.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity(name="USUARIO")
@Table(name="USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class User{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false, unique = true)
    private String email;
}