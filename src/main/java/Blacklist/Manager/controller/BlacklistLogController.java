package Blacklist.Manager.controller;

import Blacklist.Manager.entity.BlacklistLog;
import Blacklist.Manager.service.blacklistLog.BlacklistLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blacklist-logs")
public class BlacklistLogController {

    @Autowired
    private BlacklistLogService blacklistLogService;

    @GetMapping
    public ResponseEntity<List<BlacklistLog>> getAllBlacklistLogs() {
        List<BlacklistLog> blacklistLogs = blacklistLogService.getAllBlacklistLogs();
        return ResponseEntity.ok(blacklistLogs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlacklistLog> getBlacklistLogById(@PathVariable int id) {
        return blacklistLogService.getBlacklistLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BlacklistLog> createBlacklistLog(@RequestBody BlacklistLog blacklistLog) {
        BlacklistLog createdBlacklistLog = blacklistLogService.createBlacklistLog(blacklistLog);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBlacklistLog);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlacklistLog> updateBlacklistLog(@PathVariable int id, @RequestBody BlacklistLog blacklistLog) {
        if (blacklistLogService.getBlacklistLogById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
//        blacklistLog.setId(id); // Ensure the ID is set
        BlacklistLog updatedBlacklistLog = blacklistLogService.updateBlacklistLog(blacklistLog);
        return ResponseEntity.ok(updatedBlacklistLog);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlacklistLog(@PathVariable int id) {
        if (blacklistLogService.getBlacklistLogById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        blacklistLogService.deleteBlacklistLog(id);
        return ResponseEntity.noContent().build();
    }
}

