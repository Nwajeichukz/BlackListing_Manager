package Blacklist.Manager.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BlacklistLog")
public class BlacklistLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "blacklist_id", nullable = false)
    private Blacklist blacklist;

    @Column(name = "removed_reason", nullable = false)
    private String removedReason;

    @ManyToOne
    @JoinColumn(name = "removed_by", nullable = false)
    private User removedBy;

    @Column(name = "removed_at", nullable = false)
    private LocalDateTime removedAt;

    // Constructors, getters, and setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blacklist getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Blacklist blacklist) {
        this.blacklist = blacklist;
    }

    public String getRemovedReason() {
        return removedReason;
    }

    public void setRemovedReason(String removedReason) {
        this.removedReason = removedReason;
    }

    public User getRemovedBy() {
        return removedBy;
    }

    public void setRemovedBy(User removedBy) {
        this.removedBy = removedBy;
    }

    public LocalDateTime getRemovedAt() {
        return removedAt;
    }

    public void setRemovedAt(LocalDateTime removedAt) {
        this.removedAt = removedAt;
    }

    @Override
    public String toString() {
        return "BlacklistLog{" +
                "id=" + id +
                ", blacklist=" + blacklist +
                ", removedReason='" + removedReason + '\'' +
                ", removedBy=" + removedBy +
                ", removedAt=" + removedAt +
                '}';
    }
}

