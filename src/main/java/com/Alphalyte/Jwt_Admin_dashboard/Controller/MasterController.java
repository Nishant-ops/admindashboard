package com.Alphalyte.Jwt_Admin_dashboard.Controller;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.*;
import com.Alphalyte.Jwt_Admin_dashboard.Service.Master.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class MasterController {

    @Autowired
    BoardMasterService boardMasterService;
    @Autowired
    ClassGroupMasterService classGroupMasterService;
    @Autowired
    CourseMasterService courseMasterService;
    @Autowired
    ExamMasterService examMasterService;
    @Autowired
    StandardMasterService standardMasterService;
    @Autowired
    StateMasterService stateMasterService;
    @Autowired
    SubjectMasterService subjectMasterService;
    @Autowired
    TestMasterService testMasterService;

/*--------------------------------BoardMaster-----------------------------------------*/
    @GetMapping("/BoardMaster")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DEVELOPER')")
    public List<BoardMaster> getAllBoards(){
        return boardMasterService.GetAllBoards();
    }

    @PostMapping("/BoardMaster")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('DEVELOPER')")
    public BoardMaster addBoard(@RequestBody BoardMaster boardMaster,@RequestParam(required = true) String username){
        return boardMasterService.AddBoard(boardMaster,username);
    }

    @DeleteMapping("/BoardMaster/{id}")
    public void deleteBoard(@PathVariable int id,@RequestParam String username){
        boardMasterService.DeleteBoard(id,username);
    }

    @PutMapping("/BoardMaster")
    public ResponseEntity<?> updateBoard(@RequestBody BoardMaster boardMaster, @RequestParam String username){
        return boardMasterService.UpdateBoard(boardMaster, username);
    }


    /*--------------------------------ClassGroupMaster-----------------------------------------*/

    @GetMapping("/ClassGroupMaster")
    public List<ClassGroupMaster> getAllClassGroupMaster()
    {
        return classGroupMasterService.GetAllClassGroups();
    }

    @PostMapping("/ClassGroupMaster")
    public ResponseEntity<?> AddNewClassGroupMaster(@RequestBody ClassGroupMaster classGroupMaster,@RequestParam(required = true) String username)
    {
        return classGroupMasterService.AddClassGroup(classGroupMaster,username);
    }

   @DeleteMapping("/ClassGroupMaster/{id}")
   public ResponseEntity<?> DeleteClassGroupMaster(@PathVariable int id,@RequestParam(required = true) String username)
   {
       return classGroupMasterService.DeleteClassGroup(id,username);
   }

   @PutMapping("/ClassGroupMaster")
   public ResponseEntity<?> updateClassGroup(@RequestBody ClassGroupMaster classGroupMaster,@RequestParam(required = true) String username)
   {
        return classGroupMasterService.UpdateClassGroup(classGroupMaster,username);
   }


    /*--------------------------------CourseMaster-----------------------------------------*/

    @GetMapping("/CourseMaster")
    public List<CourseMaster> getAllCourseMaster()
    {
        return courseMasterService.GetAllCourses();
    }

    @PostMapping("/CourseMaster")
    public ResponseEntity<?> AddNewCourseMaster(@RequestBody CourseMaster courseMaster, @RequestParam String username)
    {
        return courseMasterService.AddCourse(courseMaster,username);
    }

    @DeleteMapping("/CourseMaster/{id}")
    public ResponseEntity DeleteCourseMaster(@PathVariable int id, @RequestParam String username)
    {
        return courseMasterService.DeleteCourse(id,username);
    }

    @PutMapping("/CourseMaster")
    public ResponseEntity<?> updateCourse(@RequestBody CourseMaster courseMaster, @RequestParam String username){
        return courseMasterService.UpdateCourse(courseMaster,username);
    }

    /*--------------------------------ExamMaster-----------------------------------------*/

    @GetMapping("/ExamMaster")
    public List<ExamMaster> getAllExamMaster(){
        return examMasterService.GetAllExams();
    }

    @PostMapping("/ExamMaster")
    public ResponseEntity<?> addExam(@RequestBody ExamMaster examMaster, @RequestParam String username){
        return examMasterService.AddExam(examMaster,username);
    }

    @DeleteMapping("/ExamMaster/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable int id, @RequestParam String username){
        return examMasterService.DeleteExam(id,username);
    }

    @PutMapping("/ExamMaster")
    public ResponseEntity<?> updateExam(@RequestBody ExamMaster examMaster, @RequestParam String username){
        return examMasterService.UpdateExam(examMaster,username);
    }

    /*--------------------------------StandardMaster-----------------------------------------*/

    @GetMapping("/StandardMaster")
    public List<StandardMaster> getAllStandardMaster(){
        return standardMasterService.GetAllStandards();
    }

    @PostMapping("/StandardMaster")
    public ResponseEntity addStandardMaster(@RequestBody StandardMaster standardMaster, @RequestParam String username){
        return standardMasterService.AddStandard(standardMaster,username);
    }

    @DeleteMapping("/StandardMaster/{id}")
    public ResponseEntity<?> deleteStandardMaster(@PathVariable int id, @RequestParam String username){
        return standardMasterService.DeleteStandard(id,username);
    }

    @PutMapping("/StandardMaster")
    public ResponseEntity<?> updateStandard(@RequestBody StandardMaster standardMaster, @RequestParam String username){
        return standardMasterService.UpdateStandard(standardMaster,username);
    }

    /*--------------------------------StateMaster-----------------------------------------*/

    @GetMapping("/StateMaster")
    public List<StateMaster> getAllStateMaster(){
        return stateMasterService.GetAllStates();
    }

    @PostMapping("/StateMaster")
    public ResponseEntity<?> addStateMaster(@RequestBody StateMaster stateMaster, @RequestParam String username){
        return stateMasterService.AddState(stateMaster,username);
    }

    @DeleteMapping("/StateMaster/{id}")
    public ResponseEntity<?> deleteStateMaster(@PathVariable int id, @RequestParam String username){
        return stateMasterService.DeleteState(id, username);
    }

    @PutMapping("/StateMaster")
    public ResponseEntity<?> updateState(@RequestBody StateMaster stateMaster, @RequestParam String username){
        return stateMasterService.UpdateState(stateMaster,username);
    }

    /*--------------------------------SubjectMaster-----------------------------------------*/

    @GetMapping("/SubjectMaster")
    public List<SubjectMaster> getAllSubjectMaster()
    {
        return subjectMasterService.GetAllSubjects();
    }

    @PostMapping("/SubjectMaster")
    @PreAuthorize("hasAuthority('DEVELOPER')")
    public ResponseEntity<?> AddNewSubjectMaster(@RequestBody SubjectMaster subjectMaster, @RequestParam String username)
    {
        return subjectMasterService.AddSubject(subjectMaster,username);
    }

    @PutMapping("/SubjectMaster")
    public ResponseEntity<?> updateSubjectMaster(@RequestBody SubjectMaster subjectMaster, @RequestParam String username)
    {
        return subjectMasterService.UpdateSubject(subjectMaster,username);
    }

    @DeleteMapping("/SubjectMaster/{id}")
    public ResponseEntity<?> DeleteSubjectMaster(@PathVariable int id, @RequestParam String username)
    {
        return subjectMasterService.DeleteSubject(id,username);
    }

    /*--------------------------------TestMaster-----------------------------------------*/

    @GetMapping("/TestMaster")
    public List<TestMaster> getAllTestMaster(){
        return testMasterService.GetAllTests();
    }

    @PostMapping("/TestMaster")
    public ResponseEntity addTestMaster(@RequestBody TestMaster testMaster, @RequestParam String username){
        return testMasterService.AddTest(testMaster, username);
    }

    @DeleteMapping("/TestMaster/{id}")
    public ResponseEntity deleteTestMaster(@PathVariable int id, @RequestParam String username){
        return testMasterService.DeleteTest(id, username);
    }

    @PutMapping("/TestMaster")
    public ResponseEntity<?> updateTest(@RequestBody TestMaster testMaster, @RequestParam String username){
        return testMasterService.UpdateTest(testMaster,username);
    }
    /*------------------------------------------------------------------------------------*/

} //End MasterController
