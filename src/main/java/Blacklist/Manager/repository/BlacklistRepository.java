package Blacklist.Manager.repository;

import Blacklist.Manager.entity.Blacklist;
import Blacklist.Manager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
    // Define custom query methods here if needed

    Blacklist findByItem(Item item);
}

