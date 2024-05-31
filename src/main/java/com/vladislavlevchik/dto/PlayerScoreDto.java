package com.vladislavlevchik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PlayerScoreDto {

    private Long id;

    private String playerName;

    private int sets;

    private int games;

    private int points;

    public void wonPoint() {
        points++;
    }

    public void wonGame() {
        games++;
    }

    public void wonSet(){
        sets++;
    }

    public void clearPoints() {
        points = 0;
    }

    public void clearGames() {
        games = 0;
    }
}
