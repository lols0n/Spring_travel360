package pl.sda.spring2_travel360.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TravelParticipants {

    @Id
    @GeneratedValue
    private Long id;

    private String fristName;
    private String lastName;
    private String phoneNumber;
    private String email;

//    @ManyToOne
//    private User relatedToUser;
}
