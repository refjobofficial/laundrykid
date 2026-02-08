package com.limnstage.laundry.model;

//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.OffsetDateTime;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
        @Id
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "users_id_seq"
        )
        @SequenceGenerator(
                name = "users_id_seq",
                sequenceName = "users_id_seq",
                allocationSize = 1
        )
        private Long id;

        @Column(length = 150, nullable = false)
        private String name;

        @Column(length = 255, nullable = false, unique = true)
        private String email;

        @Column(name = "phone_no", length = 15)
        private String phoneNo;

        @Column(length = 30)
        private String offers;

        @Column(name = "is_active", nullable = false)
        private Boolean isActive = true;

        @Column(
                name = "created_at",
                nullable = false,
                updatable = false
        )
        private OffsetDateTime createdAt;

        @PrePersist
        protected void onCreate() {
                this.createdAt = OffsetDateTime.now();
        }
}
