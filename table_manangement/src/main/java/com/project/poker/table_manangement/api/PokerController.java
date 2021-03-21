package com.project.poker.table_manangement.api;

import com.project.poker.table_manangement.api.model.PokerTableDto;
import com.project.poker.table_manangement.application.service.PokerTableService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PokerController {

    private final PokerTableService pokerTableService;
    private final ModelMapper modelMapper;

    @GetMapping("tables-paged")
    public Page<PokerTableDto> getPokerTablesPaged(Pageable pageRequest) {
        return pokerTableService.getPokerTablePage(pageRequest)
                .map(pokerTable -> modelMapper.map(pokerTable, PokerTableDto.class));
    }

    @GetMapping("tables-all")
    public List<PokerTableDto> getPokerTablesAll() {
        return pokerTableService.getAllPokerTables().stream()
                .map(pokerTable -> modelMapper.map(pokerTable, PokerTableDto.class))
                .collect(Collectors.toList());
    }
}
