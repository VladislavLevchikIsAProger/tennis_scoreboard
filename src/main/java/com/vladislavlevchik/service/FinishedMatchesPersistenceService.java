package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.repository.MatchRepository;
import com.vladislavlevchik.utils.MapperUtil;

import java.util.UUID;

import static com.vladislavlevchik.utils.MapperUtil.convertToEntity;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();

    public void persist(MatchScoreDto matchScoreDto){
        matchRepository.save(convertToEntity(matchScoreDto));
    }


}
