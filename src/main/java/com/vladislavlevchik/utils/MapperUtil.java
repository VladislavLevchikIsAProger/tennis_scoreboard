package com.vladislavlevchik.utils;

import com.vladislavlevchik.dto.MatchResponseDto;
import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.entity.Player;
import lombok.experimental.UtilityClass;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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

        MODEL_MAPPER.typeMap(PlayerScoreDto.class, Player.class)
                .addMapping(PlayerScoreDto::getId, Player::setId)
                .addMapping(PlayerScoreDto::getPlayerName, Player::setName);

        MODEL_MAPPER.typeMap(PlayerRequestDto.class, Player.class)
                .addMapping(PlayerRequestDto::getName, Player::setName);

        Converter<MatchScoreDto, Match> matchScoreDtoToMatchConverter = context -> {
            MatchScoreDto matchScoreDto = context.getSource();

            PlayerScoreDto playerOneDto = matchScoreDto.getPlayerOne();
            PlayerScoreDto playerTwoDto = matchScoreDto.getPlayerTwo();
            PlayerScoreDto winnerDto;
            if (playerOneDto.getSets() > playerTwoDto.getSets()) {
                winnerDto = playerOneDto;
            } else {
                winnerDto = playerTwoDto;
            }

            Player playerOne = MODEL_MAPPER.map(playerOneDto, Player.class);
            Player playerTwo = MODEL_MAPPER.map(playerTwoDto, Player.class);
            Player winner = MODEL_MAPPER.map(winnerDto, Player.class);

            return Match.builder()
                    .playerOne(playerOne)
                    .playerTwo(playerTwo)
                    .winner(winner)
                    .build();
        };

        TypeMap<MatchScoreDto, Match> typeMap = MODEL_MAPPER.createTypeMap(MatchScoreDto.class, Match.class);
        typeMap.setConverter(matchScoreDtoToMatchConverter);

    }

    public static MatchResponseDto convertToDto(Match match) {
        return MODEL_MAPPER.map(match, MatchResponseDto.class);
    }

    public static PlayerScoreDto convertToDto(Player player) {
        return MODEL_MAPPER.map(player, PlayerScoreDto.class);
    }

    public static Match convertToEntity(MatchScoreDto matchScoreDto) {
        return MODEL_MAPPER.map(matchScoreDto, Match.class);
    }

    public static Player convertToEntity(PlayerRequestDto playerRequestDto) {
        return MODEL_MAPPER.map(playerRequestDto, Player.class);
    }

    public static MatchResponseDto convertToMatchResponseDto(MatchScoreDto matchScoreDto) {
        Match matchEntity = convertToEntity(matchScoreDto);
        return convertToDto(matchEntity);
    }

}
