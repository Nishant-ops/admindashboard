package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.TestMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.TestMasterRepo;
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

@ContextConfiguration(classes = {TestMasterService.class})
@ExtendWith(SpringExtension.class)
public class TestMasterServiceTest {
    @MockBean
    private TestMasterRepo testMasterRepo;

    @Autowired
    private TestMasterService testMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllTests() {
        ArrayList<TestMaster> testMasterList = new ArrayList<TestMaster>();
        when(this.testMasterRepo.findAll()).thenReturn(testMasterList);
        List<TestMaster> actualGetAllTestsResult = this.testMasterService.GetAllTests();
        assertSame(testMasterList, actualGetAllTestsResult);
        assertTrue(actualGetAllTestsResult.isEmpty());
        verify(this.testMasterRepo).findAll();
    }

    @Test
    public void testAddTest() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        TestMaster testMaster = new TestMaster();
        testMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setId(1);
        testMaster.setMockTestName("Mock Test Name");
        testMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster.setLastYearPaper("Last Year Paper");
        when(this.testMasterRepo.save((TestMaster) any())).thenReturn(testMaster);

        TestMaster testMaster1 = new TestMaster();
        testMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setId(1);
        testMaster1.setMockTestName("Mock Test Name");
        testMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster1.setLastYearPaper("Last Year Paper");
        ResponseEntity<?> actualAddTestResult = this.testMasterService.AddTest(testMaster1, "janedoe");
        assertEquals("Added Test: Mock Test Name", actualAddTestResult.getBody());
        assertEquals("<200 OK OK,Added Test: Mock Test Name,[]>", actualAddTestResult.toString());
        assertEquals(HttpStatus.OK, actualAddTestResult.getStatusCode());
        assertTrue(actualAddTestResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.testMasterRepo).save((TestMaster) any());
        assertEquals("janedoe", testMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteTest() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.testMasterRepo).deleteById((Integer) any());
        when(this.testMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteTestResult = this.testMasterService.DeleteTest(1, "janedoe");
        assertEquals("Test deleted", actualDeleteTestResult.getBody());
        assertEquals("<200 OK OK,Test deleted,[]>", actualDeleteTestResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteTestResult.getStatusCode());
        assertTrue(actualDeleteTestResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.testMasterRepo).deleteById((Integer) any());
        verify(this.testMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteTest2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.testMasterRepo).deleteById((Integer) any());
        when(this.testMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteTestResult = this.testMasterService.DeleteTest(1, "janedoe");
        assertEquals("Invalid id", actualDeleteTestResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteTestResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteTestResult.getStatusCode());
        assertTrue(actualDeleteTestResult.getHeaders().isEmpty());
        verify(this.testMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateTest() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        TestMaster testMaster = new TestMaster();
        testMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setId(1);
        testMaster.setMockTestName("Mock Test Name");
        testMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster.setLastYearPaper("Last Year Paper");

        TestMaster testMaster1 = new TestMaster();
        testMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setId(1);
        testMaster1.setMockTestName("Mock Test Name");
        testMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster1.setLastYearPaper("Last Year Paper");
        when(this.testMasterRepo.save((TestMaster) any())).thenReturn(testMaster1);
        when(this.testMasterRepo.getById((Integer) any())).thenReturn(testMaster);
        when(this.testMasterRepo.existsById((Integer) any())).thenReturn(true);

        TestMaster testMaster2 = new TestMaster();
        testMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster2.setId(1);
        testMaster2.setMockTestName("Mock Test Name");
        testMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster2.setLastYearPaper("Last Year Paper");
        ResponseEntity<?> actualUpdateTestResult = this.testMasterService.UpdateTest(testMaster2, "janedoe");
        assertEquals("Test Updated", actualUpdateTestResult.getBody());
        assertEquals("<200 OK OK,Test Updated,[]>", actualUpdateTestResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateTestResult.getStatusCode());
        assertTrue(actualUpdateTestResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.testMasterRepo).existsById((Integer) any());
        verify(this.testMasterRepo).getById((Integer) any());
        verify(this.testMasterRepo).save((TestMaster) any());
    }

    @Test
    public void testUpdateTest2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        TestMaster testMaster = new TestMaster();
        testMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster.setId(1);
        testMaster.setMockTestName("Mock Test Name");
        testMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster.setLastYearPaper("Last Year Paper");

        TestMaster testMaster1 = new TestMaster();
        testMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster1.setId(1);
        testMaster1.setMockTestName("Mock Test Name");
        testMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster1.setLastYearPaper("Last Year Paper");
        when(this.testMasterRepo.save((TestMaster) any())).thenReturn(testMaster1);
        when(this.testMasterRepo.getById((Integer) any())).thenReturn(testMaster);
        when(this.testMasterRepo.existsById((Integer) any())).thenReturn(false);

        TestMaster testMaster2 = new TestMaster();
        testMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        testMaster2.setId(1);
        testMaster2.setMockTestName("Mock Test Name");
        testMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        testMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        testMaster2.setLastYearPaper("Last Year Paper");
        ResponseEntity<?> actualUpdateTestResult = this.testMasterService.UpdateTest(testMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateTestResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateTestResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateTestResult.getStatusCode());
        assertTrue(actualUpdateTestResult.getHeaders().isEmpty());
        verify(this.testMasterRepo).existsById((Integer) any());
    }
}

