package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.repository.MatchRepository;
import com.vladislavlevchik.utils.MapperUtil;

import java.util.List;
import java.util.UUID;

import static com.vladislavlevchik.utils.MapperUtil.convertToEntity;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();

    public void persist(MatchScoreDto matchScoreDto) {
        matchRepository.save(convertToEntity(matchScoreDto));
    }

    public List<Match> find(String name) {
        if (name == null) {
            return matchRepository.findAll();
        } else {
            name = name.toUpperCase();
            return matchRepository.findAllMatchesByPlayerName(name);
        }
    }

}
