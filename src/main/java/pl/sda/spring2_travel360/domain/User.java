package pl.sda.spring2_travel360.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "travelUser")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String login;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean confirmationStatus;
    private String confirmationId;
    private LocalDateTime validTo;
}
