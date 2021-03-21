package com.project.poker.table_manangement.application.domain.repository;

import com.project.poker.table_manangement.application.domain.model.PokerTable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PokerTableRepository extends PagingAndSortingRepository<PokerTable, Integer> {

    List<PokerTable> findAll();

}
