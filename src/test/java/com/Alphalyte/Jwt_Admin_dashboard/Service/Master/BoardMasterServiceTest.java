package com.Alphalyte.Jwt_Admin_dashboard.Service.Master;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.Master.BoardMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserLogReport;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.Master.BoardMasterRepo;
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

@ContextConfiguration(classes = {BoardMasterService.class})
@ExtendWith(SpringExtension.class)
public class BoardMasterServiceTest {
    @MockBean
    private BoardMasterRepo boardMasterRepo;

    @Autowired
    private BoardMasterService boardMasterService;

    @MockBean
    private UserLogReportRepo userLogReportRepo;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testGetAllBoards() {
        ArrayList<BoardMaster> boardMasterList = new ArrayList<BoardMaster>();
        when(this.boardMasterRepo.findAll()).thenReturn(boardMasterList);
        List<BoardMaster> actualGetAllBoardsResult = this.boardMasterService.GetAllBoards();
        assertSame(boardMasterList, actualGetAllBoardsResult);
        assertTrue(actualGetAllBoardsResult.isEmpty());
        verify(this.boardMasterRepo).findAll();
    }

    @Test
    public void testAddBoard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        BoardMaster boardMaster = new BoardMaster();
        boardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setId(1);
        boardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster.setBoardName("Board Name");
        boardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster.setBoardAbb("Board Abb");
        when(this.boardMasterRepo.save((BoardMaster) any())).thenReturn(boardMaster);

        BoardMaster boardMaster1 = new BoardMaster();
        boardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setId(1);
        boardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster1.setBoardName("Board Name");
        boardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster1.setBoardAbb("Board Abb");
        BoardMaster actualAddBoardResult = this.boardMasterService.AddBoard(boardMaster1, "janedoe");
        assertSame(boardMaster1, actualAddBoardResult);
        assertEquals("janedoe", actualAddBoardResult.getCreatedBy());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.boardMasterRepo).save((BoardMaster) any());
    }

    @Test
    public void testDeleteBoard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);
        doNothing().when(this.boardMasterRepo).deleteById((Integer) any());
        this.boardMasterService.DeleteBoard(1, "janedoe");
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.boardMasterRepo).deleteById((Integer) any());
    }

    @Test
    public void testUpdateBoard() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        BoardMaster boardMaster = new BoardMaster();
        boardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setId(1);
        boardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster.setBoardName("Board Name");
        boardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster.setBoardAbb("Board Abb");

        BoardMaster boardMaster1 = new BoardMaster();
        boardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setId(1);
        boardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster1.setBoardName("Board Name");
        boardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster1.setBoardAbb("Board Abb");
        when(this.boardMasterRepo.save((BoardMaster) any())).thenReturn(boardMaster1);
        when(this.boardMasterRepo.getById((Integer) any())).thenReturn(boardMaster);
        when(this.boardMasterRepo.existsById((Integer) any())).thenReturn(true);

        BoardMaster boardMaster2 = new BoardMaster();
        boardMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster2.setId(1);
        boardMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster2.setBoardName("Board Name");
        boardMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster2.setBoardAbb("Board Abb");
        ResponseEntity<?> actualUpdateBoardResult = this.boardMasterService.UpdateBoard(boardMaster2, "janedoe");
        assertEquals("Board Updated", actualUpdateBoardResult.getBody());
        assertEquals("<200 OK OK,Board Updated,[]>", actualUpdateBoardResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateBoardResult.getStatusCode());
        assertTrue(actualUpdateBoardResult.getHeaders().isEmpty());
        verify(this.userReposoritries).getUsercodeFromName(anyString());
        verify(this.userLogReportRepo).save((UserLogReport) any());
        verify(this.boardMasterRepo).existsById((Integer) any());
        verify(this.boardMasterRepo).getById((Integer) any());
        verify(this.boardMasterRepo).save((BoardMaster) any());
    }

    @Test
    public void testUpdateBoard2() {
        when(this.userReposoritries.getUsercodeFromName(anyString())).thenReturn(1);

        UserLogReport userLogReport = new UserLogReport();
        userLogReport.setLogType("Log Type");
        userLogReport.setLogDateTime(LocalDateTime.of(1, 1, 1, 1, 1));
        userLogReport.setUsercode(1);
        userLogReport.setUsername("janedoe");
        userLogReport.setFormName("Form Name");
        userLogReport.setId(1);
        when(this.userLogReportRepo.save((UserLogReport) any())).thenReturn(userLogReport);

        BoardMaster boardMaster = new BoardMaster();
        boardMaster.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster.setId(1);
        boardMaster.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster.setBoardName("Board Name");
        boardMaster.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster.setBoardAbb("Board Abb");

        BoardMaster boardMaster1 = new BoardMaster();
        boardMaster1.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster1.setId(1);
        boardMaster1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster1.setBoardName("Board Name");
        boardMaster1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster1.setBoardAbb("Board Abb");
        when(this.boardMasterRepo.save((BoardMaster) any())).thenReturn(boardMaster1);
        when(this.boardMasterRepo.getById((Integer) any())).thenReturn(boardMaster);
        when(this.boardMasterRepo.existsById((Integer) any())).thenReturn(false);

        BoardMaster boardMaster2 = new BoardMaster();
        boardMaster2.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster2.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        boardMaster2.setId(1);
        boardMaster2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        boardMaster2.setBoardName("Board Name");
        boardMaster2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        boardMaster2.setBoardAbb("Board Abb");
        ResponseEntity<?> actualUpdateBoardResult = this.boardMasterService.UpdateBoard(boardMaster2, "janedoe");
        assertEquals("Failed to update", actualUpdateBoardResult.getBody());
        assertEquals("<200 OK OK,Failed to update,[]>", actualUpdateBoardResult.toString());
        assertEquals(HttpStatus.OK, actualUpdateBoardResult.getStatusCode());
        assertTrue(actualUpdateBoardResult.getHeaders().isEmpty());
        verify(this.boardMasterRepo).existsById((Integer) any());
    }
}

