package com.Alphalyte.Jwt_Admin_dashboard.Service.CRM;

import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Client;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Note;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.ClientRepository;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.NotesRepository;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.ProjectRepository;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserRepository;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ClientRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.NoteRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.ClientFullView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    NotesRepository notesRepository;
    

    /****************************************  ADD CLIENT  *************************************************/


    public ResponseEntity<String> addClient(ClientRequest clientRequest, MultipartFile file){

        Client client = new Client(clientRequest.getFirstName(), clientRequest.getLastName(), clientRequest.getPhone1(), clientRequest.getPhone2(), clientRequest.getLandline(),
                clientRequest.getEmail1(), clientRequest.getEmail2(), clientRequest.getAddress(), clientRequest.getCountry(), clientRequest.getState(), clientRequest.getCity(), clientRequest.getRemark(), clientRequest.getRegDate(),
                clientRequest.getDob(), clientRequest.getGender());




        final boolean b = !(null == file);
        if (b)  {
            if (Objects.requireNonNull(file.getOriginalFilename()).contains("..") || !(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(), "image/png"))) {

                return new ResponseEntity<>("Invalid file type - " + file.getContentType(), HttpStatus.BAD_REQUEST);

            } else {
                try {
                    byte[] image = file.getBytes();
                    client.setImage(image);
                } catch (IOException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
                }
            }
        }
        clientRepository.save(client);

        List<Project> projects = new ArrayList<>();
        for (long id: clientRequest.getProjects()) {

            if (projectRepository.existsById(id)){
                Project project = projectRepository.getById(id);
                project.setClientId(client.getId());
                projectRepository.save(project);
                projects.add(projectRepository.getById(id));

            }
            else return new ResponseEntity<>("Invalid Project Id", HttpStatus.NOT_FOUND );
        }
        client.setProject(projects);

        clientRepository.save(client);

        return new ResponseEntity<>("Saved", HttpStatus.CREATED);
    }


    /****************************************  UPDATE CLIENT **********************************************/

    public ResponseEntity<String> updateClient(long id, ClientRequest clientRequest, MultipartFile file){

        if (clientRepository.existsById(id)){

            Client client = clientRepository.getById(id);

            client.setFirstName(clientRequest.getFirstName());
            client.setLastName(clientRequest.getLastName());
            client.setEmail1(clientRequest.getEmail1());
            client.setEmail2(clientRequest.getEmail2());
            client.setPhone1(clientRequest.getPhone1());
            client.setPhone2(clientRequest.getPhone2());
            client.setAddress(clientRequest.getAddress());
            client.setCity(clientRequest.getCity());
            client.setCountry(clientRequest.getCountry());
            client.setState(clientRequest.getState());
            client.setGender(clientRequest.getGender());
            client.setLandline(clientRequest.getLandline());
            client.setRemark(clientRequest.getRemark());
            client.setRegDate(clientRequest.getRegDate());
            client.setDob(clientRequest.getDob());


            List<Project> projects = new ArrayList<>();
            for (long pid: clientRequest.getProjects()) {

                if (projectRepository.existsById(pid)){
                    Project project = projectRepository.getById(pid);
                    project.setClientId(client.getId());
                    projectRepository.save(project);
                    projects.add(projectRepository.getById(pid));

                }
                else return new ResponseEntity<>("Invalid Project Id", HttpStatus.NOT_FOUND );
            }
            client.setProject(projects);


            final boolean b = !(null == file);
            if (b) {
                if (Objects.requireNonNull(file.getOriginalFilename()).contains("..") || !(Objects.equals(file.getContentType(), "image/jpeg") || Objects.equals(file.getContentType(), "image/png"))) {

                    return new ResponseEntity<>("Invalid file type - " + file.getContentType(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);

                } else {
                    try {
                        byte[] image = file.getBytes();
                        client.setImage(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
                    }
                }
            }


            try {
                clientRepository.save(client);
                return new ResponseEntity<>("Client Updated", HttpStatus.OK);
            }
            catch (Exception e){
                return new ResponseEntity<>(e.getLocalizedMessage() + "\nProject already assign to another client", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    /****************************************  GET CLIENT BY ID********************************************/

    public ResponseEntity<Client> getClientById(long id){
        if (clientRepository.existsById(id)){
            Client client = clientRepository.getById(id);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /****************************************  SET STATUS *************************************************/

    @Transactional
    public ResponseEntity<String> setStatusOfClient(long id, String status){
        if (clientRepository.existsById(id)){
            if (status.equals("Active") || status.equals("Old") || status.equals("Block")){
                Client client = clientRepository.getById(id);
                client.setStatus(status);

                return new ResponseEntity<>("Status changed" , HttpStatus.OK);
            }
            else return new ResponseEntity<>("Invalid status", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }

    /****************************************  GET ALL CLIENTS  *************************************************/

    public Page<Client> getAllClients(int offset, int pageSize, String field){
        return clientRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
    }


    /****************************************  ADD NOTE  *************************************************/

    public ResponseEntity<String> addNote(long id, Note note){
        if (clientRepository.existsById(id)){
            Client client = clientRepository.getById(id);

            List<Note> noteList = client.getNotes();
            noteList.add(note);

            clientRepository.save(client);
            return new ResponseEntity<>("Note added", HttpStatus.OK);
        }

        return new ResponseEntity<>("Bad request",HttpStatus.BAD_REQUEST);
    }


    /****************************************  DELETE NOTE  *************************************************/

    @Transactional
    public ResponseEntity<String> deleteNoteById(long clientId, long noteId){
        if (clientRepository.existsById(clientId)) {
            if (notesRepository.existsById(noteId)) {
                if (clientRepository.getById(clientId).getNotes().contains(notesRepository.getById(noteId))){

                    clientRepository.getById(clientId)
                            .getNotes()
                            .remove(notesRepository.getById(noteId));

                    return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
                }

                else return new  ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>("Note not found", HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
    }



    /****************************************  EDIT NOTE  *************************************************/

    @Transactional
    public ResponseEntity<String> updateNoteById(long id, NoteRequest noteRequest){
        if (notesRepository.existsById(id)){
            Note note = notesRepository.getById(id);
            note.setNote(noteRequest.getNote());
            return new ResponseEntity<>("Note updated", HttpStatus.OK);
        }

        return new ResponseEntity<>("Invalid Id", HttpStatus.BAD_REQUEST);
    }


    /****************************************  CLIENT FULL VIEW  *****************************************/


    public ResponseEntity<ClientFullView> clientFullViewResponseEntity(long id){
        if (clientRepository.existsById(id)){

            ClientFullView clientResponse = new ClientFullView();
            Client client = clientRepository.getById(id);

            clientResponse.setFirstName(client.getFirstName());
            clientResponse.setLastName(client.getLastName());
            clientResponse.setEmail1(client.getEmail1());
            clientResponse.setEmail2(client.getEmail2());
            clientResponse.setPhone1(client.getPhone1());
            clientResponse.setPhone2(client.getPhone2());
            clientResponse.setAddress(client.getAddress());
            clientResponse.setCity(client.getCity());
            clientResponse.setCountry(client.getCountry());
            clientResponse.setState(client.getState());
            clientResponse.setGender(client.getGender());
            clientResponse.setLandline(client.getLandline());
            clientResponse.setRemark(client.getRemark());
            clientResponse.setRegDate(client.getRegDate());
            clientResponse.setDob(client.getDob());
            clientResponse.setStatus(client.getStatus());
            clientResponse.setProjects(client.getProject());
            clientResponse.setNotes(client.getNotes());

            return new ResponseEntity<>(clientResponse, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}

