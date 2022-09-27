package org.lemanoman.websocketserver.rest;

import org.lemanoman.websocketserver.HistoryModel;
import org.lemanoman.websocketserver.repository.HistoryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/history")
public class HistoryRest {

    private final HistoryRepository historyRepository;

    public HistoryRest(HistoryRepository historyRepository){
        this.historyRepository = historyRepository;
    }

    @GetMapping("/")
    public List<HistoryModel> getListHistory(){
        return this.historyRepository.findAll();
    }
}
