package com.Alphalyte.Jwt_Admin_dashboard.payload.Response;


import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Client;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Note;
import com.Alphalyte.Jwt_Admin_dashboard.Model.CRM.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CRMResponse {
    private Client client;
    private Note note;
    private Project project;
}
