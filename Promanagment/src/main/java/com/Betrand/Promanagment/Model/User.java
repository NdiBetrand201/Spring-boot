package com.Betrand.Promanagment.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private  String fullname;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
    private List<Issue>assignedIssues= new ArrayList<>();

    private  int projectSize;

}
