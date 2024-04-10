package Blacklist.Manager.service.blacklist;

import Blacklist.Manager.dto.BlacklistDTO;
import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.BlacklistLog;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.BlacklistLogRepository;
import Blacklist.Manager.repository.BlacklistRepository;
import Blacklist.Manager.repository.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final BlacklistLogRepository blacklistLogRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public BlacklistService(BlacklistRepository blacklistRepository, BlacklistLogRepository blacklistLogRepository, ItemRepository itemRepository) {
        this.blacklistRepository = blacklistRepository;
        this.blacklistLogRepository = blacklistLogRepository;
        this.itemRepository = itemRepository;
    }

    public List<BlacklistDTO> getAllBlacklistedItems() {
        List<Blacklist> blacklistItems = blacklistRepository.findAll();
        if(blacklistItems.isEmpty()) throw new ApiException("Blacklist is empty");
        return blacklistItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BlacklistDTO getBlacklistById(int id) {
        Blacklist blacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new ApiException("Blacklist not found with id: " + id));
        return convertToDTO(blacklist);
    }


    @Transactional
    public BlacklistDTO addBlacklistedItem(int itemId, String reason) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiException("Item not found with id: " + itemId));

        Blacklist existingBlacklist = blacklistRepository.findByItem(item);
        if (existingBlacklist != null) {
            throw new ApiException("Item is already blacklisted");
        }

        Blacklist blacklist = new Blacklist();
        blacklist.setItem(item);
        blacklist.setReason(reason);
        blacklist.setCreatedAt(LocalDateTime.now());
        blacklist.setUpdatedAt(LocalDateTime.now());

        Blacklist savedBlacklist = blacklistRepository.save(blacklist);
        return convertToDTO(savedBlacklist);
    }

    @Transactional
    public BlacklistLog removeBlacklistedItem(int itemId, String reason) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ApiException("Item not found with id: " + itemId));
System.out.println("item: " +item);
        Blacklist blacklist = blacklistRepository.findByItem(item);
        System.out.println("blacklist: " +blacklist);
        if (blacklist == null) {
            throw new ApiException("Item is not blacklisted");
        }

        blacklistRepository.delete(blacklist);

        // Create BlacklistLog for item removal
        BlacklistLog blacklistLog = new BlacklistLog();
        blacklistLog.setBlacklist(blacklist);
        blacklistLog.setRemovedReason(reason);
        blacklistLog.setRemovedAt(LocalDateTime.now());

        return blacklistLogRepository.save(blacklistLog);
    }

    public BlacklistDTO updateBlacklist(int id, BlacklistDTO blacklistDTO) {
        Blacklist existingBlacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new ApiException("Blacklist not found with id: " + id));

        BeanUtils.copyProperties(blacklistDTO, existingBlacklist);
        Blacklist updatedBlacklist = blacklistRepository.save(existingBlacklist);
        return convertToDTO(updatedBlacklist);
    }

    public void deleteBlacklist(int id) {
        blacklistRepository.deleteById(id);
    }

    private BlacklistDTO convertToDTO(Blacklist blacklist) {
        BlacklistDTO blacklistDTO = new BlacklistDTO();
        blacklistDTO.setId(blacklist.getId());
        blacklistDTO.setItem(blacklist.getItem().getId());
        blacklistDTO.setReason(blacklist.getReason());
//        blacklistDTO.setCreatedAt(blacklist.getCreatedAt());
//        blacklistDTO.setUpdatedAt(blacklist.getUpdatedAt());
        return blacklistDTO;
    }
}
