package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {

    private static final Map<UUID, MatchScoreDto> allOngoingMatches = new ConcurrentHashMap<>();

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
