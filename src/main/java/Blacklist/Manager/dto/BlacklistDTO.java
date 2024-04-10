package Blacklist.Manager.dto;

import java.time.LocalDateTime;

public class BlacklistDTO {

    private int id;
    private int itemId;
    private String reason;

    // Constructors

    public BlacklistDTO() {
    }

    public BlacklistDTO(int id, int itemId, String reason) {
        this.id = id;
        this.itemId = itemId;
        this.reason = reason;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItem(int itemId) {
        this.itemId = itemId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    // toString method (for debugging/logging)

    @Override
    public String toString() {
        return "BlacklistDTO{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
