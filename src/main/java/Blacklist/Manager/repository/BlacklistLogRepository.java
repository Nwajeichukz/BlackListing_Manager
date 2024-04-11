package Blacklist.Manager.repository;

import Blacklist.Manager.entity.BlacklistLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistLogRepository extends JpaRepository<BlacklistLog, Long> {}


