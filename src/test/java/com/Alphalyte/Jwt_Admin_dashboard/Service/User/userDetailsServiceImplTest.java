package com.Alphalyte.Jwt_Admin_dashboard.Service.User;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.Alphalyte.Jwt_Admin_dashboard.Model.User.UserGroupMaster;
import com.Alphalyte.Jwt_Admin_dashboard.Model.User.user;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserGroupMasterRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserLogReportRepo;
import com.Alphalyte.Jwt_Admin_dashboard.Reposoritries.User.UserReposoritries;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {userDetailsServiceImpl.class})
@ExtendWith(SpringExtension.class)
public class userDetailsServiceImplTest {
    @Autowired
    private userDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    private UserReposoritries userReposoritries;

    @Test
    public void testLoadUserByUsername() throws UnsupportedEncodingException, UsernameNotFoundException {
        UserGroupMaster userGroupMaster = new UserGroupMaster();
        userGroupMaster.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setGid(1);
        userGroupMaster.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        userGroupMaster.setActive(true);
        userGroupMaster.setModifiedby("Jan 1, 2020 9:00am GMT+0100");
        userGroupMaster.setGroupname("Groupname");
        userGroupMaster.setCreatedby("Jan 1, 2020 8:00am GMT+0100");

        user user = new user();
        user.setImage("AAAAAAAA".getBytes("UTF-8"));
        user.setPassword("iloveyou");
        user.setGroup_name(userGroupMaster);
        user.setEmail("jane.doe@example.org");
        user.setModifiedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setLocaladdress("42 Main St");
        user.setPermanentAddress("42 Main St");
        user.setDateOfJoining(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setUsercode(1);
        user.setLanguage("Language");
        user.setUsername("janedoe");
        user.setUserService(new UserMasterService(mock(UserReposoritries.class), mock(UserGroupMasterRepo.class),
                mock(UserLogReportRepo.class)));
        user.setCreatedat(LocalDateTime.of(1, 1, 1, 1, 1));
        user.setCreatedBY("Jan 1, 2020 8:00am GMT+0100");
        user.setModifiedBY("Jan 1, 2020 9:00am GMT+0100");
        user.setPhoneNumber(1L);
        user.setBranch("janedoe/featurebranch");
        Optional<user> ofResult = Optional.<user>of(user);
        when(this.userReposoritries.findByUsername(anyString())).thenReturn(ofResult);
        assertSame(user, this.userDetailsServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userReposoritries).findByUsername(anyString());
    }

    @Test
    public void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(this.userReposoritries.findByUsername(anyString())).thenReturn(Optional.<user>empty());
        assertThrows(UsernameNotFoundException.class, () -> this.userDetailsServiceImpl.loadUserByUsername("janedoe"));
        verify(this.userReposoritries).findByUsername(anyString());
    }
}

