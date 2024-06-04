package com.vladislavlevchik.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "player_one_id")
    private Player playerOne;

    @ManyToOne
    @JoinColumn(name = "player_two_id")
    private Player playerTwo;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;

}
