package com.icommerce.audit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity(name="AUDIT")
@javax.persistence.Table()
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="product_code")
    private String productCode;

    @Column(name="activity")
    private String activity;

    @Column(name="query")
    private String query;

    @Column(name="filter")
    private String filter;

    @Column(name="sort_by")
    private String sortBy;

    @Column(name="sort_order")
    private String order;

    @Column(name="page")
    private int page;

    @Column(name="time_stamp")
    @Transient
    private Timestamp timestamp;
}
