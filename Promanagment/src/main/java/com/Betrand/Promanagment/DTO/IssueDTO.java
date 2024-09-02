package com.Betrand.Promanagment.DTO;

import com.Betrand.Promanagment.Model.Project;
import com.Betrand.Promanagment.Model.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueDTO {
    private Long id;
    private  String title;
    private  String description;
    private String status;

    private Long projectID;
    private  String priorty;
    private LocalDate duedate;
    private List<String> tags=new ArrayList<>();

    private User assignee;
    private Project project;
}
