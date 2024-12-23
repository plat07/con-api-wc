package com.pi4.wayclient.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JsonBackReference(value = "customer-ticket")
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JsonBackReference(value = "department-ticket")
    @JoinColumn(name="department_id", referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JsonBackReference(value = "employee-ticket")
    @JoinColumn(name="employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(mappedBy = "ticket")
    private Product product;

    @OneToOne(mappedBy ="ticket")
    private ServiceEntity service;

    @Column(nullable = false)
    private String status;

    @JsonFormat(pattern="dd/MM/yyyy")
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String title;

    public Ticket(UUID id, Customer customer, Department department, Product product, ServiceEntity service,
                  String status, LocalDate date, String description, String title
    ) {
       this.id = id;
       this.customer = customer;
       this.department = department;
       this.product = product;
       this.service = service;
       this.status = status;
       this.date = date;
       this.description = description;
       this.title = title;
    }
}
