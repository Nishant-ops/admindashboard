package com.Alphalyte.Jwt_Admin_dashboard.Service.CRM;


import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.ClientRepository;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.ProjectRepository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ClientRepository clientRepository;

    /****************************************  ADD PROJECT  *************************************************/

    public ResponseEntity<String> addProject(ProjectRequest projectRequest){
        Project project = new Project(projectRequest);
        projectRepository.save(project);
        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }

    /****************************************  ADD FILE TO PROJECT  *****************************************/

    public ResponseEntity<String> addFileToProject(long id, MultipartFile file){

        Project project = projectRepository.getById(id);

        if (Objects.requireNonNull(file.getOriginalFilename()).contains("..")) {
            return new ResponseEntity<>("Invalid file type - " + file.getContentType(), HttpStatus.BAD_REQUEST);
        } else {
            try {
                byte[] fileBytes = file.getBytes();
                project.setFile(fileBytes);
            } catch (IOException e) {
                e.printStackTrace();
                return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }


    /****************************************  UPDATE PROJECT  *************************************************/

    public ResponseEntity<String> updateProject(Long id, ProjectRequest projectRequest ){
        if (projectRepository.existsById(id)){
            Project project = new Project(projectRequest);
            project.setId(id);
            projectRepository.save(project);

            return new ResponseEntity<>("updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }




    /****************************************  DELETE PROJECT  *************************************************/

    @Transactional
    public ResponseEntity<String> deleteProjectById(long id){
        if (projectRepository.existsById(id)){
            Project project = projectRepository.getById(id);
            if (project.getClientId() != null){
                if (clientRepository.existsById(project.getClientId())){

                    clientRepository.getById(project.getClientId())
                            .getProject()
                            .remove(project);

                    projectRepository.deleteById(id);

                    return new ResponseEntity<>("Client mapping and project deleted", HttpStatus.OK);

                } else return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);

            } else projectRepository.deleteById(id);

            return new ResponseEntity<>("Deleted project", HttpStatus.OK);

        }
        return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
    }



}//end ProjectService.java
