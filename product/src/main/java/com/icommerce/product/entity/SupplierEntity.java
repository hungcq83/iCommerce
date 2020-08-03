package com.icommerce.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "supplier")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class SupplierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "supplier")
    private List<ProductEntity> products;

    @Column(name="modified_date")
    @Transient
    private Timestamp modifiedDate;
}
