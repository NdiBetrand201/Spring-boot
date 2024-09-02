package com.Betrand.Promanagment.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
@Data
public class IssueRequest {
    private Long id;
    private  String title;
    private  String description;
    private String status;
    @JsonIgnore
    private Long projectId;
    private  String priorty;
    private LocalDate duedate;
}
