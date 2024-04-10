package Blacklist.Manager.dto;

import java.time.LocalDateTime;

public class BlacklistLogDTO {

    private int id;
    private int blacklistId;
    private String removedReason;
    private int removedByUserId;
    private LocalDateTime removedAt;

    // Constructors

    public BlacklistLogDTO() {
    }

    public BlacklistLogDTO(int id, int blacklistId, String removedReason, int removedByUserId, LocalDateTime removedAt) {
        this.id = id;
        this.blacklistId = blacklistId;
        this.removedReason = removedReason;
        this.removedByUserId = removedByUserId;
        this.removedAt = removedAt;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlacklistId() {
        return blacklistId;
    }

    public void setBlacklistId(int blacklistId) {
        this.blacklistId = blacklistId;
    }

    public String getRemovedReason() {
        return removedReason;
    }

    public void setRemovedReason(String removedReason) {
        this.removedReason = removedReason;
    }

    public int getRemovedByUserId() {
        return removedByUserId;
    }

    public void setRemovedByUserId(int removedByUserId) {
        this.removedByUserId = removedByUserId;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    // toString method (for debugging/logging)

    @Override
    public String toString() {
        return "BlacklistLogDTO{" +
                "id=" + id +
                ", blacklistId=" + blacklistId +
                ", removedReason='" + removedReason + '\'' +
                ", removedByUserId=" + removedByUserId +
                ", removedAt=" + removedAt +
                '}';
    }
}

