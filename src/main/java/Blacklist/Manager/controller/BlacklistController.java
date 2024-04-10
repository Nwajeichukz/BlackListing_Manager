package Blacklist.Manager.controller;

import Blacklist.Manager.dto.BlacklistDTO;
import Blacklist.Manager.dto.ItemDTO;
import Blacklist.Manager.dto.RemoveBlacklistDTO;
import Blacklist.Manager.entity.BlacklistLog;
import Blacklist.Manager.service.blacklist.BlacklistService;
import Blacklist.Manager.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blacklist")
public class BlacklistController {


    private final BlacklistService blacklistService;
    private final ItemService itemService;

    @Autowired
    public BlacklistController(BlacklistService blacklistService, ItemService itemService) {
        this.blacklistService = blacklistService;
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<BlacklistDTO>> getAllBlacklistedItems() {
        List<BlacklistDTO> blacklistItems = blacklistService.getAllBlacklistedItems();
        return new ResponseEntity<>(blacklistItems, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlacklistDTO> getBlacklistById(@PathVariable int id) {
        BlacklistDTO blacklistDTO = blacklistService.getBlacklistById(id);
        return ResponseEntity.ok(blacklistDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<BlacklistDTO> addBlacklistedItem(@RequestBody BlacklistDTO blacklistDTO) {
        BlacklistDTO addedItem = blacklistService.addBlacklistedItem(blacklistDTO.getItemId(), blacklistDTO.getReason());
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @PostMapping("/remove")
    public ResponseEntity<BlacklistLog> removeBlacklistedItem(@RequestBody RemoveBlacklistDTO blacklistDTO ) {
        System.out.println("blacklistDTO.getItemId(): "+ blacklistDTO);
        BlacklistLog blacklistLog = blacklistService.removeBlacklistedItem(blacklistDTO.getItemId(), blacklistDTO.getReason());
        return new ResponseEntity<>(blacklistLog, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlacklistDTO> updateBlacklist(@PathVariable int id, @RequestBody BlacklistDTO blacklistDTO) {
        BlacklistDTO updatedBlacklist = blacklistService.updateBlacklist(id, blacklistDTO);
        return ResponseEntity.ok(updatedBlacklist);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlacklist(@PathVariable int id) {
        blacklistService.deleteBlacklist(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/not-blacklisted")
    public ResponseEntity<List<ItemDTO>> getItemsNotBlacklisted() {
        List<ItemDTO> itemsNotBlacklisted = itemService.getItemsNotBlacklisted();
        return new ResponseEntity<>(itemsNotBlacklisted, HttpStatus.OK);
    }
}
