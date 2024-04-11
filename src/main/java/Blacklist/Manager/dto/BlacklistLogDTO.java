package Blacklist.Manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistLogDTO {

    private int blacklistId;
    private String removedReason;
    private int removedByUserId;
    private LocalDateTime removedAt;


}

