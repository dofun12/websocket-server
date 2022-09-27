package org.lemanoman.websocketserver;

import org.lemanoman.websocketserver.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public HistoryModel saveMax(HistoryModel historyModel, int max) {
        HistoryModel saved = this.historyRepository.save(historyModel);
        if (saved.getId() == null || saved.getId() <= max) {
            return saved;
        }
        final Long savedId = saved.getId();
        Long idToBeDeleted = (savedId - max);
        Optional<HistoryModel> selected = this.historyRepository.findById(idToBeDeleted);
        if(!selected.isPresent()){
            return saved;
        }

        this.historyRepository.delete(selected.get());
        return saved;
    }
}
