package Blacklist.Manager.dto;

import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class FromDbToBlackListDto {
    private Long itemId;

    private String item;
    private String reason;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public FromDbToBlackListDto(Blacklist blacklist) {
        this.itemId = blacklist.getId();
        this.item = blacklist.getItem();
        this.reason = blacklist.getReason();
        this.createdAt = blacklist.getCreatedAt();
        this.updatedAt = blacklist.getUpdatedAt();
    }

}
