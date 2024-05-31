package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    //TODO мб коллекция потокобезопасная?
    private static final Map<UUID, MatchScoreDto> allOngoingMatches = new HashMap<>();

    public MatchScoreDto findById(UUID uuid) {
        return allOngoingMatches.get(uuid);
    }

    public void addMatch(UUID uuid, MatchScoreDto matchScoreDto) {
        allOngoingMatches.put(uuid, matchScoreDto);
    }

    public void removeFromMatches(UUID uuid) {
        allOngoingMatches.remove(uuid);
    }
}
