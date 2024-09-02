package com.Betrand.Promanagment.Controller;

import com.Betrand.Promanagment.DTO.IssueDTO;
import com.Betrand.Promanagment.Model.Issue;
import com.Betrand.Promanagment.Model.User;
import com.Betrand.Promanagment.Request.IssueRequest;
import com.Betrand.Promanagment.Response.AuthResponse;
import com.Betrand.Promanagment.Response.MessageResponse;
import com.Betrand.Promanagment.Service.IssueService;
import com.Betrand.Promanagment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @GetMapping("{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId)throws Exception{
       Optional<Issue> issue=issueService.getIssueById(issueId);
        return new ResponseEntity<>(issue.get(), HttpStatus.OK);
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
       List<Issue> issues= issueService.getIssueByProjectId(projectId);
        return new ResponseEntity<>(issues,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IssueDTO>createIssue(@RequestBody IssueRequest issue,
                                               @RequestHeader("Authorization") String token)
            throws Exception{
        User user=userService.findUserProfileByJwt(token);
//

            Issue createdIssue=issueService.createIssue(issue,user);

            IssueDTO issueDTO=new IssueDTO();
            issueDTO.setDescription(issue.getDescription());
            issueDTO.setDuedate(issue.getDuedate());
            issueDTO.setId(createdIssue.getId());
            issueDTO.setPriorty(issue.getPriorty());
            issueDTO.setProject(createdIssue.getProject());
            issueDTO.setProjectID(createdIssue.getProjectID());
            issueDTO.setStatus(createdIssue.getStatus());
            issueDTO.setTitle(createdIssue.getTitle());
            issueDTO.setTags(createdIssue.getTags());
            issueDTO.setAssignee(createdIssue.getAssignee());

            return  new ResponseEntity<>(issueDTO,HttpStatus.CREATED);

    }
    @DeleteMapping("/deleteIssue/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                    @RequestHeader("Authorization")String token)
        throws Exception{
        User user=userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId,user.getId());
        MessageResponse res=new MessageResponse();
        res.setMessage("Issue Deleted");

        return new  ResponseEntity<>(res,HttpStatus.OK);

    }

    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,
                                                @PathVariable Long userId)
        throws Exception{
        Issue issue=issueService.addUserToIssue(issueId,userId);
        return  new ResponseEntity<>(issue,HttpStatus.OK);
    }

   @PutMapping("{issueId}/status/{status}")
    public ResponseEntity<Issue>updateIssueStatus(
            @PathVariable String status,
            @PathVariable Long issueId
   )throws  Exception{
        Issue issue=issueService.updateIssueStatus(issueId,status);
        return ResponseEntity.ok(issue);
   }

}
