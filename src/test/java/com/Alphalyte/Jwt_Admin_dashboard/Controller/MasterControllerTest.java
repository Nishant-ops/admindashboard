package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ClassGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.CourseMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.ExamMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StandardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.StateMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.SubjectMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.TestMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.BoardMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.ClassGroupMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.CourseMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.ExamMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.StandardMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.StateMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.SubjectMasterService;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.TestMasterService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MasterController.class})
@ExtendWith(SpringExtension.class)
public class MasterControllerTest {
    @MockBean
    private BoardMasterService boardMasterService;

    @MockBean
    private ClassGroupMasterService classGroupMasterService;

    @MockBean
    private CourseMasterService courseMasterService;

    @MockBean
    private ExamMasterService examMasterService;

    @Autowired
    private MasterController masterController;

    @MockBean
    private StandardMasterService standardMasterService;

    @MockBean
    private StateMasterService stateMasterService;

    @MockBean
    private SubjectMasterService subjectMasterService;

    @MockBean
    private TestMasterService testMasterService;

    @Test
    public void testDeleteBoard() throws Exception {
        doNothing().when(this.boardMasterService).DeleteBoard(anyInt(), anyString());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/BoardMaster/{id}", 1)
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDeleteClassGroupMaster() throws Exception {
        when(this.classGroupMasterService.DeleteClassGroup(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ClassGroupMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteCourseMaster() throws Exception {
        when(this.courseMasterService.DeleteCourse(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/CourseMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteExam() throws Exception {
        when(this.examMasterService.DeleteExam(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/ExamMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteStandardMaster() throws Exception {
        when(this.standardMasterService.DeleteStandard(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/StandardMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteStateMaster() throws Exception {
        when(this.stateMasterService.DeleteState(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/StateMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteSubjectMaster() throws Exception {
        when(this.subjectMasterService.DeleteSubject(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/SubjectMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testDeleteTestMaster() throws Exception {
        when(this.testMasterService.DeleteTest(anyInt(), anyString()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/TestMaster/{id}", 1)
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    @Test
    public void testGetAllBoards() throws Exception {
        when(this.boardMasterService.GetAllBoards()).thenReturn(new ArrayList<BoardMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/BoardMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllBoards2() throws Exception {
        when(this.boardMasterService.GetAllBoards()).thenReturn(new ArrayList<BoardMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/BoardMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllClassGroupMaster() throws Exception {
        when(this.classGroupMasterService.GetAllClassGroups()).thenReturn(new ArrayList<ClassGroupMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ClassGroupMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllClassGroupMaster2() throws Exception {
        when(this.classGroupMasterService.GetAllClassGroups()).thenReturn(new ArrayList<ClassGroupMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ClassGroupMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllCourseMaster() throws Exception {
        when(this.courseMasterService.GetAllCourses()).thenReturn(new ArrayList<CourseMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/CourseMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllCourseMaster2() throws Exception {
        when(this.courseMasterService.GetAllCourses()).thenReturn(new ArrayList<CourseMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/CourseMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllExamMaster() throws Exception {
        when(this.examMasterService.GetAllExams()).thenReturn(new ArrayList<ExamMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ExamMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllExamMaster2() throws Exception {
        when(this.examMasterService.GetAllExams()).thenReturn(new ArrayList<ExamMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/ExamMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllStandardMaster() throws Exception {
        when(this.standardMasterService.GetAllStandards()).thenReturn(new ArrayList<StandardMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/StandardMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllStandardMaster2() throws Exception {
        when(this.standardMasterService.GetAllStandards()).thenReturn(new ArrayList<StandardMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/StandardMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllStateMaster() throws Exception {
        when(this.stateMasterService.GetAllStates()).thenReturn(new ArrayList<StateMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/StateMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllStateMaster2() throws Exception {
        when(this.stateMasterService.GetAllStates()).thenReturn(new ArrayList<StateMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/StateMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllSubjectMaster() throws Exception {
        when(this.subjectMasterService.GetAllSubjects()).thenReturn(new ArrayList<SubjectMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/SubjectMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllSubjectMaster2() throws Exception {
        when(this.subjectMasterService.GetAllSubjects()).thenReturn(new ArrayList<SubjectMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/SubjectMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllTestMaster() throws Exception {
        when(this.testMasterService.GetAllTests()).thenReturn(new ArrayList<TestMaster>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/TestMaster");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllTestMaster2() throws Exception {
        when(this.testMasterService.GetAllTests()).thenReturn(new ArrayList<TestMaster>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/TestMaster");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.masterController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

