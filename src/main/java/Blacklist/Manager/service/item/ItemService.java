package Blacklist.Manager.service.item;


import Blacklist.Manager.dto.AppResponse;
import Blacklist.Manager.dto.ItemDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface ItemService {
    AppResponse<Map<String, Object>> getAllItems(Pageable pageable);

    AppResponse<String> createItem(ItemDTO itemDto);
}
