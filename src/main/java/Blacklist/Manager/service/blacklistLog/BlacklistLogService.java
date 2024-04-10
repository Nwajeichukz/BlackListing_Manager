package Blacklist.Manager.service.blacklistLog;

import Blacklist.Manager.entity.BlacklistLog;
import Blacklist.Manager.repository.BlacklistLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BlacklistLogService {

    @Autowired
    private BlacklistLogRepository blacklistLogRepository;

    public List<BlacklistLog> getAllBlacklistLogs() {
        return blacklistLogRepository.findAll();
    }

    public Optional<BlacklistLog> getBlacklistLogById(int id) {
        return blacklistLogRepository.findById(id);
    }

    public BlacklistLog createBlacklistLog(BlacklistLog blacklistLog) {
        return blacklistLogRepository.save(blacklistLog);
    }

    public BlacklistLog updateBlacklistLog(BlacklistLog blacklistLog) {
        return blacklistLogRepository.save(blacklistLog);
    }

    public void deleteBlacklistLog(int id) {
        blacklistLogRepository.deleteById(id);
    }
}

