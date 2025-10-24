package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity

public class Client {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="client_name", nullable = false)
    private String clientName;

    private String spocName;
    private String division;
    private String location;

    @Column(columnDefinition = "TEXT")
    private String requirement;

    private String status;

    @Column(columnDefinition = "TEXT")
    private String nextSteps;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    @JsonBackReference

    private Partner partner;

    
}


