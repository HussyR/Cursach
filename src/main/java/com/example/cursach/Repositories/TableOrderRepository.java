package com.example.cursach.Repositories;


import com.example.cursach.Models.TableOrder;
import com.example.cursach.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableOrderRepository extends JpaRepository<TableOrder, Long> {
    List<TableOrder> findByUser(User user);
}
