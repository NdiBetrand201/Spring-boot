package com.Betrand.Promanagment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  String title;
    private  String description;
    private String status;

    private Long projectID;
    private  String priorty;
    private LocalDate duedate;
    private List<String> tags=new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "issue",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Comment>comments=new ArrayList<>();

    @ManyToOne
    private User assignee;

    @ManyToOne
    private Project project;
}
