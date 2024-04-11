package Blacklist.Manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistDTO {
    @NotEmpty(message = "itemName should not be blank")
    private String itemName;
    @NotEmpty(message = "Reasons should not be blank")
    private String reason;

}
