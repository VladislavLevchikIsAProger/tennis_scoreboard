package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;

public class MatchScoreCalculationService {

    public void updateScore(MatchScoreDto matchScoreDto, Long id) {
        PlayerScoreDto playerOne = matchScoreDto.getPlayerOne();
        PlayerScoreDto playerTwo = matchScoreDto.getPlayerTwo();

        if (playerOne.getId().equals(id)) {
            determineScoringMethod(playerOne, playerTwo);
        } else {
            determineScoringMethod(playerTwo, playerOne);
        }
    }

    private void determineScoringMethod(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        if (pointWinner.getGames() == 6 && opponent.getGames() == 6) {
            playTieBreak(pointWinner, opponent);
        } else {
            handleGameResult(pointWinner, opponent);
            handleSetResult(pointWinner, opponent);
        }
    }

    private void handleGameResult(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        int pointWinnerPoints = pointWinner.getPoints();
        int opponentPoints = opponent.getPoints();

        if (pointWinnerPoints == 3 && opponentPoints < 3) {
            pointWinner.wonGame();

            clearPointsFromPlayers(pointWinner, opponent);
        } else if (pointWinnerPoints >= 3 && opponentPoints >= 3) {
            playOnMoreOrLess(pointWinner, opponent);
        } else {
            pointWinner.wonPoint();
        }
    }

    private void handleSetResult(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        int pointWinnerGames = pointWinner.getGames();
        int opponentGames = opponent.getGames();

        if (pointWinnerGames >= 6 && pointWinnerGames - opponentGames >= 2) {
            pointWinner.wonSet();

            clearGamesFromPlayers(pointWinner, opponent);
        }
    }

    private void playTieBreak(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        int pointWinnerPoints = pointWinner.getPoints();
        int opponentPoints = opponent.getPoints();

        if (pointWinnerPoints >= 6 && pointWinnerPoints - opponentPoints >= 1) {
            pointWinner.wonSet();

            clearGamesFromPlayers(pointWinner, opponent);
            clearPointsFromPlayers(pointWinner, opponent);
        } else {
            pointWinner.wonPoint();
        }
    }


    private void playOnMoreOrLess(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        if (pointWinner.getPoints() > opponent.getPoints()) {
            pointWinner.wonGame();

            clearPointsFromPlayers(pointWinner, opponent);
        } else {
            pointWinner.wonPoint();
        }
    }

    private void clearPointsFromPlayers(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        pointWinner.clearPoints();
        opponent.clearPoints();
    }

    private void clearGamesFromPlayers(PlayerScoreDto pointWinner, PlayerScoreDto opponent) {
        pointWinner.clearGames();
        opponent.clearGames();
    }

}
