package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Player;

import java.util.Optional;

public interface IPlayerDao extends CrudDao<Player, Long>{

    Optional<Player> findByName(String name);

}
