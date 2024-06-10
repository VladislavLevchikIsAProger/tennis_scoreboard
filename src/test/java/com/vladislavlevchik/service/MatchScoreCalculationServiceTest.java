package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService matchScoreCalculationService;
    private MatchScoreDto match;
    private PlayerScoreDto playerOne;
    private PlayerScoreDto playerTwo;

    @BeforeEach
    void init() {
        matchScoreCalculationService = new MatchScoreCalculationService();
        match = MatchScoreDto.builder()
                .playerOne(
                        PlayerScoreDto.builder()
                                .id(1L)
                                .build()
                )
                .playerTwo(
                        PlayerScoreDto.builder()
                                .id(2L)
                                .build()
                )
                .build();

        playerOne = match.getPlayerOne();
        playerTwo = match.getPlayerTwo();
    }

    @Test
    void shouldWinGame() {
        playerOne.setPoints(3);
        playerTwo.setPoints(0);

        matchScoreCalculationService.updateScore(match, 1L);

        assertEquals(1, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());
    }

    @Test
    void shouldCheckAdvantagePlayAfterDeuce() {
        playerOne.setPoints(3);
        playerTwo.setPoints(3);

        matchScoreCalculationService.updateScore(match, 1L);

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(4, playerOne.getPoints());
        assertEquals(3, playerTwo.getPoints());
    }

    @Test
    void shouldDetermineWinnerAfterDeuce() {
        playerOne.setPoints(5);
        playerTwo.setPoints(6);

        matchScoreCalculationService.updateScore(match, 2L);

        assertEquals(0, playerOne.getGames());
        assertEquals(1, playerTwo.getGames());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());
    }

    @Test
    void shouldReturnToDeuceAfterAdvantage() {
        playerOne.setPoints(6);
        playerTwo.setPoints(5);

        matchScoreCalculationService.updateScore(match, 2L);

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(6, playerOne.getPoints());
        assertEquals(6, playerTwo.getPoints());
    }

    @Test
    void shouldWinSet() {
        playerOne.setPoints(3);
        playerOne.setGames(5);

        playerTwo.setPoints(0);
        playerTwo.setGames(4);

        matchScoreCalculationService.updateScore(match, 1L);

        assertEquals(1, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());
    }

    @Test
    void shouldBeginTieBreakAfterPointWin(){
        playerOne.setPoints(3);
        playerOne.setGames(6);

        playerTwo.setPoints(1);
        playerTwo.setGames(6);

        matchScoreCalculationService.updateScore(match,1L);

        assertEquals(0, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());

        assertEquals(6, playerOne.getGames());
        assertEquals(6, playerTwo.getGames());

        assertEquals(4, playerOne.getPoints());
        assertEquals(1, playerTwo.getPoints());

    }

    @Test
    void shouldWinTieBreak(){
        playerOne.setPoints(6);
        playerOne.setGames(6);

        playerTwo.setPoints(5);
        playerTwo.setGames(6);

        matchScoreCalculationService.updateScore(match, 1L);

        assertEquals(1, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());
    }

    @Test
    void shouldContinueTieBreak(){
        playerOne.setPoints(6);
        playerOne.setGames(6);

        playerTwo.setPoints(5);
        playerTwo.setGames(6);

        matchScoreCalculationService.updateScore(match, 2L);

        assertEquals(6, playerOne.getPoints());
        assertEquals(6, playerTwo.getPoints());

        assertEquals(6, playerOne.getGames());
        assertEquals(6, playerTwo.getGames());

        assertEquals(0, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());

        matchScoreCalculationService.updateScore(match, 2L);

        assertEquals(6, playerOne.getPoints());
        assertEquals(7, playerTwo.getPoints());

        assertEquals(6, playerOne.getGames());
        assertEquals(6, playerTwo.getGames());

        assertEquals(0, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());
    }

    @Test
    void shouldWinTieBreakAt8To6(){
        playerOne.setPoints(7);
        playerOne.setGames(6);

        playerTwo.setPoints(6);
        playerTwo.setGames(6);

        matchScoreCalculationService.updateScore(match, 1L);

        assertEquals(0, playerOne.getPoints());
        assertEquals(0, playerTwo.getPoints());

        assertEquals(0, playerOne.getGames());
        assertEquals(0, playerTwo.getGames());

        assertEquals(1, playerOne.getSets());
        assertEquals(0, playerTwo.getSets());
    }

}
