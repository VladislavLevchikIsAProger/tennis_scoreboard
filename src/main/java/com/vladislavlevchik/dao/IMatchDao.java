package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Match;

import java.util.List;
import java.util.Optional;

public interface IMatchDao extends CrudDao<Match, Long> {
    List<Match> findAllMatchesByPlayerName(String name);

}
