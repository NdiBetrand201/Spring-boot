package com.Betrand.Promanagment.Service;

import com.Betrand.Promanagment.Model.Issue;
import com.Betrand.Promanagment.Model.User;
import com.Betrand.Promanagment.Request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Optional<Issue> getIssueById(Long issueId)throws Exception;

    List<Issue> getIssueByProjectId(Long projectId) throws Exception;

//    Optional<Issue> updateIssue(Long issueId,IssueRequest updatedIssue,Long userId)throws Exception;

    Issue createIssue(IssueRequest issue, User user) throws Exception;

    void deleteIssue(Long issueId, Long userId)throws Exception;

    Issue addUserToIssue(Long issueId, Long userId)throws Exception;

    Issue updateIssueStatus(Long issueId,String status)throws Exception;


}
