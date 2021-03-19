package com.project.poker.table_manangement.api;

import com.project.poker.table_manangement.api.model.PokerTableDto;
import com.project.poker.table_manangement.application.service.PokerTableService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PokerController {

    private final PokerTableService pokerTableService;
    private final ModelMapper modelMapper;

    @GetMapping("tables")
    public Page<PokerTableDto> getPokerTables(Pageable pageRequest) {
        return pokerTableService.getPokerTablePage(pageRequest)
                .map(pokerTable -> modelMapper.map(pokerTable, PokerTableDto.class));
    }
}
