package com.postech.fastfood.application.mapper;

import com.postech.fastfood.adapter.driven.persistence.entity.UserEntity;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerCpfRequest;
import com.postech.fastfood.adapter.driver.controller.dto.request.CustomerEmailRequest;
import com.postech.fastfood.core.domain.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toDomain(UserEntity userEntity){

        if (userEntity == null) {
            return null;
        }

        return new User.Builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .cpf(userEntity.getCpf())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();

    }
    public static UserEntity toEntity(User user){

        if (user == null) {
            return null;
        }

        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .cpf(user.getCpf())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static User toDomain(CustomerCpfRequest user) {
        if (user == null) {
            return null;
        }
        return new User.Builder().cpf(user.cpf()).build();
    }

    public static User toDomain(@Valid CustomerEmailRequest user) {
        if (user == null) {
            return null;
        }
        return new User.Builder()
                .email(user.email())
                .name(user.name())
                .build();
    }


}
