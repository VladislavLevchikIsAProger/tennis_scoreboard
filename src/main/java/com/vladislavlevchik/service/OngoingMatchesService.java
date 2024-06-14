package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.vladislavlevchik.utils.MapperUtil.convertToDto;

public class OngoingMatchesService {
    private static final Map<UUID, MatchScoreDto> allOngoingMatches = new ConcurrentHashMap<>();
    private final PlayerPersistenceService playerPersistenceService = new PlayerPersistenceService();

    public MatchScoreDto findById(UUID uuid) {
        return allOngoingMatches.get(uuid);
    }

    public void removeFromMatches(UUID uuid) {
        allOngoingMatches.remove(uuid);
    }

    public UUID createMatch(PlayerRequestDto playerOneDto, PlayerRequestDto playerTwoDto) {
        Player playerOne = playerPersistenceService.findOrSave(playerOneDto);

        Player playerTwo = playerPersistenceService.findOrSave(playerTwoDto);

        MatchScoreDto matchScoreDto = MatchScoreDto.builder()
                .playerOne(convertToDto(playerOne))
                .playerTwo(convertToDto(playerTwo))
                .build();

        UUID uuid = UUID.randomUUID();

        allOngoingMatches.put(uuid, matchScoreDto);

        return uuid;
    }
}
