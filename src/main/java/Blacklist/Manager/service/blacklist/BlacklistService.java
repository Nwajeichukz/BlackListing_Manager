package Blacklist.Manager.service.blacklist;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.BlacklistDTO;
import Blacklist.Manager.entity.Blacklist;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface BlacklistService {
    AppResponse<String> addBlacklistedItem(BlacklistDTO blacklistDTO);

     AppResponse<Map<String, Object>> getAllBlacklistedItems(Pageable pageable);

     AppResponse<String> removeBlacklistedItem(BlacklistDTO blacklistDTO);
    AppResponse<Blacklist> updateBlacklist(long id, BlacklistDTO blacklistDTO);

    }
