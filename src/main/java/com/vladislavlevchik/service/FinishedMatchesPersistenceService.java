package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.repository.MatchRepository;

import java.util.List;

import static com.vladislavlevchik.utils.MapperUtil.convertToEntity;

public class FinishedMatchesPersistenceService {
    private final MatchRepository matchRepository = new MatchRepository();

    public void persist(MatchScoreDto matchScoreDto) {
        matchRepository.save(convertToEntity(matchScoreDto));
    }

    public List<Match> find(String name, int pageSize, int page) {
        if (name == null) {
            return matchRepository.findAllWithPagination(pageSize, page);
        }

        name = name.toUpperCase();
        return matchRepository.findAllByPlayerNameWithPagination(name, pageSize, page);
    }

    public long getCount(String name) {
        if (name == null) {
            return matchRepository.getCount();
        }

        name = name.toUpperCase();
        return matchRepository.getCountByPlayerName(name);
    }

}
