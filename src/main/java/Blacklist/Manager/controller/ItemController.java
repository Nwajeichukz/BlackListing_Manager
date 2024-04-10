package Blacklist.Manager.controller;

import Blacklist.Manager.dto.ItemDTO;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.service.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        return itemService.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody ItemDTO item) {
        if (item.getName() == null || item.getCategoryId() == null) {
            return ResponseEntity.badRequest().build(); // Handle invalid request
        }

        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody ItemDTO item) {

        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        if (itemService.getItemById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}

