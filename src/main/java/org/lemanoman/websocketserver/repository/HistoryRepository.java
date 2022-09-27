package org.lemanoman.websocketserver.repository;

import org.lemanoman.websocketserver.HistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryModel, Long> {
}
