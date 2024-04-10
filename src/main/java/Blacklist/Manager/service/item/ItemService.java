package Blacklist.Manager.service.item;

import Blacklist.Manager.dto.ItemDTO;
import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.Category;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.exception.ApiException;
import Blacklist.Manager.repository.BlacklistRepository;
import Blacklist.Manager.repository.CategoryRepository;
import Blacklist.Manager.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {


    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private BlacklistRepository blacklistRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository, BlacklistRepository blacklistRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.blacklistRepository = blacklistRepository;
    }

    public void addToBlacklist(Blacklist blacklist) {
        blacklistRepository.save(blacklist);
    }

    public Optional<Item> findById(int id) {
        return itemRepository.findById(id);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(int id) {
        return itemRepository.findById(id);
    }

    public Item createItem(ItemDTO item) {
        Item items = new Item();
        // Fetch category only if categoryId is provided
        if (item.getCategoryId() != null) {
            Category category = categoryRepository.findById(item.getCategoryId())
                    .orElseThrow(() -> new ApiException("Category not found"));
            items.setCategory(category);
        }
        items.setName(item.getName());
        return itemRepository.save(items);
    }


    public Item updateItem(int id, ItemDTO item) {
        Item updatedItem = itemRepository.findById(id)
                .orElseThrow(() -> new ApiException("Item not found"));
        if (item.getName() != null) {
            updatedItem.setName(item.getName());
        }
        if (item.getCategoryId() != null) {
            Category category = categoryRepository.findById(item.getCategoryId())
                    .orElseThrow(() -> new ApiException("Category not found"));
            updatedItem.setCategory(category);
        }
        return itemRepository.save(updatedItem);
    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }


    public List<ItemDTO> getItemsNotBlacklisted() {
        try {
            // Get all items that are not blacklisted
            List<Item> allItems = itemRepository.findAll();
            List<Blacklist> blacklistedItems = blacklistRepository.findAll();

            // Filter out blacklisted items
            List<Integer> blacklistedItemIds = blacklistedItems.stream()
                    .map(blacklist -> blacklist.getItem().getId())
                    .collect(Collectors.toList());

            List<ItemDTO> itemsNotBlacklisted = allItems.stream()
                    .filter(item -> !blacklistedItemIds.contains(item.getId()))
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
if(itemsNotBlacklisted.isEmpty()) throw new ApiException("No items found");
            return itemsNotBlacklisted;
        } catch (Exception e) {
            // Handle the exception appropriately
            e.printStackTrace(); // For logging purposes
            throw new ApiException("Failed to retrieve items not blacklisted"+ e);
        }
    }
    private ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setCategoryId(item.getCategory().getId());
        // Add more properties to the DTO as needed

        return itemDTO;
    }
}

