package com.vladislavlevchik.utils;

import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.exception.SameNameException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {

    public static void validate(PlayerRequestDto playerOne, PlayerRequestDto playerTwo) {
        String playerOneName = playerOne.getName();
        String playerTwoName = playerTwo.getName();

        if (playerOneName.equals(playerTwoName)) {
            throw new SameNameException("Players must be different.");
        }
    }

}
