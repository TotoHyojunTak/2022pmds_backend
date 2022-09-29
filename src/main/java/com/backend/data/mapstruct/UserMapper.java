package com.backend.data.mapstruct;

import com.backend.data.dto.response.UserDTO;
import com.backend.data.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // 변수명이 다를 경우 / source = Entity,  target = DTO or expression= java 표현법
//    @Mapping(target="mcpCd", expression="java(String.valueOf(entity.getMcpCd()))")
//    @Mapping(target="mcpNm", expression="java(entity.getMcpNm())")
//    @Mapping(target="useYn", expression="java(entity.getUseYn())")
//    @Mapping(target="insDt", expression="java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
//    @Mapping(target="updDt", expression="java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
    UserDTO toDto(UserEntity entity);


//    @Mapping(target="mcpCd", expression="java(Integer.valueOf(dto.getMcpCd()))")
//    @Mapping(target="mcpNm", expression="java(dto.getMcpNm())")
//    @Mapping(target="useYn", expression="java(dto.getUseYn())")
//    @Mapping(target="insDt", expression="java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
//    @Mapping(target="updDt", expression="java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
    UserEntity toEntity(UserDTO dto);


	// toDto, toEntity와 동일한 방법으로 리스트 매핑
    List<UserDTO> toDtoList(List<UserEntity> entityList);

	List<UserEntity> toEntityList(List<UserDTO> dtoList);


}
