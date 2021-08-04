package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StandardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.StandardMasterRepo;
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

@ContextConfiguration(classes = {StandardMasterService.class})
@ExtendWith(SpringExtension.class)
public class StandardMasterServiceTest {
    @MockBean
    private StandardMasterRepo standardMasterRepo;

    @Autowired
    private StandardMasterService standardMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllStandards() {
        ArrayList<StandardMaster> standardMasterList = new ArrayList<StandardMaster>();
        when(this.standardMasterRepo.findAll()).thenReturn(standardMasterList);
        List<StandardMaster> actualGetAllStandardsResult = this.standardMasterService.GetAllStandards();
        assertSame(standardMasterList, actualGetAllStandardsResult);
        assertTrue(actualGetAllStandardsResult.isEmpty());
        verify(this.standardMasterRepo).findAll();
    }

    @Test
    public void testAddStandard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StandardMaster standardMaster = new StandardMaster();
        standardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setStdNameRoman("Std Name Roman");
        standardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setId(1);
        standardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster.setStandardName("Standard Name");
        standardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.standardMasterRepo.save((StandardMaster) any())).thenReturn(standardMaster);

        StandardMaster standardMaster1 = new StandardMaster();
        standardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setStdNameRoman("Std Name Roman");
        standardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setId(1);
        standardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster1.setStandardName("Standard Name");
        standardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualAddStandardResult = this.standardMasterService.AddStandard(standardMaster1, "janedoe");
        assertEquals("Added Standard: Standard Name", actualAddStandardResult.getBody());
        assertEquals("<200 OK OK,Added Standard: Standard Name,[]>", actualAddStandardResult.toString());
        assertEquals(HttpStatus.OK, actualAddStandardResult.getStatusCode());
        assertTrue(actualAddStandardResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.standardMasterRepo).save((StandardMaster) any());
        assertEquals("janedoe", standardMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteStandard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.standardMasterRepo).deleteById((Integer) any());
        when(this.standardMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteStandardResult = this.standardMasterService.DeleteStandard(1, "janedoe");
        assertEquals("Standard deleted", actualDeleteStandardResult.getBody());
        assertEquals("<200 OK OK,Standard deleted,[]>", actualDeleteStandardResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteStandardResult.getStatusCode());
        assertTrue(actualDeleteStandardResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.standardMasterRepo).deleteById((Integer) any());
        verify(this.standardMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteStandard2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.standardMasterRepo).deleteById((Integer) any());
        when(this.standardMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteStandardResult = this.standardMasterService.DeleteStandard(1, "janedoe");
        assertEquals("Invalid id", actualDeleteStandardResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteStandardResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteStandardResult.getStatusCode());
        assertTrue(actualDeleteStandardResult.getHeaders().isEmpty());
        verify(this.standardMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateStandard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StandardMaster standardMaster = new StandardMaster();
        standardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setStdNameRoman("Std Name Roman");
        standardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setId(1);
        standardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster.setStandardName("Standard Name");
        standardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        StandardMaster standardMaster1 = new StandardMaster();
        standardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setStdNameRoman("Std Name Roman");
        standardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setId(1);
        standardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster1.setStandardName("Standard Name");
        standardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.standardMasterRepo.save((StandardMaster) any())).thenReturn(standardMaster1);
        when(this.standardMasterRepo.getById((Integer) any())).thenReturn(standardMaster);
        when(this.standardMasterRepo.existsById((Integer) any())).thenReturn(true);

        StandardMaster standardMaster2 = new StandardMaster();
        standardMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster2.setStdNameRoman("Std Name Roman");
        standardMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster2.setId(1);
        standardMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster2.setStandardName("Standard Name");
        standardMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualUpdateStandardResult = this.standardMasterService.UpdateStandard(standardMaster2,
                "janedoe");
        assertEquals("Standard Updated", actualUpdateStandardResult.getBody());
        assertEquals("<200 OK OK,Standard Updated,[]>", actualUpdateStandardResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateStandardResult.getStatusCode());
        assertTrue(actualUpdateStandardResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.standardMasterRepo).existsById((Integer) any());
        verify(this.standardMasterRepo).getById((Integer) any());
        verify(this.standardMasterRepo).save((StandardMaster) any());
    }

    @Test
    public void testUpdateStandard2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StandardMaster standardMaster = new StandardMaster();
        standardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setStdNameRoman("Std Name Roman");
        standardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster.setId(1);
        standardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster.setStandardName("Standard Name");
        standardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");

        StandardMaster standardMaster1 = new StandardMaster();
        standardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setStdNameRoman("Std Name Roman");
        standardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster1.setId(1);
        standardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster1.setStandardName("Standard Name");
        standardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        when(this.standardMasterRepo.save((StandardMaster) any())).thenReturn(standardMaster1);
        when(this.standardMasterRepo.getById((Integer) any())).thenReturn(standardMaster);
        when(this.standardMasterRepo.existsById((Integer) any())).thenReturn(false);

        StandardMaster standardMaster2 = new StandardMaster();
        standardMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster2.setStdNameRoman("Std Name Roman");
        standardMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        standardMaster2.setId(1);
        standardMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        standardMaster2.setStandardName("Standard Name");
        standardMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        ResponseEntity<?> actualUpdateStandardResult = this.standardMasterService.UpdateStandard(standardMaster2,
                "janedoe");
        assertEquals("Failed to update", actualUpdateStandardResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateStandardResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateStandardResult.getStatusCode());
        assertTrue(actualUpdateStandardResult.getHeaders().isEmpty());
        verify(this.standardMasterRepo).existsById((Integer) any());
    }
}

