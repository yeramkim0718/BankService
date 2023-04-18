package com.example.bankservice.repository;

import com.example.bankservice.model.entity.MemberBalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberBalanceHistoryRepository extends JpaRepository<MemberBalanceHistory, Integer> {
}
