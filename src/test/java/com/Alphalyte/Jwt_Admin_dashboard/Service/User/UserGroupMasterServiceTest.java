package com.Alphalyte.Jwt_Admin_dashboard.Service.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserGroupMasterService.class})
@ExtendWith(SpringExtension.class)
public class UserGroupMasterServiceTest {
    @MockBean
    private UserGroupMasterRepo userGroupMasterRepo;

    @Autowired
    private UserGroupMasterService userGroupMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllGroups() {
        ArrayList<UserGroupMaster> userGroupMasterList = new ArrayList<UserGroupMaster>();
        when(this.userGroupMasterRepo.findAll()).thenReturn(userGroupMasterList);
        List<UserGroupMaster> actualGetAllGroupsResult = this.userGroupMasterService.GetAllGroups();
        assertSame(userGroupMasterList, actualGetAllGroupsResult);
        assertTrue(actualGetAllGroupsResult.isEmpty());
        verify(this.userGroupMasterRepo).findAll();
    }

    @Test
    public void testGetById() {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.getById((Integer) any())).thenReturn(userGroupMaster);
        when(this.userGroupMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity actualGetByIdResult = this.userGroupMasterService.GetById(1);
        assertEquals("Invalid group id", actualGetByIdResult.getBody());
        assertEquals("<200 OK OK,Invalid group id,[]>", actualGetByIdResult.toString());
        assertEquals(HttpStatus.OK, actualGetByIdResult.getStatusCode());
        assertTrue(actualGetByIdResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).existsById((Integer) any());
        verify(this.userGroupMasterRepo).getById((Integer) any());
    }

    @Test
    public void testGetById2() {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.getById((Integer) any())).thenReturn(userGroupMaster);
        when(this.userGroupMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity actualGetByIdResult = this.userGroupMasterService.GetById(1);
        assertEquals("Invalid group id", actualGetByIdResult.getBody());
        assertEquals("<200 OK OK,Invalid group id,[]>", actualGetByIdResult.toString());
        assertEquals(HttpStatus.OK, actualGetByIdResult.getStatusCode());
        assertTrue(actualGetByIdResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testGetGroupNames() {
        ArrayList<String> stringList = new ArrayList<String>();
        when(this.userGroupMasterRepo.groupnames()).thenReturn(stringList);
        List<String> actualGetGroupNamesResult = this.userGroupMasterService.GetGroupNames();
        assertSame(stringList, actualGetGroupNamesResult);
        assertTrue(actualGetGroupNamesResult.isEmpty());
        verify(this.userGroupMasterRepo).groupnames();
    }

    @Test
    public void testGetGroupCodeByName() {
        when(this.userGroupMasterRepo.call(anyString())).thenReturn(1);
        ResponseEntity actualGetGroupCodeByNameResult = this.userGroupMasterService.GetGroupCodeByName("Group Name");
        assertEquals("<200 OK OK,1,[]>", actualGetGroupCodeByNameResult.toString());
        assertTrue(actualGetGroupCodeByNameResult.hasBody());
        assertEquals(HttpStatus.OK, actualGetGroupCodeByNameResult.getStatusCode());
        assertTrue(actualGetGroupCodeByNameResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).call(anyString());
    }

    @Test
    public void testGetGroupCodeByName2() {
        when(this.userGroupMasterRepo.call(anyString())).thenReturn(null);
        ResponseEntity actualGetGroupCodeByNameResult = this.userGroupMasterService.GetGroupCodeByName("Group Name");
        assertEquals("Invalid group name", actualGetGroupCodeByNameResult.getBody());
        assertEquals("<200 OK OK,Invalid group name,[]>", actualGetGroupCodeByNameResult.toString());
        assertEquals(HttpStatus.OK, actualGetGroupCodeByNameResult.getStatusCode());
        assertTrue(actualGetGroupCodeByNameResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).call(anyString());
    }

    @Test
    public void testAddGroup() {
        when(this.userGroupMasterRepo.call(anyString())).thenReturn(1);

        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        ResponseEntity actualAddGroupResult = this.userGroupMasterService.AddGroup(userGroupMaster);
        assertEquals("Groupname already exists", actualAddGroupResult.getBody());
        assertEquals("<200 OK OK,Groupname already exists,[]>", actualAddGroupResult.toString());
        assertEquals(HttpStatus.OK, actualAddGroupResult.getStatusCode());
        assertTrue(actualAddGroupResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).call(anyString());
    }

    @Test
    public void testAddGroup2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.save((UserGroupMaster) any())).thenReturn(userGroupMaster);
        when(this.userGroupMasterRepo.call(anyString())).thenReturn(null);

        UserGroupMaster userGroupMaster1 = new UserGroupMaster();
        userGroupMaster1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setGid(1);
        userGroupMaster1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setActive(true);
        userGroupMaster1.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster1.setGroupname("Groupname");
        userGroupMaster1.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        ResponseEntity actualAddGroupResult = this.userGroupMasterService.AddGroup(userGroupMaster1);
        assertEquals("group added", actualAddGroupResult.getBody());
        assertEquals("<200 OK OK,group added,[]>", actualAddGroupResult.toString());
        assertEquals(HttpStatus.OK, actualAddGroupResult.getStatusCode());
        assertTrue(actualAddGroupResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.userGroupMasterRepo).call(anyString());
        verify(this.userGroupMasterRepo).save((UserGroupMaster) any());
    }

    @Test
    public void testUpdateGroup() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        UserGroupMaster userGroupMaster1 = new UserGroupMaster();
        userGroupMaster1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setGid(1);
        userGroupMaster1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setActive(true);
        userGroupMaster1.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster1.setGroupname("Groupname");
        userGroupMaster1.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.save((UserGroupMaster) any())).thenReturn(userGroupMaster1);
        when(this.userGroupMasterRepo.getById((Integer) any())).thenReturn(userGroupMaster);
        when(this.userGroupMasterRepo.existsById((Integer) any())).thenReturn(true);

        UserGroupMaster userGroupMaster2 = new UserGroupMaster();
        userGroupMaster2.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster2.setGid(1);
        userGroupMaster2.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster2.setActive(true);
        userGroupMaster2.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster2.setGroupname("Groupname");
        userGroupMaster2.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        ResponseEntity actualUpdateGroupResult = this.userGroupMasterService.UpdateGroup(userGroupMaster2);
        assertTrue(actualUpdateGroupResult.hasBody());
        assertTrue(actualUpdateGroupResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateGroupResult.getStatusCode());
        assertEquals("Groupname", ((UserGroupMaster) actualUpdateGroupResult.getBody()).getGroupname());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", ((UserGroupMaster) actualUpdateGroupResult.getBody()).getModifiedby());
        assertTrue(((UserGroupMaster) actualUpdateGroupResult.getBody()).isActive());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.userGroupMasterRepo).existsById((Integer) any());
        verify(this.userGroupMasterRepo).getById((Integer) any());
        verify(this.userGroupMasterRepo).save((UserGroupMaster) any());
    }

    @Test
    public void testUpdateGroup2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        UserGroupMaster userGroupMaster1 = new UserGroupMaster();
        userGroupMaster1.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setGid(1);
        userGroupMaster1.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster1.setActive(true);
        userGroupMaster1.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster1.setGroupname("Groupname");
        userGroupMaster1.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        when(this.userGroupMasterRepo.save((UserGroupMaster) any())).thenReturn(userGroupMaster1);
        when(this.userGroupMasterRepo.getById((Integer) any())).thenReturn(userGroupMaster);
        when(this.userGroupMasterRepo.existsById((Integer) any())).thenReturn(false);

        UserGroupMaster userGroupMaster2 = new UserGroupMaster();
        userGroupMaster2.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster2.setGid(1);
        userGroupMaster2.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster2.setActive(true);
        userGroupMaster2.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster2.setGroupname("Groupname");
        userGroupMaster2.setCreatedby("Jan 1, 2020 8:00am GMT+0100");
        ResponseEntity actualUpdateGroupResult = this.userGroupMasterService.UpdateGroup(userGroupMaster2);
        assertEquals("invalid group code", actualUpdateGroupResult.getBody());
        assertEquals("<200 OK OK,invalid group code,[]>", actualUpdateGroupResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateGroupResult.getStatusCode());
        assertTrue(actualUpdateGroupResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteGroupByName() {
        when(this.userGroupMasterRepo.groupnames()).thenReturn(new ArrayList<String>());
        ResponseEntity actualDeleteGroupByNameResult = this.userGroupMasterService.DeleteGroupByName("Groupname",
                "janedoe");
        assertEquals("Group not found", actualDeleteGroupByNameResult.getBody());
        assertEquals("<200 OK OK,Group not found,[]>", actualDeleteGroupByNameResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteGroupByNameResult.getStatusCode());
        assertTrue(actualDeleteGroupByNameResult.getHeaders().isEmpty());
        verify(this.userGroupMasterRepo).groupnames();
    }

    @Test
    public void testDeleteGroupByName2() {
        when(this.userGroupMasterRepo.groupnames()).thenReturn(new ArrayList<String>());
        ResponseEntity actualDeleteGroupByNameResult = this.userGroupMasterService.DeleteGroupByName("disable", "janedoe");
        assertEquals("Cannot delete default group", actualDeleteGroupByNameResult.getBody());
        assertEquals("<200 OK OK,Cannot delete default group,[]>", actualDeleteGroupByNameResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteGroupByNameResult.getStatusCode());
        assertTrue(actualDeleteGroupByNameResult.getHeaders().isEmpty());
    }
}

