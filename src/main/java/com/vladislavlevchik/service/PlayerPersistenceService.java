package com.vladislavlevchik.service;

import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.repository.PlayerRepository;
import com.vladislavlevchik.utils.MapperUtil;

import static com.vladislavlevchik.utils.MapperUtil.convertToEntity;

public class PlayerPersistenceService {

    private final PlayerRepository playerRepository = new PlayerRepository();

    public Player findOrSave(PlayerRequestDto playerDto) {
        return playerRepository.findByName(playerDto.getName())
                .orElseGet(() -> playerRepository.save(convertToEntity(playerDto)));
    }

}
