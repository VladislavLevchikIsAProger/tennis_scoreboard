package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;

public class MatchScoreCalculationService {

    public void updateScore(MatchScoreDto matchScoreDto, Long id) {
        PlayerScoreDto playerOne = matchScoreDto.getPlayerOne();
        PlayerScoreDto playerTwo = matchScoreDto.getPlayerTwo();

        if (playerOne.getId().equals(id)) {
            handleGameResult(playerOne, playerTwo);
        } else {
            handleGameResult(playerTwo, playerOne);
        }
    }

    private void handleGameResult(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        if (pointWinner.getPoints() == 3 && opponent.getPoints() < 3) {
            pointWinner.wonGame();

            clearPointsFromPlayers(pointWinner, opponent);
        } else if (pointWinner.getPoints() >= 3 && opponent.getPoints() >= 3) {
            if (pointWinner.getPoints() > opponent.getPoints()) {
                pointWinner.wonGame();

                clearPointsFromPlayers(pointWinner, opponent);
            } else {
                pointWinner.wonPoint();
            }
        } else {
            pointWinner.wonPoint();
        }
    }

    private void clearPointsFromPlayers(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        pointWinner.clearPoints();
        opponent.clearPoints();
    }

}
