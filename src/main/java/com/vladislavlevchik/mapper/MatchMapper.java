package com.vladislavlevchik.mapper;

import com.vladislavlevchik.dto.MatchResponseDto;
import com.vladislavlevchik.entity.Match;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;

public class MatchMapper {
    private static final ModelMapper MODEL_MAPPER;

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        MODEL_MAPPER.typeMap(Match.class, MatchResponseDto.class).
                addMapping(src -> src.getPlayerTwo().getName(), MatchResponseDto::setPlayerOne).
                addMapping(src -> src.getPlayerTwo().getName(), MatchResponseDto::setPlayerTwo).
                addMapping(src -> src.getWinner().getName(), MatchResponseDto::setWinnerName);
    }

    public static MatchResponseDto convertToDto(Match match) {
        return MODEL_MAPPER.map(match, MatchResponseDto.class);
    }
}
