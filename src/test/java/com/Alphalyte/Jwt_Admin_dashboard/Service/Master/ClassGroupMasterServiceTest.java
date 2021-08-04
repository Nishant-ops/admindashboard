package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ClassGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.ClassGroupMasterRepo;
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

@ContextConfiguration(classes = {ClassGroupMasterService.class})
@ExtendWith(SpringExtension.class)
public class ClassGroupMasterServiceTest {
    @MockBean
    private ClassGroupMasterRepo classGroupMasterRepo;

    @Autowired
    private ClassGroupMasterService classGroupMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllClassGroups() {
        ArrayList<ClassGroupMaster> classGroupMasterList = new ArrayList<ClassGroupMaster>();
        when(this.classGroupMasterRepo.findAll()).thenReturn(classGroupMasterList);
        List<ClassGroupMaster> actualGetAllClassGroupsResult = this.classGroupMasterService.GetAllClassGroups();
        assertSame(classGroupMasterList, actualGetAllClassGroupsResult);
        assertTrue(actualGetAllClassGroupsResult.isEmpty());
        verify(this.classGroupMasterRepo).findAll();
    }

    @Test
    public void testAddClassGroup() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Insert");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ClassGroupMaster classGroupMaster = new ClassGroupMaster();
        classGroupMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setId(1);
        classGroupMaster.setClassGroup("Class Group");
        classGroupMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.classGroupMasterRepo.save((ClassGroupMaster) any())).thenReturn(classGroupMaster);

        ClassGroupMaster classGroupMaster1 = new ClassGroupMaster();
        classGroupMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setId(1);
        classGroupMaster1.setClassGroup("Class Group");
        classGroupMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualAddClassGroupResult = this.classGroupMasterService.AddClassGroup(classGroupMaster1,
                "janedoe");
        assertEquals("Added class group: Class Group", actualAddClassGroupResult.getBody());
        assertEquals("<200 OK OK,Added class group: Class Group,[]>", actualAddClassGroupResult.toString());
        assertEquals(HttpStatus.OK, actualAddClassGroupResult.getStatusCode());
        assertTrue(actualAddClassGroupResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.classGroupMasterRepo).save((ClassGroupMaster) any());
        assertEquals("janedoe", classGroupMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteClassGroup() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.classGroupMasterRepo).deleteById((Integer) any());
        when(this.classGroupMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteClassGroupResult = this.classGroupMasterService.DeleteClassGroup(1, "janedoe");
        assertEquals("Class Group deleted", actualDeleteClassGroupResult.getBody());
        assertEquals("<200 OK OK,Class Group deleted,[]>", actualDeleteClassGroupResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteClassGroupResult.getStatusCode());
        assertTrue(actualDeleteClassGroupResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.classGroupMasterRepo).deleteById((Integer) any());
        verify(this.classGroupMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteClassGroup2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.classGroupMasterRepo).deleteById((Integer) any());
        when(this.classGroupMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteClassGroupResult = this.classGroupMasterService.DeleteClassGroup(1, "janedoe");
        assertEquals("Invalid id", actualDeleteClassGroupResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteClassGroupResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteClassGroupResult.getStatusCode());
        assertTrue(actualDeleteClassGroupResult.getHeaders().isEmpty());
        verify(this.classGroupMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateClassGroup() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ClassGroupMaster classGroupMaster = new ClassGroupMaster();
        classGroupMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setId(1);
        classGroupMaster.setClassGroup("Class Group");
        classGroupMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        ClassGroupMaster classGroupMaster1 = new ClassGroupMaster();
        classGroupMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setId(1);
        classGroupMaster1.setClassGroup("Class Group");
        classGroupMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.classGroupMasterRepo.save((ClassGroupMaster) any())).thenReturn(classGroupMaster1);
        when(this.classGroupMasterRepo.getById((Integer) any())).thenReturn(classGroupMaster);
        when(this.classGroupMasterRepo.existsById((Integer) any())).thenReturn(true);

        ClassGroupMaster classGroupMaster2 = new ClassGroupMaster();
        classGroupMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster2.setId(1);
        classGroupMaster2.setClassGroup("Class Group");
        classGroupMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualUpdateClassGroupResult = this.classGroupMasterService.UpdateClassGroup(classGroupMaster2,
                "janedoe");
        assertEquals("Class Group Updated", actualUpdateClassGroupResult.getBody());
        assertEquals("<200 OK OK,Class Group Updated,[]>", actualUpdateClassGroupResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateClassGroupResult.getStatusCode());
        assertTrue(actualUpdateClassGroupResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.classGroupMasterRepo).existsById((Integer) any());
        verify(this.classGroupMasterRepo).getById((Integer) any());
        verify(this.classGroupMasterRepo).save((ClassGroupMaster) any());
    }

    @Test
    public void testUpdateClassGroup2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ClassGroupMaster classGroupMaster = new ClassGroupMaster();
        classGroupMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster.setId(1);
        classGroupMaster.setClassGroup("Class Group");
        classGroupMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        ClassGroupMaster classGroupMaster1 = new ClassGroupMaster();
        classGroupMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster1.setId(1);
        classGroupMaster1.setClassGroup("Class Group");
        classGroupMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.classGroupMasterRepo.save((ClassGroupMaster) any())).thenReturn(classGroupMaster1);
        when(this.classGroupMasterRepo.getById((Integer) any())).thenReturn(classGroupMaster);
        when(this.classGroupMasterRepo.existsById((Integer) any())).thenReturn(false);

        ClassGroupMaster classGroupMaster2 = new ClassGroupMaster();
        classGroupMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        classGroupMaster2.setId(1);
        classGroupMaster2.setClassGroup("Class Group");
        classGroupMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        classGroupMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualUpdateClassGroupResult = this.classGroupMasterService.UpdateClassGroup(classGroupMaster2,
                "janedoe");
        assertEquals("Failed to update", actualUpdateClassGroupResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateClassGroupResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateClassGroupResult.getStatusCode());
        assertTrue(actualUpdateClassGroupResult.getHeaders().isEmpty());
        verify(this.classGroupMasterRepo).existsById((Integer) any());
    }
}

