package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.*;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MasterController {

    @Autowired
    BoardMasterRepo boardMasterRepo;
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
        return boardMasterRepo.findAll();
    }

    @PostMapping("/BoardMaster")
    public BoardMaster addBoard(@RequestBody BoardMaster boardMaster){
        boardMaster.setCreatedAt(LocalDateTime.now());
        boardMasterRepo.save(boardMaster);
        return boardMaster;
    }

    @DeleteMapping("/BoardMaster/{id}")
    public void deleteBoard(@PathVariable int id){
        boardMasterRepo.deleteById(id);
    }

    @PutMapping("/BoardMaster")
    public ResponseEntity<?> updateBoard(@RequestBody BoardMaster boardMaster){
        boolean exist = boardMasterRepo.existsById(boardMaster.getId());
        if (exist){
            boardMaster.setId(boardMaster.getId());
            boardMasterRepo.save(boardMaster);
            return ResponseEntity.ok("Board Updated");
        }
        return ResponseEntity.ok("Failed to update");
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
    /*------------------------------------------------------------------------------------*/
}
