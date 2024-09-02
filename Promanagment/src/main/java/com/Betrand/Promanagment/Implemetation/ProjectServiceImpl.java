package com.Betrand.Promanagment.Implemetation;

import com.Betrand.Promanagment.Model.Chat;
import com.Betrand.Promanagment.Model.Project;
import com.Betrand.Promanagment.Model.User;
import com.Betrand.Promanagment.Repository.ProjectRepository;
import com.Betrand.Promanagment.Service.ChatService;
import com.Betrand.Promanagment.Service.ProjectService;
import com.Betrand.Promanagment.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
   private ProjectRepository projectRepository;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project createdProject=new Project();
        createdProject.setOwner(user);
        createdProject.setTag(project.getTag());
        createdProject.setCategory(project.getCategory());
        createdProject.setName(project.getName());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);
        Project savedProject=projectRepository.save(createdProject);

        Chat chat=new Chat();
        chat.setProject(savedProject);

        Chat projectChat=chatService.createChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
       List<Project> projects=projectRepository.findByTeamContainingOrOwner(user,user);

       if(category!=null){
           projects=projects.stream().filter(project -> project.getCategory().equals(category))
                   .collect(Collectors.toList());
       }
        if(tag!=null){
            projects=projects.stream().filter(project -> project.getCategory().equals(tag))
                    .collect(Collectors.toList());
        }
        return projects;
    }

    @Override
    public Project getProjectById(Long ProjectId) throws Exception {
        Optional<Project> project=projectRepository.findById(ProjectId);
        if (project==null){
            throw new Exception("Project not found");
        }
        return project.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
        Optional<Project> project=projectRepository.findById(projectId);
        if(project.get().getOwner().getId()==userId){
            projectRepository.delete(project.get());
        }

    }

    @Override
    public Project updateProject(Project updatedProject, Long ProjectId) throws Exception {
        Project project= getProjectById(ProjectId);

        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setTag(updatedProject.getTag());

        return projectRepository.save(project);


    }

    @Override
    public void addUserToProject(Long ProjectId, Long userId) throws Exception {
        Project project=getProjectById(ProjectId);
        User user=userService.findUserById(userId);
        if(!project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);

    }

    @Override
    public void removeUserFromProject(Long ProjectId, Long userId) throws Exception {
        Project project=getProjectById(ProjectId);
        User user=userService.findUserById(userId);
        if(project.getTeam().contains(user)){
            project.getChat().getUsers().remove(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);

    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project=getProjectById(projectId);

        return project.getChat();
    }

    @Override
    public List<Project> searchProjects(String keyword, User user) throws Exception {

        return projectRepository.findByNameContainingAndTeamContains(keyword,user);

    }
}
