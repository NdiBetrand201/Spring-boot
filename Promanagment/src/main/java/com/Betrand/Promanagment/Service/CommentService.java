package com.Betrand.Promanagment.Service;

import com.Betrand.Promanagment.Model.Comment;
import com.Betrand.Promanagment.Response.MessageResponse;

import java.util.List;

public interface CommentService {
    Comment createComment(Long IssueId,Long userId,String comment )throws Exception;


    MessageResponse deleteComment(Long commentId,Long userId)throws Exception;

    List<Comment> findCommentsByIssuid(Long issueId)throws Exception;
}
