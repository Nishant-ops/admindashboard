package com.Alphalyte.Jwt_Admin_dashboard.Controller;


import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Client;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Note;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.CRM.ClientRepository;
import com.Alphalyte.Jwt_Admin_dashboard.Service.CRM.ClientService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.CRM.ProjectService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ClientRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ProjectRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.ClientFullView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CRMController {

    @Autowired
    ClientService clientService;

    @Autowired
    ProjectService projectService;

    @Autowired
    ClientRepository clientRepository;

    /****************************************  ADD CLIENT  ***********************************************/

    @PostMapping(value = "/client" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addClient(@RequestPart(value = "client") ClientRequest request,
                                            @RequestPart(value = "file",required = false) MultipartFile file){
        return clientService.addClient(request, file);
    }


    /****************************************  UPDATE CLIENT  ***********************************************/

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<String> updateClient(@PathVariable("id") long id,@RequestPart(value = "client") ClientRequest clientRequest,
                                               @RequestPart(value = "file",required = false) MultipartFile file){
        return clientService.updateClient(id, clientRequest, file);
    }


    /****************************************  GET CLIENT BY ID  ******************************************/

    @GetMapping(value = "/clientById/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") long id){
        return clientService.getClientById(id);
    }


    /****************************************  DELETE CLIENT BY ID  ******************************************/

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable long id){
        return clientService.deleteClient(id);
    }


    /****************************************  CLIENT FULL VIEW  ******************************************/

    @GetMapping(value = "/clientFullView/{id}")
    public ResponseEntity<ClientFullView> clientFullViewResponseEntity(@PathVariable long id){
        return clientService.clientFullViewResponseEntity(id);
    }


    /****************************************  CHANGE CLIENT STATUS  *************************************/

    @PutMapping(value = "/clientStatus/{id}")
    public ResponseEntity<String> changeClientStatus(@PathVariable("id") long id, @RequestParam("status") String status){
        return clientService.setStatusOfClient(id, status);
    }


    /****************************************  ADD NOTE TO CLIENT  ***************************************/

    @PostMapping(value = "/client/{id}/note")
    public ResponseEntity<String> addNoteToClient(@PathVariable("id") long id, @RequestBody Note note){
        return clientService.addNote(id, note);
    }

    /****************************************  DELETE NOTE FROM CLIENT  ***************************************/

    @DeleteMapping(value = "/client/{clientId}/{noteId}")
    public ResponseEntity<String> deleteNoteFromClient(@PathVariable("clientId") long clientId,@PathVariable("noteId") long noteId){
        return clientService.deleteNoteById(clientId, noteId);
    }



    /****************************************  PAGINATION AND SORTING  *************************************/

    @GetMapping(value = "/client/{offset}/{size}/{field}" )
    public Page<Client> getAllClients( @PathVariable("offset") int offset, @PathVariable("size") int pageSize, @PathVariable("field") String field){
        return clientService.getAllClientsWithFilter(offset, pageSize, field);
    }

    /****************************************  PAGINATION AND SORTING  *************************************/

    @GetMapping(value = "/client/{offset}/{size}" )
    public Page<Client> getAllClients( @PathVariable("offset") int offset, @PathVariable("size") int pageSize){
        return clientService.getAllClientsWithoutFilter(offset, pageSize);
    }



    /****************************************  ADD PROJECT  *************************************************/
    @PostMapping(value = "/project")
    public ResponseEntity<String> addProject(@RequestBody ProjectRequest projectRequest){
        return projectService.addProject(projectRequest);
    }

    /****************************************  DELETE PROJECT  *************************************************/

    @DeleteMapping(value = "/project/{id}")
    public ResponseEntity<String> deleteProjectById(@PathVariable("id") long id){
        return projectService.deleteProjectById(id);
    }




}
