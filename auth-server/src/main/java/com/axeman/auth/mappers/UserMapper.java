package com.axeman.auth.mappers;

import com.axeman.auth.entities.User;
import com.axeman.auth.models.http.request.SaveUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(ignore = true, target = "password")
    public User userRequestToUserEntity(SaveUserRequest userRequest);

}
