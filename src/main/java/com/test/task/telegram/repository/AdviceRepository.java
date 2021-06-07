package com.test.task.telegram.repository;

import com.test.task.telegram.entity.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {

    Advice findByUuid(UUID uuid);

    List<Advice> findAllByCity_NameIgnoreCase(String cityName);
}
