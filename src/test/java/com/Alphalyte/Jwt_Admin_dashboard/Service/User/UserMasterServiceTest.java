package com.Alphalyte.Jwt_Admin_dashboard.Service.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserResquest;
import com.Alphalyte.Jwt_Admin_dashboard.payload.Request.UserUpdateRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserMasterService.class})
@ExtendWith(SpringExtension.class)
public class UserMasterServiceTest {
    @MockBean
    private UserGroupMasterRepo userGroupMasterRepo;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @Autowired
    private UserMasterService userMasterService;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllUsers() {
        when(this.userReposoritries.findAll()).thenReturn(new ArrayList<user>());
        assertTrue(this.userMasterService.GetAllUsers().isEmpty());
        verify(this.userReposoritries).findAll();
    }

    @Test
    public void testGetAllUsers2() throws UnsupportedEncodingException {
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
        user.setUsercode(0);
        user.setLanguage("Language");
        user.setUsername("janedoe");
        user.setUserService(this.userMasterService);
        user.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        user.setModifiedBY("Jan 1, 2020 9:00am GMT+0100");
        user.setPhoneNumber(0L);
        user.setBranch("janedoe/featurebranch");

        ArrayList<user> userList = new ArrayList<user>();
        userList.add(user);
        when(this.userReposoritries.findAll()).thenReturn(userList);
        assertEquals(1, this.userMasterService.GetAllUsers().size());
        verify(this.userReposoritries).findAll();
    }

    @Test
    public void testUpdateUser() throws IOException {
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
        Optional<user> ofResult = Optional.<user>of(user);
        when(this.userReposoritries.findById((Integer) any())).thenReturn(ofResult);

        UserGroupMaster userGroupMaster1 = new UserGroupMaster();
        userGroupMaster1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setGid(1);
        userGroupMaster1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setActive(true);
        userGroupMaster1.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster1.setGroupname("Groupname");
        userGroupMaster1.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.getById((Integer) any())).thenReturn(userGroupMaster1);
        when(this.userGroupMasterRepo.existsById((Integer) any())).thenReturn(true);
        when(this.userGroupMasterRepo.call(anyString())).thenReturn(1);

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setEmail("jane.doe@example.org");
        userUpdateRequest.setUsercode(1);
        userUpdateRequest.setLanguage("Language");
        userUpdateRequest.setLocaladdress("42 Main St");
        userUpdateRequest.setPermanentAddress("42 Main St");
        userUpdateRequest.setUsername("janedoe");
        userUpdateRequest.setDateOfJoining(LocalDateTime.of(1, 1, 1, 1, 1));
        userUpdateRequest.setPhone(1L);
        userUpdateRequest.setBranch("janedoe/featurebranch");
        userUpdateRequest.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userUpdateRequest.setGroupname("Groupname");
        ResponseEntity<?> actualUpdateUserResult = this.userMasterService.UpdateUser(userUpdateRequest,
                new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));
        assertEquals("Invalid phone number", actualUpdateUserResult.getBody());
        assertEquals("<200 OK OK,Invalid phone number,[]>", actualUpdateUserResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateUserResult.getStatusCode());
        assertTrue(actualUpdateUserResult.getHeaders().isEmpty());
        verify(this.userReposoritries).findById((Integer) any());
        verify(this.userGroupMasterRepo).call(anyString());
        verify(this.userGroupMasterRepo).existsById((Integer) any());
        verify(this.userGroupMasterRepo).getById((Integer) any());
    }

    @Test
    public void testDeleteById() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);
        doNothing().when(this.userReposoritries).deleteById((Integer) any());
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(true);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ResponseEntity<?> actualDeleteByIdResult = this.userMasterService.deleteById(1, "janedoe");
        assertEquals("User deleted", actualDeleteByIdResult.getBody());
        assertEquals("<200 OK OK,User deleted,[]>", actualDeleteByIdResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteByIdResult.getStatusCode());
        assertTrue(actualDeleteByIdResult.getHeaders().isEmpty());
        verify(this.userReposoritries).deleteById((Integer) any());
        verify(this.userReposoritries).existsById((Integer) any());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
    }

    @Test
    public void testDeleteById2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);
        doNothing().when(this.userReposoritries).deleteById((Integer) any());
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(false);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        ResponseEntity<?> actualDeleteByIdResult = this.userMasterService.deleteById(1, "janedoe");
        assertEquals("User with usercode 1 not found!", actualDeleteByIdResult.getBody());
        assertEquals("<200 OK OK,User with usercode 1 not found!,[]>", actualDeleteByIdResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteByIdResult.getStatusCode());
        assertTrue(actualDeleteByIdResult.getHeaders().isEmpty());
        verify(this.userReposoritries).existsById((Integer) any());
    }

    @Test
    public void testAddUser() throws IOException {
        UserResquest userRequest = new UserResquest();
        ResponseEntity<?> actualAddUserResult = this.userMasterService.AddUser(userRequest,
                new MockMultipartFile("Name", "AAAAAAAAAAAAAAAAAAAAAAAA".getBytes("UTF-8")));
        assertEquals("Username cannot be null", actualAddUserResult.getBody());
        assertEquals("<200 OK OK,Username cannot be null,[]>", actualAddUserResult.toString());
        assertEquals(HttpStatus.OK, actualAddUserResult.getStatusCode());
        assertTrue(actualAddUserResult.getHeaders().isEmpty());
    }

    @Test
    public void testGetUserById() throws UnsupportedEncodingException {
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
        when(this.userReposoritries.existsById((Integer) any())).thenReturn(true);
        assertSame(user, this.userMasterService.getUserById(1));
        verify(this.userReposoritries).existsById((Integer) any());
        verify(this.userReposoritries).getById((Integer) any());
    }

    @Test
    public void testRenderImageFromDb() throws IOException {
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
        Optional<user> ofResult = Optional.<user>of(user);
        when(this.userReposoritries.findById((Integer) any())).thenReturn(ofResult);
        byte[] actualRenderImageFromDbResult = this.userMasterService.renderImageFromDb(1, new Response());
        assertEquals(8, actualRenderImageFromDbResult.length);
        assertEquals('A', actualRenderImageFromDbResult[0]);
        assertEquals('A', actualRenderImageFromDbResult[1]);
        assertEquals('A', actualRenderImageFromDbResult[2]);
        assertEquals('A', actualRenderImageFromDbResult[3]);
        assertEquals('A', actualRenderImageFromDbResult[4]);
        assertEquals('A', actualRenderImageFromDbResult[5]);
        assertEquals('A', actualRenderImageFromDbResult[6]);
        assertEquals('A', actualRenderImageFromDbResult[7]);
        verify(this.userReposoritries).findById((Integer) any());
    }

    @Test
    public void testRenderImageFromDb2() throws IOException {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        user user = new user();
        user.setImage(new byte[]{});
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
        Optional<user> ofResult = Optional.<user>of(user);
        when(this.userReposoritries.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(0, this.userMasterService.renderImageFromDb(1, new Response()).length);
        verify(this.userReposoritries).findById((Integer) any());
    }
}

