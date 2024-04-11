package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.BlacklistDTO;

import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.service.blacklist.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/blacklist")
@RequiredArgsConstructor
public class BlacklistController {
    private final BlacklistService blacklistService;


    @PreAuthorize("hasRole('ROLE_BLACKLIST_ADMIN')")
    @GetMapping
    public ResponseEntity<AppResponse<Map<String, Object>>> getAllBlacklistedItems(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(blacklistService.getAllBlacklistedItems(PageRequest.of(page, size)));
    }

    @PreAuthorize("hasRole('ROLE_BLACKLIST_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<AppResponse<String>> addBlacklistedItem(@Valid @RequestBody BlacklistDTO blacklistDTO) {
        return ResponseEntity.ok(blacklistService.addBlacklistedItem(blacklistDTO));
    }

    @PreAuthorize("hasRole('ROLE_BLACKLIST_ADMIN')")
    @PostMapping("/remove")
    public ResponseEntity<AppResponse<String>> removeBlacklistedItem(@Valid @RequestBody BlacklistDTO blacklistDTO ) {
        return ResponseEntity.ok(blacklistService.removeBlacklistedItem(blacklistDTO));
    }

    @PreAuthorize("hasRole('ROLE_BLACKLIST_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AppResponse<Blacklist>> updateBlacklist(@PathVariable long id, @RequestBody @Valid BlacklistDTO blacklistDTO) {
        return ResponseEntity.ok(blacklistService.updateBlacklist(id, blacklistDTO));
    }
}