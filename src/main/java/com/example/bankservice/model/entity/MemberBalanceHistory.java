package com.example.bankservice.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "member_balance_history")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class MemberBalanceHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "trans_type")
    private TransType transType;
    @Column(name = "from_member_id")
    private Integer fromMemberId;
    @Column(name = "to_member_id")
    private Integer toMemberId;
    private Integer amount;
    @CreatedDate @Column(updatable = false )
    private LocalDateTime createdAt;


    public MemberBalanceHistory (TransType transType, Integer memberId, Integer amount) {
        this.setTransType(transType);
        this.setFromMemberId(memberId);
        this.setAmount(amount);
    }

    public MemberBalanceHistory (TransType transType, Integer fromMemberId, Integer toMemberId, Integer amount)  {
        this.setTransType(transType);
        this.setFromMemberId(fromMemberId);
        this.setAmount(amount);
        this.setToMemberId(toMemberId);
    }

}
