package com.vladislavlevchik.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchScoreDto {

    PlayerScoreDto playerOne;

    PlayerScoreDto playerTwo;

}

