package com.Betrand.Promanagment.Implemetation;

import com.Betrand.Promanagment.Model.Issue;
import com.Betrand.Promanagment.Model.Project;
import com.Betrand.Promanagment.Model.User;
import com.Betrand.Promanagment.Repository.IssueRepository;
import com.Betrand.Promanagment.Request.IssueRequest;
import com.Betrand.Promanagment.Service.IssueService;
import com.Betrand.Promanagment.Service.ProjectService;
import com.Betrand.Promanagment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class IssueServiceImpl implements IssueService {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Override
    public Optional<Issue> getIssueById(Long issueId) throws Exception {
        Optional<Issue> issue=issueRepository.findById(issueId);
        if (issue.isPresent()){
            return  issue;
        }
        throw new Exception("No issues found with issuedId"+issueId);
    }

    @Override
    public List<Issue> getIssueByProjectId(Long projectId) throws Exception {
        List<Issue> issues=issueRepository.findByProjectId(projectId);
        return issues;
    }

    @Override
    public Issue createIssue(IssueRequest issueRequest, User user) throws Exception {
        Project project=projectService.getProjectById(issueRequest.getProjectId());
        Issue issue =new Issue() ;
        issue.setTitle(issueRequest.getTitle());
        issue.setDescription(issueRequest.getDescription());
        issue.setStatus(issueRequest.getStatus());
//        issue.setProjectId(issueRequest.getProjectId());
        issue.setDuedate(issueRequest.getDuedate());
        issue.setPriorty(issueRequest.getPriorty());
        issue.setProject(project);
        return issueRepository.save(issue);
    }

    @Override
    public void deleteIssue(Long issueId, Long userId) throws Exception {
        Optional<Issue> issue=issueRepository.findById(issueId);
        issue.ifPresent(value -> issueRepository.delete(value));
        throw new Exception("Could not delete Issue");

    }

    @Override
    public Issue addUserToIssue(Long issueId, Long userId) throws Exception {
        User user=userService.findUserById(userId);
        Optional<Issue> issue=issueRepository.findById(issueId);
        issue.get().setAssignee(user);
        return issueRepository.save(issue.get());
    }

    @Override
    public Issue updateIssueStatus(Long issueId, String status) throws Exception {
        Optional<Issue> issue=issueRepository.findById(issueId);
        if (issue.isPresent()){
            issue.get().setStatus(status);
            return issueRepository.save(issue.get());
        }
        throw new Exception("Issue not found");

    }


}
