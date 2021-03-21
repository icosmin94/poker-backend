package com.project.poker.table_manangement.application.service;

import com.project.poker.table_manangement.application.domain.model.PokerTable;
import com.project.poker.table_manangement.application.domain.repository.PokerTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PokerTableService {

    private final PokerTableRepository pokerTableRepository;

    public Page<PokerTable> getPokerTablePage(Pageable pageRequest) {
        return pokerTableRepository.findAll(pageRequest);
    }

    public List<PokerTable> getAllPokerTables() {
        return pokerTableRepository.findAll();
    }
}

