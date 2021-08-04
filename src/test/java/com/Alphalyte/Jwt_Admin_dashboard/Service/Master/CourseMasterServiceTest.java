package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.CourseMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.CourseMasterRepo;
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

@ContextConfiguration(classes = {CourseMasterService.class})
@ExtendWith(SpringExtension.class)
public class CourseMasterServiceTest {
    @MockBean
    private CourseMasterRepo courseMasterRepo;

    @Autowired
    private CourseMasterService courseMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllCourses() {
        ArrayList<CourseMaster> courseMasterList = new ArrayList<CourseMaster>();
        when(this.courseMasterRepo.findAll()).thenReturn(courseMasterList);
        List<CourseMaster> actualGetAllCoursesResult = this.courseMasterService.GetAllCourses();
        assertSame(courseMasterList, actualGetAllCoursesResult);
        assertTrue(actualGetAllCoursesResult.isEmpty());
        verify(this.courseMasterRepo).findAll();
    }

    @Test
    public void testAddCourse() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        CourseMaster courseMaster = new CourseMaster();
        courseMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setId(1);
        courseMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster.setCourseAbb("Course Abb");
        courseMaster.setCourseName("Course Name");
        when(this.courseMasterRepo.save((CourseMaster) any())).thenReturn(courseMaster);

        CourseMaster courseMaster1 = new CourseMaster();
        courseMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setId(1);
        courseMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster1.setCourseAbb("Course Abb");
        courseMaster1.setCourseName("Course Name");
        ResponseEntity<?> actualAddCourseResult = this.courseMasterService.AddCourse(courseMaster1, "janedoe");
        assertEquals("Added course: Course Name", actualAddCourseResult.getBody());
        assertEquals("<200 OK OK,Added course: Course Name,[]>", actualAddCourseResult.toString());
        assertEquals(HttpStatus.OK, actualAddCourseResult.getStatusCode());
        assertTrue(actualAddCourseResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.courseMasterRepo).save((CourseMaster) any());
        assertEquals("janedoe", courseMaster1.getCreatedBy());
    }

    @Test
    public void testDeleteCourse() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.courseMasterRepo).deleteById((Integer) any());
        when(this.courseMasterRepo.existsById((Integer) any())).thenReturn(true);
        ResponseEntity<?> actualDeleteCourseResult = this.courseMasterService.DeleteCourse(1, "janedoe");
        assertEquals("Course deleted", actualDeleteCourseResult.getBody());
        assertEquals("<200 OK OK,Course deleted,[]>", actualDeleteCourseResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteCourseResult.getStatusCode());
        assertTrue(actualDeleteCourseResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.courseMasterRepo).deleteById((Integer) any());
        verify(this.courseMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testDeleteCourse2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.courseMasterRepo).deleteById((Integer) any());
        when(this.courseMasterRepo.existsById((Integer) any())).thenReturn(false);
        ResponseEntity<?> actualDeleteCourseResult = this.courseMasterService.DeleteCourse(1, "janedoe");
        assertEquals("Invalid id", actualDeleteCourseResult.getBody());
        assertEquals("<200 OK OK,Invalid id,[]>", actualDeleteCourseResult.toString());
        assertEquals(HttpStatus.OK, actualDeleteCourseResult.getStatusCode());
        assertTrue(actualDeleteCourseResult.getHeaders().isEmpty());
        verify(this.courseMasterRepo).existsById((Integer) any());
    }

    @Test
    public void testUpdateCourse() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        CourseMaster courseMaster = new CourseMaster();
        courseMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setId(1);
        courseMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster.setCourseAbb("Course Abb");
        courseMaster.setCourseName("Course Name");

        CourseMaster courseMaster1 = new CourseMaster();
        courseMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setId(1);
        courseMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster1.setCourseAbb("Course Abb");
        courseMaster1.setCourseName("Course Name");
        when(this.courseMasterRepo.save((CourseMaster) any())).thenReturn(courseMaster1);
        when(this.courseMasterRepo.getById((Integer) any())).thenReturn(courseMaster);
        when(this.courseMasterRepo.existsById((Integer) any())).thenReturn(true);

        CourseMaster courseMaster2 = new CourseMaster();
        courseMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster2.setId(1);
        courseMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster2.setCourseAbb("Course Abb");
        courseMaster2.setCourseName("Course Name");
        ResponseEntity<?> actualUpdateCourseResult = this.courseMasterService.UpdateCourse(courseMaster2, "janedoe");
        assertEquals("Course Updated", actualUpdateCourseResult.getBody());
        assertEquals("<200 OK OK,Course Updated,[]>", actualUpdateCourseResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateCourseResult.getStatusCode());
        assertTrue(actualUpdateCourseResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.courseMasterRepo).existsById((Integer) any());
        verify(this.courseMasterRepo).getById((Integer) any());
        verify(this.courseMasterRepo).save((CourseMaster) any());
    }

    @Test
    public void testUpdateCourse2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        CourseMaster courseMaster = new CourseMaster();
        courseMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster.setId(1);
        courseMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster.setCourseAbb("Course Abb");
        courseMaster.setCourseName("Course Name");

        CourseMaster courseMaster1 = new CourseMaster();
        courseMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster1.setId(1);
        courseMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster1.setCourseAbb("Course Abb");
        courseMaster1.setCourseName("Course Name");
        when(this.courseMasterRepo.save((CourseMaster) any())).thenReturn(courseMaster1);
        when(this.courseMasterRepo.getById((Integer) any())).thenReturn(courseMaster);
        when(this.courseMasterRepo.existsById((Integer) any())).thenReturn(false);

        CourseMaster courseMaster2 = new CourseMaster();
        courseMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        courseMaster2.setId(1);
        courseMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        courseMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        courseMaster2.setCourseAbb("Course Abb");
        courseMaster2.setCourseName("Course Name");
        ResponseEntity<?> actualUpdateCourseResult = this.courseMasterService.UpdateCourse(courseMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateCourseResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateCourseResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateCourseResult.getStatusCode());
        assertTrue(actualUpdateCourseResult.getHeaders().isEmpty());
        verify(this.courseMasterRepo).existsById((Integer) any());
    }
}

