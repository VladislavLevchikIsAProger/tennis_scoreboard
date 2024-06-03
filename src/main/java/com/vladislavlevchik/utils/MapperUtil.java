package com.vladislavlevchik.utils;

import com.vladislavlevchik.dto.MatchResponseDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.entity.Player;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@UtilityClass
public class MapperUtil {
    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MODEL_MAPPER.typeMap(Match.class, MatchResponseDto.class)
                .addMapping(src -> src.getPlayerOne().getName(), MatchResponseDto::setPlayerOne)
                .addMapping(src -> src.getPlayerTwo().getName(), MatchResponseDto::setPlayerTwo)
                .addMapping(src -> src.getWinner().getName(), MatchResponseDto::setWinnerName);

        MODEL_MAPPER.typeMap(Player.class, PlayerScoreDto.class)
                .addMapping(Player::getName, PlayerScoreDto::setPlayerName);
    }

    public static MatchResponseDto convertToDto(Match match) {
        return MODEL_MAPPER.map(match, MatchResponseDto.class);
    }

    public static PlayerScoreDto convertToDto(Player player) {
        return MODEL_MAPPER.map(player, PlayerScoreDto.class);
    }
}
