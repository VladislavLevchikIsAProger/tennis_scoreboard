package com.vladislavlevchik.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDto {

    private String playerOne;

    private String playerTwo;

    private String winnerName;
}
