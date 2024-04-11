package Blacklist.Manager.dto;

import Blacklist.Manager.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromDbToItemDto {
    private Long id;

    private String name;


    public FromDbToItemDto(Item item){
        id = item.getId();
        this.name = item.getItemName();
    }


}
