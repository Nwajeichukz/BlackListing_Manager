package Blacklist.Manager.repository;

import Blacklist.Manager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    // Define custom query methods here if needed
}

