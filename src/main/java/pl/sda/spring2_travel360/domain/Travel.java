package pl.sda.spring2_travel360.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Travel {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
//    @ManyToOne

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "travels")
//    private List<TravelParticipants> participants;
}
