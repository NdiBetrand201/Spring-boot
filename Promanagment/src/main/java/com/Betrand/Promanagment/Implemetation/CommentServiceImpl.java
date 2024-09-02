package com.Betrand.Promanagment.Implemetation;

import com.Betrand.Promanagment.Model.Comment;
import com.Betrand.Promanagment.Model.User;
import com.Betrand.Promanagment.Repository.CommentRepository;
import com.Betrand.Promanagment.Response.MessageResponse;
import com.Betrand.Promanagment.Service.CommentService;
import com.Betrand.Promanagment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Override
    public Comment createComment(Long IssueId, Long userId, String comment) throws Exception {
        return null;
    }

    @Override
    public MessageResponse deleteComment(Long commentId, Long userId) throws Exception {
        Optional<Comment> comment=commentRepository.findById(commentId);
        Optional<User> user= Optional.ofNullable(userService.findUserById(userId));

        if (comment.isEmpty()){
            throw new Exception("Comment not Found");
        }
        if (user.isEmpty()){
            throw new Exception("user not Found");
        }
      if (comment.get().getUser().equals(user.get())){
          commentRepository.deleteById(commentId);
      }else {
          throw  new Exception("User does not have permission to delete comment");
      }
        return null;
    }

    @Override
    public List<Comment> findCommentsByIssuid(Long issueId) throws Exception {
        List<Comment> comments=commentRepository.findByIssueId(issueId);
        return comments;
    }
}
