package com.axeman.auth.entities;

import com.axeman.auth.models.MfaType;
import com.axeman.auth.models.UserAccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "axe_auth_schema")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    Long userid;

    @Column(name = "username", nullable = false, insertable = true, updatable = true, unique = true)
    String username;

    @Column(name = "primary_phone", unique = true)
    String primaryPhone;

    @Column(name = "primary_email", unique = true)
    String primaryEmail;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "password_creation_date")
    Date passwordCreationDate;

    @Column(name = "status")
    UserAccountStatus status;

    @Column(name = "mfa_enabled_flag")
    Boolean mfaEnabledFlag;

    @Column(name = "mfa_method")
    MfaType mfaMethod;

    @Column(name = "mfa_id")
    String mfaId;

    @Column(name = "salt", nullable = false)
    String salt;

    @Column(name = "profile_image_path")
    String profileImagePath;
}
