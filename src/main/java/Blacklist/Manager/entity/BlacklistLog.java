package Blacklist.Manager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "BlacklistLog")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BlacklistLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String blacklistItem;

    @Column(name = "removed_reason", nullable = false)
    private String removedReason;

    @JoinColumn(name = "removed_by", nullable = false)
    private String removedBy;

    @Column(name = "removed_at", nullable = false)
    private LocalDateTime removedAt;
}

