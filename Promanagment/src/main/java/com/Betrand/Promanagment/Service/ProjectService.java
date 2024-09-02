package com.Betrand.Promanagment.Service;

import com.Betrand.Promanagment.Model.Chat;
import com.Betrand.Promanagment.Model.Project;
import com.Betrand.Promanagment.Model.User;

import java.util.List;

public interface ProjectService {
    public Project createProject(Project project,User user) throws Exception;

    List<Project> getProjectByTeam(User user, String category, String tag)throws Exception;

    Project getProjectById(Long ProjectId)throws  Exception;

    void deleteProject(Long projectId,Long userId)throws Exception;

    Project updateProject(Project updatedProject,Long ProjectId)throws  Exception;

    void addUserToProject(Long ProjectId,Long userId)throws  Exception;

    void removeUserFromProject(Long ProjectId,Long userId)throws Exception;

    Chat getChatByProjectId(Long projectId)throws Exception;

    List<Project> searchProjects(String keyword, User user)throws Exception;
}
