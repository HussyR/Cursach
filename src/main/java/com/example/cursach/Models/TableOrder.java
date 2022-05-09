package com.example.cursach.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tbl")
@Data
public class TableOrder {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String time;
    private String numberOfPersons;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
