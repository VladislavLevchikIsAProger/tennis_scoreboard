package com.vladislavlevchik.service;

import com.vladislavlevchik.entity.MatchScore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {

    //TODO мб коллекция потокобезопасная?
    private final Map<UUID, MatchScore> allOngoingMatches = new HashMap<>();

    public MatchScore findById(String uuidString) {
        UUID uuid = UUID.fromString(uuidString);
        return allOngoingMatches.get(uuid);
    }

    public void addMatch(UUID uuid, MatchScore matchScore) {
        allOngoingMatches.put(uuid, matchScore);
    }

    public void removeFromMatches(UUID uuid) {
        allOngoingMatches.remove(uuid);
    }
}
