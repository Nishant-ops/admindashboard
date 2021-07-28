package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.*;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.*;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.BoardMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MasterController {

    @Autowired
    BoardMasterService boardMasterService;
    @Autowired
    ClassGroupMasterRepo classGroupMasterRepo;
    @Autowired
    CourseMasterRepo courseMasterRepo;
    @Autowired
    ExamMasterRepo examMasterRepo;
    @Autowired
    StandardMasterRepo standardMasterRepo;
    @Autowired
    StateMasterRepo stateMasterRepo;
    @Autowired
    SubjectMasterRepo subjectMasterRepo;
    @Autowired
    TestMasterRepo testMasterRepo;
/*--------------------------------BoardMaster-----------------------------------------*/
    @GetMapping("/BoardMaster")
    public List<BoardMaster> getAllBoards(){
        return boardMasterService.GetAllBoards();
    }

    @PostMapping("/BoardMaster")
    public BoardMaster addBoard(@RequestBody BoardMaster boardMaster){
        return boardMasterService.AddBoard(boardMaster);
    }

    @DeleteMapping("/BoardMaster/{id}")
    public void deleteBoard(@PathVariable int id){

        boardMasterService.DeleteBoard(id);
    }

    @PutMapping("/BoardMaster")
    public ResponseEntity<?> updateBoard(@RequestBody BoardMaster boardMaster){
        return boardMasterService.UpdateBoard(boardMaster);
    }


    /*--------------------------------ClassGroupMaster-----------------------------------------*/

    @GetMapping("/ClassGroupMaster")
    public List<ClassGroupMaster> getAllClassGroupMaster()
    {
        return classGroupMasterRepo.findAll();
    }

    @PostMapping("/ClassGroupMaster")
    public ClassGroupMaster AddNewClassGroupMaster(@RequestBody ClassGroupMaster classGroupMaster)
    {
        classGroupMaster.setCreatedAt(LocalDateTime.now());
        classGroupMasterRepo.save(classGroupMaster);
        return classGroupMaster;
    }

   @DeleteMapping("/ClassGroupMaster/{id}")
   public void DeleteClassGroupMaster(@PathVariable int id)
   {
       classGroupMasterRepo.deleteById(id);
   }

   @PutMapping("/ClassGroupMaster")
   public ResponseEntity<?> updateClassGroup(@RequestBody ClassGroupMaster classGroupMaster){
       boolean exist = classGroupMasterRepo.existsById(classGroupMaster.getId());
       if (exist){
           ClassGroupMaster dbuser = classGroupMasterRepo.getById(classGroupMaster.getId());
           dbuser.setModifiedAt(LocalDateTime.now());
           dbuser.setModifiedBy(classGroupMaster.getModifiedBy());
           dbuser.setClassGroup(classGroupMaster.getClassGroup());
           classGroupMasterRepo.save(dbuser);
           return ResponseEntity.ok("Class Group Updated");
       }
       return ResponseEntity.ok("Failed to update");
   }


    /*--------------------------------CourseMaster-----------------------------------------*/

    @GetMapping("/CourseMaster")
    public List<CourseMaster> getAllCourseMaster()
    {
        return courseMasterRepo.findAll();
    }

    @PostMapping("/CourseMaster")
    public CourseMaster AddNewCourseMaster(@RequestBody CourseMaster courseMaster)
    {
       courseMaster.setCreatedAt(LocalDateTime.now());
       courseMasterRepo.save(courseMaster);
       return courseMaster;
    }

    @DeleteMapping("/CourseMaster/{id}")
    public void DeleteCourseMaster(@PathVariable int id)
    {
        courseMasterRepo.deleteById(id);
    }

    @PutMapping("/CourseMaster")
    public ResponseEntity<?> updateCourse(@RequestBody CourseMaster courseMaster){
        boolean exist = courseMasterRepo.existsById(courseMaster.getId());
        if (exist){
            CourseMaster dbuser=courseMasterRepo.getById(courseMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(courseMaster.getModifiedBy());
            dbuser.setCourseName(courseMaster.getCourseName());
            dbuser.setCourseAbb(courseMaster.getCourseAbb());
            courseMasterRepo.save(dbuser);
            return ResponseEntity.ok("Course Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }

    /*--------------------------------ExamMaster-----------------------------------------*/

    @GetMapping("/ExamMaster")
    public List<ExamMaster> getAllExamMaster(){
        return examMasterRepo.findAll();
    }

    @PostMapping("/ExamMaster")
    public ExamMaster addExam(@RequestBody ExamMaster examMaster){
        examMaster.setCreatedAt(LocalDateTime.now());
        examMasterRepo.save(examMaster);
        return examMaster;
    }

    @DeleteMapping("/ExamMaster/{id}")
    public void deleteExam(@PathVariable int id){
        examMasterRepo.deleteById(id);
    }

    @PutMapping("/ExamMaster")
    public ResponseEntity<?> updateExam(@RequestBody ExamMaster examMaster){
        boolean exist = examMasterRepo.existsById(examMaster.getId());
        if (exist){
            ExamMaster dbuser = examMasterRepo.getById(examMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(examMaster.getModifiedBy());
            dbuser.setExamName(examMaster.getExamName());
            dbuser.setExamAbb(examMaster.getExamAbb());
            examMasterRepo.save(dbuser);
            return ResponseEntity.ok("Exam Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }

    /*--------------------------------StandardMaster-----------------------------------------*/

    @GetMapping("/StandardMaster")
    public List<StandardMaster> getAllStandardMaster(){
        return standardMasterRepo.findAll();
    }

    @PostMapping("/StandardMaster")
    public StandardMaster addStandardMaster(@RequestBody StandardMaster standardMaster){
        standardMaster.setCreatedAt(LocalDateTime.now());
        standardMasterRepo.save(standardMaster);
        return standardMaster;
    }

    @DeleteMapping("/StandardMaster/{id}")
    public void deleteStandardMaster(@PathVariable int id){
        standardMasterRepo.deleteById(id);
    }

    @PutMapping("/StandardMaster")
    public ResponseEntity<?> updateStandard(@RequestBody StandardMaster standardMaster){
        boolean exist = standardMasterRepo.existsById(standardMaster.getId());
        if (exist){
            StandardMaster dbuser = standardMasterRepo.getById(standardMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(standardMaster.getModifiedBy());
            dbuser.setStandardName(standardMaster.getStandardName());
            dbuser.setStdNameRoman(standardMaster.getStdNameRoman());
            standardMasterRepo.save(dbuser);
            return ResponseEntity.ok("Board Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }

    /*--------------------------------StateMaster-----------------------------------------*/

    @GetMapping("/StateMaster")
    public List<StateMaster> getAllStateMaster(){
        return stateMasterRepo.findAll();
    }

    @PostMapping("/StateMaster")
    public StateMaster addStateMaster(@RequestBody StateMaster stateMaster){
        stateMaster.setCreatedAt(LocalDateTime.now());
        stateMasterRepo.save(stateMaster);
        return stateMaster;
    }

    @DeleteMapping("/StateMaster/{id}")
    public void deleteStateMaster(@PathVariable int id){
        stateMasterRepo.deleteById(id);
    }

    @PutMapping("/StateMaster")
    public ResponseEntity<?> updateState(@RequestBody StateMaster stateMaster){
        boolean exist = stateMasterRepo.existsById(stateMaster.getId());
        if (exist){
            StateMaster dbuser = stateMasterRepo.getById(stateMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(stateMaster.getModifiedBy());
            dbuser.setStateName(stateMaster.getStateName());
            dbuser.setStateCode(stateMaster.getStateCode());
            stateMasterRepo.save(dbuser);
            return ResponseEntity.ok("State Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }

    /*--------------------------------SubjectMaster-----------------------------------------*/

    @GetMapping("/SubjectMaster")
    public List<SubjectMaster> getAllSubjectMaster()
    {
        return subjectMasterRepo.findAll();
    }

    @PostMapping("/SubjectMaster")
    public SubjectMaster AddNewSubjectMaster(@RequestBody SubjectMaster subjectMaster)
    {
        subjectMaster.setCreatedAt(LocalDateTime.now());
        subjectMasterRepo.save(subjectMaster);
        return subjectMaster;
    }

    @PutMapping("/SubjectMaster")
    public ResponseEntity<?> updateSubjectMaster(@RequestBody SubjectMaster subjectMaster){
        boolean exist = subjectMasterRepo.existsById(subjectMaster.getId());
        if (exist){
            SubjectMaster dbuser = subjectMasterRepo.getById(subjectMaster.getId());
            dbuser.setSubjectName(subjectMaster.getSubjectName());
            dbuser.setSubjectAbb(subjectMaster.getSubjectAbb());
            dbuser.setModifiedBy(subjectMaster.getModifiedBy());
            dbuser.setModifiedAt(LocalDateTime.now());
            subjectMasterRepo.save(dbuser);
            return ResponseEntity.ok("modified");
        }
        return ResponseEntity.ok("failed");
    }

    @DeleteMapping("/SubjectMaster/{id}")
    public void DeleteSubjectMaster(@PathVariable int id)
    {
        subjectMasterRepo.deleteById(id);
    }

    /*--------------------------------TestMaster-----------------------------------------*/

    @GetMapping("/TestMaster")
    public List<TestMaster> getAllTestMaster(){
        return testMasterRepo.findAll();
    }

    @PostMapping("/TestMaster")
    public TestMaster addTestMaster(@RequestBody TestMaster testMaster){
        testMaster.setCreatedAt(LocalDateTime.now());
        testMasterRepo.save(testMaster);
        return testMaster;
    }

    @DeleteMapping("/TestMaster/{id}")
    public void deleteTestMaster(@PathVariable int id){
        testMasterRepo.deleteById(id);
    }

    @PutMapping("/TestMaster")
    public ResponseEntity<?> updateTest(@RequestBody TestMaster testMaster){
        boolean exist = testMasterRepo.existsById(testMaster.getId());
        if (exist){
            TestMaster dbuser = testMasterRepo.getById(testMaster.getId());
            dbuser.setModifiedAt(LocalDateTime.now());
            dbuser.setModifiedBy(testMaster.getModifiedBy());
            dbuser.setMockTestName(testMaster.getMockTestName());
            dbuser.setLastYearPaper(testMaster.getLastYearPaper());
            testMasterRepo.save(dbuser);
            return ResponseEntity.ok("Test Updated");
        }
        return ResponseEntity.ok("Failed to update");
    }
    /*------------------------------------------------------------------------------------*/
}
