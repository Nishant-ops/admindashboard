package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.Service.User.UserGroupMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.User.UserMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ChangePass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.ResetPass;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Response.UserMasterTable;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AdminController.class})
@ExtendWith(SpringExtension.class)
public class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @MockBean
    private UserGroupMasterRepo userGroupMasterRepo;

    @MockBean
    private UserGroupMasterService userGroupMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserMasterService userMasterService;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testRenderImageFromDb() throws Exception {
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(true);
        when(this.userMasterService.renderImageFromDb((Integer) any(), (javax.servlet.http.HttpServletResponse) any()))
                .thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{usercode}/photo", 1);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("image/png"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAAimage"));
    }

    @Test
    public void testRenderImageFromDb2() throws Exception {
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(false);
        when(this.userMasterService.renderImageFromDb((Integer) any(), (javax.servlet.http.HttpServletResponse) any()))
                .thenReturn("AAAAAAAA".getBytes("UTF-8"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{usercode}/photo", 1);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Usercode not found"));
    }

    @Test
    public void testRenderImageFromDb3() throws Exception {
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(true);
        when(this.userMasterService.renderImageFromDb((Integer) any(), (javax.servlet.http.HttpServletResponse) any()))
                .thenReturn(new byte[]{});
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{usercode}/photo", 1);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Usercode not found"));
    }

    @Test
    public void testAddUser() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/users");
        MockHttpServletRequestBuilder paramResult = postResult.param("file", String.valueOf((Object) null));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userResquest",
                String.valueOf(new UserResquest()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(415));
    }

    @Test
    public void testChangePass() throws Exception {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        user user = new user();
        user.setImage("AAAAAAAA".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setGroup_name(userGroupMaster);
        user.setEmail("jane.doe@example.org");
        user.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setLocaladdress("42 Main St");
        user.setPermanentAddress("42 Main St");
        user.setDateOfJoining(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsercode(1);
        user.setLanguage("Language");
        user.setUsername("janedoe");
        user.setUserService(this.userMasterService);
        user.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        user.setModifiedBY("Jan 1, 2020 9:00am GMT+0100");
        user.setPhoneNumber(1L);
        user.setBranch("janedoe/featurebranch");
        when(this.userReposoritries.getById((Integer) any())).thenReturn(user);

        ChangePass changePass = new ChangePass();
        changePass.setUsercode(1);
        changePass.setNewpass("Newpass");
        changePass.setOldpass("Oldpass");
        String content = (new ObjectMapper()).writeValueAsString(changePass);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/changepass")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Old Password not matched"));
    }

    @Test
    public void testDeleteGroup() throws Exception {
        when(this.userGroupMasterService.DeleteGroupByName(anyString(), anyString()))
                .thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/usergroup/{groupname}", "Groupname")
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteUser() throws Exception {
        when(this.userMasterService.deleteById(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.delete("/users/{usercode}", 1)
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new String()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testGetById() throws Exception {
        when(this.userGroupMasterService.GetById(anyInt())).thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usergroup/{gid}", 1);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testGetGroupCodeByName() throws Exception {
        when(this.userGroupMasterService.GetGroupCodeByName(anyString()))
                .thenReturn(new ResponseEntity(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groupcode/{groupname}", "Groupname");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testGetGroupNames() throws Exception {
        when(this.userGroupMasterService.GetGroupNames()).thenReturn(new ArrayList<String>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usergroup/names");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetGroupNames2() throws Exception {
        when(this.userGroupMasterService.GetGroupNames()).thenReturn(new ArrayList<String>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/usergroup/names");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetLogReport() throws Exception {
        when(this.userLogReportRepo.findAll()).thenReturn(new ArrayList<UserLogReport>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logreport");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetLogReport2() throws Exception {
        when(this.userLogReportRepo.findAll()).thenReturn(new ArrayList<UserLogReport>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/logreport");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetallgroups() throws Exception {
        when(this.userGroupMasterService.GetAllGroups()).thenReturn(new ArrayList<UserGroupMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usergroup");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetallgroups2() throws Exception {
        when(this.userGroupMasterService.GetAllGroups()).thenReturn(new ArrayList<UserGroupMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/usergroup");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testResetPass() throws Exception {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        user user = new user();
        user.setImage("AAAAAAAA".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setGroup_name(userGroupMaster);
        user.setEmail("jane.doe@example.org");
        user.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setLocaladdress("42 Main St");
        user.setPermanentAddress("42 Main St");
        user.setDateOfJoining(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsercode(1);
        user.setLanguage("Language");
        user.setUsername("janedoe");
        user.setUserService(this.userMasterService);
        user.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        user.setModifiedBY("Jan 1, 2020 9:00am GMT+0100");
        user.setPhoneNumber(1L);
        user.setBranch("janedoe/featurebranch");

        UserGroupMaster userGroupMaster1 = new UserGroupMaster();
        userGroupMaster1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setGid(1);
        userGroupMaster1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setActive(true);
        userGroupMaster1.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster1.setGroupname("Groupname");
        userGroupMaster1.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        user user1 = new user();
        user1.setImage("AAAAAAAA".getBytes("UTF-8"));
        user1.setPassword("iloveyou");
        user1.setGroup_name(userGroupMaster1);
        user1.setEmail("jane.doe@example.org");
        user1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setLocaladdress("42 Main St");
        user1.setPermanentAddress("42 Main St");
        user1.setDateOfJoining(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setUsercode(1);
        user1.setLanguage("Language");
        user1.setUsername("janedoe");
        user1.setUserService(this.userMasterService);
        user1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user1.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        user1.setModifiedBY("Jan 1, 2020 9:00am GMT+0100");
        user1.setPhoneNumber(1L);
        user1.setBranch("janedoe/featurebranch");
        when(this.userReposoritries.save((user) any())).thenReturn(user1);
        when(this.userReposoritries.getById((Integer) any())).thenReturn(user);
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(true);

        ResetPass resetPass = new ResetPass();
        resetPass.setUsercode(1);
        resetPass.setNewpass("Newpass");
        String content = (new ObjectMapper()).writeValueAsString(resetPass);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/resetpass")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("PASSWORD RESET SUCCESSFULL!"));
    }

    @Test
    public void testUpdateUser() throws Exception {
        MockHttpServletRequestBuilder putResult = MockMvcRequestBuilders.put("/users");
        MockHttpServletRequestBuilder paramResult = putResult.param("file", String.valueOf((Object) null));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("userRequest",
                String.valueOf(new UserUpdateRequest()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void testUsers() throws Exception {
        when(this.userMasterService.GetAllUsers()).thenReturn(new ArrayList<UserMasterTable>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testUsers2() throws Exception {
        when(this.userMasterService.GetAllUsers()).thenReturn(new ArrayList<UserMasterTable>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.adminController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

