package Blacklist.Manager.service.blacklist;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.BlacklistDTO;
import Blacklist.Manager.dto.FromDbToBlackListDto;
import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.BlacklistLog;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.BlacklistLogRepository;
import Blacklist.Manager.repository.BlacklistRepository;
import Blacklist.Manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BlacklistServiceImpl implements BlacklistService{

    private final BlacklistRepository blacklistRepository;
    private final BlacklistLogRepository blacklistLogRepository;
    private final ItemRepository itemRepository;



    @Override
    public AppResponse<Map<String, Object>> getAllBlacklistedItems(Pageable pageable) {
        Page<FromDbToBlackListDto> dbToBlackListDtoPage = blacklistRepository.findAll(pageable).map(FromDbToBlackListDto::new);

        Map<String, Object> page = Map.of(
                "page", dbToBlackListDtoPage.getNumber(),
                "totalPages", dbToBlackListDtoPage.getTotalPages(),
                "totalElements", dbToBlackListDtoPage.getTotalElements(),
                "size", dbToBlackListDtoPage. getSize(),
                "content", dbToBlackListDtoPage.getContent()
        );

        return new AppResponse<>("successful", page);
    }

    @Override
    public AppResponse<String> addBlacklistedItem(BlacklistDTO blacklistDTO) {
        Item item = itemRepository.findByItemName(blacklistDTO.getItemName())
                .orElseThrow(() -> new ApiException("Item not found with id"));

        String nameOfItem = item.getItemName();


        boolean existingBlacklist = blacklistRepository.existsByItem(nameOfItem);
        if (existingBlacklist) {
            return new AppResponse<>(0, "item already blacklisted");
        }

        Blacklist blacklist = new Blacklist();
        blacklist.setItem(nameOfItem);
        blacklist.setReason(blacklistDTO.getReason());
        blacklist.setCreatedAt(LocalDateTime.now());
        blacklist.setUpdatedAt(LocalDateTime.now());

        blacklistRepository.save(blacklist);

        itemRepository.delete(item);

        return new AppResponse<>("successfully added too the blacklist", nameOfItem);
    }


    @Override
    public AppResponse<String> removeBlacklistedItem(BlacklistDTO blacklistDTO) {

        Blacklist blacklist = blacklistRepository.findByItem(blacklistDTO.getItemName()).
                orElseThrow(() -> new ApiException("item not found"));


        blacklistRepository.delete(blacklist);

        BlacklistLog blacklistLog = new BlacklistLog();
        blacklistLog.setBlacklistItem(blacklistDTO.getItemName());
        blacklistLog.setRemovedReason(blacklistDTO.getReason());
        blacklistLog.setRemovedAt(LocalDateTime.now());

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        blacklistLog.setRemovedBy(username);

        Item sendItem = new Item();
        sendItem.setItemName(blacklistDTO.getItemName());

        itemRepository.save(sendItem);

        blacklistLogRepository.save(blacklistLog);

        return new AppResponse<>(0,"successfully remove from blacklist");
    }

    public AppResponse<Blacklist> updateBlacklist(long id, BlacklistDTO blacklistDTO) {
        Blacklist existingBlacklist = blacklistRepository.findById(id)
                .orElseThrow(() -> new ApiException("Blacklist not found"));

        BeanUtils.copyProperties(blacklistDTO, existingBlacklist);
        Blacklist updatedBlacklist = blacklistRepository.save(existingBlacklist);

        return new AppResponse<>("succesfully updated", updatedBlacklist);

    }



}
