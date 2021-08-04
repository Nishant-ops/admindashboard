package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ExamMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.ExamMasterRepo;
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

@ContextConfiguration(classes = {ExamMasterService.class})
@ExtendWith(SpringExtension.class)
public class ExamMasterServiceTest {
    @MockBean
    private ExamMasterRepo examMasterRepo;

    @Autowired
    private ExamMasterService examMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllExams() {
        ArrayList<ExamMaster> examMasterList = new ArrayList<ExamMaster>();
        when(this.examMasterRepo.findAll()).thenReturn(examMasterList);
        List<ExamMaster> actualGetAllExamsResult = this.examMasterService.GetAllExams();
        assertSame(examMasterList, actualGetAllExamsResult);
        assertTrue(actualGetAllExamsResult.isEmpty());
        verify(this.examMasterRepo).findAll();
    }

    @Test
    public void testAddExam() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ExamMaster examMaster = new ExamMaster();
        examMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setExamName("Exam Name");
        examMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setId(1);
        examMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster.setExamAbb("Exam Abb");
        when(this.examMasterRepo.save((ExamMaster) any())).thenReturn(examMaster);

        ExamMaster examMaster1 = new ExamMaster();
        examMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setExamName("Exam Name");
        examMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setId(1);
        examMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster1.setExamAbb("Exam Abb");
        ResponseEntity<?> actualAddExamResult = this.examMasterService.AddExam(examMaster1, "janedoe");
        assertEquals("Added exam: Exam Name", actualAddExamResult.getBody());
        assertEquals("<200 OK OK,Added exam: Exam Name,[]>", actualAddExamResult.toString());
        assertEquals(HttpStatus.OK, actualAddExamResult.getStatusCode());
        assertTrue(actualAddExamResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.examMasterRepo).save((ExamMaster) any());
        assertEquals("janedoe", examMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteExam() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.examMasterRepo).deleteById((Integer) any());
        when(this.examMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteExamResult = this.examMasterService.DeleteExam(1, "janedoe");
        assertEquals("Exam deleted", actualDeleteExamResult.getBody());
        assertEquals("<200 OK OK,Exam deleted,[]>", actualDeleteExamResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteExamResult.getStatusCode());
        assertTrue(actualDeleteExamResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.examMasterRepo).deleteById((Integer) any());
        verify(this.examMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteExam2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.examMasterRepo).deleteById((Integer) any());
        when(this.examMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteExamResult = this.examMasterService.DeleteExam(1, "janedoe");
        assertEquals("Invalid id", actualDeleteExamResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteExamResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteExamResult.getStatusCode());
        assertTrue(actualDeleteExamResult.getHeaders().isEmpty());
        verify(this.examMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateExam() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ExamMaster examMaster = new ExamMaster();
        examMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setExamName("Exam Name");
        examMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setId(1);
        examMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster.setExamAbb("Exam Abb");

        ExamMaster examMaster1 = new ExamMaster();
        examMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setExamName("Exam Name");
        examMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setId(1);
        examMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster1.setExamAbb("Exam Abb");
        when(this.examMasterRepo.save((ExamMaster) any())).thenReturn(examMaster1);
        when(this.examMasterRepo.getById((Integer) any())).thenReturn(examMaster);
        when(this.examMasterRepo.existsById((Integer) any())).thenReturn(true);

        ExamMaster examMaster2 = new ExamMaster();
        examMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster2.setExamName("Exam Name");
        examMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster2.setId(1);
        examMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster2.setExamAbb("Exam Abb");
        ResponseEntity<?> actualUpdateExamResult = this.examMasterService.UpdateExam(examMaster2, "janedoe");
        assertEquals("Exam Updated", actualUpdateExamResult.getBody());
        assertEquals("<200 OK OK,Exam Updated,[]>", actualUpdateExamResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateExamResult.getStatusCode());
        assertTrue(actualUpdateExamResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.examMasterRepo).existsById((Integer) any());
        verify(this.examMasterRepo).getById((Integer) any());
        verify(this.examMasterRepo).save((ExamMaster) any());
    }

    @Test
    public void testUpdateExam2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        ExamMaster examMaster = new ExamMaster();
        examMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setExamName("Exam Name");
        examMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster.setId(1);
        examMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster.setExamAbb("Exam Abb");

        ExamMaster examMaster1 = new ExamMaster();
        examMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setExamName("Exam Name");
        examMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster1.setId(1);
        examMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster1.setExamAbb("Exam Abb");
        when(this.examMasterRepo.save((ExamMaster) any())).thenReturn(examMaster1);
        when(this.examMasterRepo.getById((Integer) any())).thenReturn(examMaster);
        when(this.examMasterRepo.existsById((Integer) any())).thenReturn(false);

        ExamMaster examMaster2 = new ExamMaster();
        examMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster2.setExamName("Exam Name");
        examMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        examMaster2.setId(1);
        examMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        examMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        examMaster2.setExamAbb("Exam Abb");
        ResponseEntity<?> actualUpdateExamResult = this.examMasterService.UpdateExam(examMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateExamResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateExamResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateExamResult.getStatusCode());
        assertTrue(actualUpdateExamResult.getHeaders().isEmpty());
        verify(this.examMasterRepo).existsById((Integer) any());
    }
}

