package by.yemelyanenko.MoneyMap.mapper;

import by.yemelyanenko.MoneyMap.DTO.UserDTO;
import by.yemelyanenko.MoneyMap.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = BaseMapper.class)
public interface UserMapper{

    @Mapping( target = "isActive", constant = "true")
    @Mapping (target = "role", expression = "java(by.yemelyanenko.MoneyMap.enums.UserRole.USER)")
    @Mapping (target = "createdAt", expression = "java(java.time.LocalDate.now())")
    UserDTO toDto (User user);

    User toEntity(UserDTO userDTO);

    List<UserDTO> toDtos(Iterable<User> list);

    List<User> toEntities(Iterable<UserDTO> list);

    User merge(@MappingTarget User entity, UserDTO dto);

}
