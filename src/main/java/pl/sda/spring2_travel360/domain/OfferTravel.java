package pl.sda.spring2_travel360.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OfferTravel {


    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String  country;

    @Column(length = 5000)
    private String descriprion;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private Long price;
}
