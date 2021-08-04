package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StateMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.StateMasterRepo;
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

@ContextConfiguration(classes = {StateMasterService.class})
@ExtendWith(SpringExtension.class)
public class StateMasterServiceTest {
    @MockBean
    private StateMasterRepo stateMasterRepo;

    @Autowired
    private StateMasterService stateMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllStates() {
        ArrayList<StateMaster> stateMasterList = new ArrayList<StateMaster>();
        when(this.stateMasterRepo.findAll()).thenReturn(stateMasterList);
        List<StateMaster> actualGetAllStatesResult = this.stateMasterService.GetAllStates();
        assertSame(stateMasterList, actualGetAllStatesResult);
        assertTrue(actualGetAllStatesResult.isEmpty());
        verify(this.stateMasterRepo).findAll();
    }

    @Test
    public void testAddState() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StateMaster stateMaster = new StateMaster();
        stateMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setStateName("MD");
        stateMaster.setId(1);
        stateMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster.setStateCode("MD");
        when(this.stateMasterRepo.save((StateMaster) any())).thenReturn(stateMaster);

        StateMaster stateMaster1 = new StateMaster();
        stateMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setStateName("MD");
        stateMaster1.setId(1);
        stateMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster1.setStateCode("MD");
        ResponseEntity<?> actualAddStateResult = this.stateMasterService.AddState(stateMaster1, "janedoe");
        assertEquals("Added Standard: MD", actualAddStateResult.getBody());
        assertEquals("<200 OK OK,Added Standard: MD,[]>", actualAddStateResult.toString());
        assertEquals(HttpStatus.OK, actualAddStateResult.getStatusCode());
        assertTrue(actualAddStateResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.stateMasterRepo).save((StateMaster) any());
        assertEquals("janedoe", stateMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteState() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.stateMasterRepo).deleteById((Integer) any());
        when(this.stateMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteStateResult = this.stateMasterService.DeleteState(1, "janedoe");
        assertEquals("State deleted", actualDeleteStateResult.getBody());
        assertEquals("<200 OK OK,State deleted,[]>", actualDeleteStateResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteStateResult.getStatusCode());
        assertTrue(actualDeleteStateResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.stateMasterRepo).deleteById((Integer) any());
        verify(this.stateMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteState2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.stateMasterRepo).deleteById((Integer) any());
        when(this.stateMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteStateResult = this.stateMasterService.DeleteState(1, "janedoe");
        assertEquals("Invalid id", actualDeleteStateResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteStateResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteStateResult.getStatusCode());
        assertTrue(actualDeleteStateResult.getHeaders().isEmpty());
        verify(this.stateMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateState() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StateMaster stateMaster = new StateMaster();
        stateMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setStateName("MD");
        stateMaster.setId(1);
        stateMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster.setStateCode("MD");

        StateMaster stateMaster1 = new StateMaster();
        stateMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setStateName("MD");
        stateMaster1.setId(1);
        stateMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster1.setStateCode("MD");
        when(this.stateMasterRepo.save((StateMaster) any())).thenReturn(stateMaster1);
        when(this.stateMasterRepo.getById((Integer) any())).thenReturn(stateMaster);
        when(this.stateMasterRepo.existsById((Integer) any())).thenReturn(true);

        StateMaster stateMaster2 = new StateMaster();
        stateMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster2.setStateName("MD");
        stateMaster2.setId(1);
        stateMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster2.setStateCode("MD");
        ResponseEntity<?> actualUpdateStateResult = this.stateMasterService.UpdateState(stateMaster2, "janedoe");
        assertEquals("State Updated", actualUpdateStateResult.getBody());
        assertEquals("<200 OK OK,State Updated,[]>", actualUpdateStateResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateStateResult.getStatusCode());
        assertTrue(actualUpdateStateResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.stateMasterRepo).existsById((Integer) any());
        verify(this.stateMasterRepo).getById((Integer) any());
        verify(this.stateMasterRepo).save((StateMaster) any());
    }

    @Test
    public void testUpdateState2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        StateMaster stateMaster = new StateMaster();
        stateMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster.setStateName("MD");
        stateMaster.setId(1);
        stateMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster.setStateCode("MD");

        StateMaster stateMaster1 = new StateMaster();
        stateMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster1.setStateName("MD");
        stateMaster1.setId(1);
        stateMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster1.setStateCode("MD");
        when(this.stateMasterRepo.save((StateMaster) any())).thenReturn(stateMaster1);
        when(this.stateMasterRepo.getById((Integer) any())).thenReturn(stateMaster);
        when(this.stateMasterRepo.existsById((Integer) any())).thenReturn(false);

        StateMaster stateMaster2 = new StateMaster();
        stateMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        stateMaster2.setStateName("MD");
        stateMaster2.setId(1);
        stateMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stateMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        stateMaster2.setStateCode("MD");
        ResponseEntity<?> actualUpdateStateResult = this.stateMasterService.UpdateState(stateMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateStateResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateStateResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateStateResult.getStatusCode());
        assertTrue(actualUpdateStateResult.getHeaders().isEmpty());
        verify(this.stateMasterRepo).existsById((Integer) any());
    }
}

