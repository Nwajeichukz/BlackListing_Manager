package Blacklist.Manager.controller;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.ItemDTO;
import Blacklist.Manager.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/all_items")
    public ResponseEntity<AppResponse<Map<String, Object>>> getAllItems(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        return ResponseEntity.ok(itemService.getAllItems(PageRequest.of(page, size)));
    }

    @PreAuthorize("hasRole('ROLE_BLACKLIST_ADMIN')")
    @PostMapping("/create_item")
    public ResponseEntity<AppResponse<String>> createItem(@Valid @RequestBody ItemDTO item) {
        return ResponseEntity.ok(itemService.createItem(item));
    }
}