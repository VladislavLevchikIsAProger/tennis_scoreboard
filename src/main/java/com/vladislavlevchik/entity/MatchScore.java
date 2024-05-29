package com.vladislavlevchik.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchScore {

    private Player playerOne;

    private Player playerTwo;

    private Score score;

}
