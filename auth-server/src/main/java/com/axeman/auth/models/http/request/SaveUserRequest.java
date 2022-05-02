package com.axeman.auth.models.http.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRequest {
    String username;
    String primaryPhone;
    String primaryEmail;
    String password;
    String mfaEnabledFlag;
    String mfaMethod;
    String mfaId;
    String profileImagePath;
}
