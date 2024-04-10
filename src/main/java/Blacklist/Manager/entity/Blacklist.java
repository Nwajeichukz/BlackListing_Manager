package Blacklist.Manager.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Blacklist")
public class Blacklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private String reason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "blacklist")
    private BlacklistLog blacklistLog;

    // Constructors, getters, and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BlacklistLog getBlacklistLog() {
        return blacklistLog;
    }

    public void setBlacklistLog(BlacklistLog blacklistLog) {
        this.blacklistLog = blacklistLog;
    }

    @Override
    public String toString() {
        return "Blacklist{" +
                "id=" + id +
                ", item=" + item +
                ", reason='" + reason + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", blacklistLog=" + blacklistLog +
                '}';
    }
}

