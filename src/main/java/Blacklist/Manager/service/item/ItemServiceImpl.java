package Blacklist.Manager.service.item;

import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.FromDbToItemDto;
import Blacklist.Manager.dto.ItemDTO;
import Blacklist.Manager.entity.Item;
import Blacklist.Manager.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public AppResponse<Map<String, Object>> getAllItems(Pageable pageable) {
       Page<FromDbToItemDto> items = itemRepository.findAll(pageable).map(FromDbToItemDto::new);

        Map<String, Object> page = Map.of(
                "page", items.getNumber(),
                "totalPages", items.getTotalPages(),
                "totalElements", items.getTotalElements(),
                "size", items.getSize(),
                "content", items.getContent()
        );

        return new AppResponse<>("successful", page);
    }


    @Override
    public AppResponse<String> createItem(ItemDTO itemDto) {
        Item newItems = new Item();
        newItems.setItemName(itemDto.getItemName());

        Item savedItem =itemRepository.save(newItems);

        return new AppResponse<>("Item successfully created", savedItem.getItemName());
    }

}