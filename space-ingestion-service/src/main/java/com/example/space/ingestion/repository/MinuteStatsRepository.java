package com.example.space.ingestion.repository;

import com.example.space.ingestion.model.MinuteStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MinuteStatsRepository
        extends JpaRepository<MinuteStats, Long> {
}
