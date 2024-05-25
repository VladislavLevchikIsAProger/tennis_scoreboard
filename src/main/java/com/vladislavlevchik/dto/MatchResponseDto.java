package com.vladislavlevchik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchResponseDto {

    private String playerOne;

    private String playerTwo;

    private String winnerName;
}
