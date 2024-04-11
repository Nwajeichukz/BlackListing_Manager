package Blacklist.Manager.repository;

import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long> {
    boolean existsByItem(String item);

    Optional<Blacklist> findByItem(String item);
}

