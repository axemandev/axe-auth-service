package com.axeman.auth.entities;

import com.axeman.auth.models.MfaType;
import com.axeman.auth.models.UserAccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class User {
    String username;
    String primaryPhone;
    String primaryEmail;
    String password;
    Date passwordCreationDate;
    UserAccountStatus status;
    Boolean mfaEnabledFlag;
    MfaType mfaMethod;
    String mfaId;
    String salt;
    String profileImagePath;
}
