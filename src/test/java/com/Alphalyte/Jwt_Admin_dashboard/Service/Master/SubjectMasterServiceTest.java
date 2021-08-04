package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.SubjectMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.SubjectMasterRepo;
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

@ContextConfiguration(classes = {SubjectMasterService.class})
@ExtendWith(SpringExtension.class)
public class SubjectMasterServiceTest {
    @MockBean
    private SubjectMasterRepo subjectMasterRepo;

    @Autowired
    private SubjectMasterService subjectMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllSubjects() {
        ArrayList<SubjectMaster> subjectMasterList = new ArrayList<SubjectMaster>();
        when(this.subjectMasterRepo.findAll()).thenReturn(subjectMasterList);
        List<SubjectMaster> actualGetAllSubjectsResult = this.subjectMasterService.GetAllSubjects();
        assertSame(subjectMasterList, actualGetAllSubjectsResult);
        assertTrue(actualGetAllSubjectsResult.isEmpty());
        verify(this.subjectMasterRepo).findAll();
    }

    @Test
    public void testAddSubject() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        SubjectMaster subjectMaster = new SubjectMaster();
        subjectMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setId(1);
        subjectMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster.setSubjectAbb("Hello from the Dreaming Spires");
        when(this.subjectMasterRepo.save((SubjectMaster) any())).thenReturn(subjectMaster);

        SubjectMaster subjectMaster1 = new SubjectMaster();
        subjectMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setId(1);
        subjectMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster1.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster1.setSubjectAbb("Hello from the Dreaming Spires");
        ResponseEntity<?> actualAddSubjectResult = this.subjectMasterService.AddSubject(subjectMaster1, "janedoe");
        assertEquals("Added Subject: Hello from the Dreaming Spires", actualAddSubjectResult.getBody());
        assertEquals("<200 OK OK,Added Subject: Hello from the Dreaming Spires,[]>", actualAddSubjectResult.toString());
        assertEquals(HttpStatus.OK, actualAddSubjectResult.getStatusCode());
        assertTrue(actualAddSubjectResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.subjectMasterRepo).save((SubjectMaster) any());
        assertEquals("janedoe", subjectMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteSubject() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.subjectMasterRepo).deleteById((Integer) any());
        when(this.subjectMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteSubjectResult = this.subjectMasterService.DeleteSubject(1, "janedoe");
        assertEquals("Subject deleted", actualDeleteSubjectResult.getBody());
        assertEquals("<200 OK OK,Subject deleted,[]>", actualDeleteSubjectResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteSubjectResult.getStatusCode());
        assertTrue(actualDeleteSubjectResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.subjectMasterRepo).deleteById((Integer) any());
        verify(this.subjectMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteSubject2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.subjectMasterRepo).deleteById((Integer) any());
        when(this.subjectMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteSubjectResult = this.subjectMasterService.DeleteSubject(1, "janedoe");
        assertEquals("Invalid id", actualDeleteSubjectResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteSubjectResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteSubjectResult.getStatusCode());
        assertTrue(actualDeleteSubjectResult.getHeaders().isEmpty());
        verify(this.subjectMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateSubject() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        SubjectMaster subjectMaster = new SubjectMaster();
        subjectMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setId(1);
        subjectMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster.setSubjectAbb("Hello from the Dreaming Spires");

        SubjectMaster subjectMaster1 = new SubjectMaster();
        subjectMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setId(1);
        subjectMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster1.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster1.setSubjectAbb("Hello from the Dreaming Spires");
        when(this.subjectMasterRepo.save((SubjectMaster) any())).thenReturn(subjectMaster1);
        when(this.subjectMasterRepo.getById((Integer) any())).thenReturn(subjectMaster);
        when(this.subjectMasterRepo.existsById((Integer) any())).thenReturn(true);

        SubjectMaster subjectMaster2 = new SubjectMaster();
        subjectMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster2.setId(1);
        subjectMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster2.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster2.setSubjectAbb("Hello from the Dreaming Spires");
        ResponseEntity<?> actualUpdateSubjectResult = this.subjectMasterService.UpdateSubject(subjectMaster2, "janedoe");
        assertEquals("Subject Updated", actualUpdateSubjectResult.getBody());
        assertEquals("<200 OK OK,Subject Updated,[]>", actualUpdateSubjectResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateSubjectResult.getStatusCode());
        assertTrue(actualUpdateSubjectResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.subjectMasterRepo).existsById((Integer) any());
        verify(this.subjectMasterRepo).getById((Integer) any());
        verify(this.subjectMasterRepo).save((SubjectMaster) any());
    }

    @Test
    public void testUpdateSubject2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        SubjectMaster subjectMaster = new SubjectMaster();
        subjectMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster.setId(1);
        subjectMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster.setSubjectAbb("Hello from the Dreaming Spires");

        SubjectMaster subjectMaster1 = new SubjectMaster();
        subjectMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster1.setId(1);
        subjectMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster1.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster1.setSubjectAbb("Hello from the Dreaming Spires");
        when(this.subjectMasterRepo.save((SubjectMaster) any())).thenReturn(subjectMaster1);
        when(this.subjectMasterRepo.getById((Integer) any())).thenReturn(subjectMaster);
        when(this.subjectMasterRepo.existsById((Integer) any())).thenReturn(false);

        SubjectMaster subjectMaster2 = new SubjectMaster();
        subjectMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        subjectMaster2.setId(1);
        subjectMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        subjectMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        subjectMaster2.setSubjectName("Hello from the Dreaming Spires");
        subjectMaster2.setSubjectAbb("Hello from the Dreaming Spires");
        ResponseEntity<?> actualUpdateSubjectResult = this.subjectMasterService.UpdateSubject(subjectMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateSubjectResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateSubjectResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateSubjectResult.getStatusCode());
        assertTrue(actualUpdateSubjectResult.getHeaders().isEmpty());
        verify(this.subjectMasterRepo).existsById((Integer) any());
    }
}

