package com.limnstage.laundry.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orders_id_seq"
    )
    @SequenceGenerator(
            name = "orders_id_seq",
            sequenceName = "orders_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(name = "phone_no", nullable = false, length = 15)
    private String phoneNo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Integer shirts = 0;

    @Column(nullable = false)
    private Integer pants = 0;

    @Column(name = "delivery_status", nullable = false, length = 50)
    private String deliveryStatus;

    @Column(
            name = "order_date",
            nullable = false,
            updatable = false
    )
    private OffsetDateTime orderDate;

    @Column(name = "uid", nullable = false)
    private Integer uid;

    @PrePersist
    protected void onCreate() {
        this.orderDate = OffsetDateTime.now();
    }
}