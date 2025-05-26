package by.yemelyanenko.MoneyMap.mapper;


import by.yemelyanenko.MoneyMap.DTO.TransactionDTO;
import by.yemelyanenko.MoneyMap.entity.Transaction;
import by.yemelyanenko.MoneyMap.mapper.resolver.CategoryResolver;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = BaseMapper.class,uses = {CategoryResolver.class})
public interface TransactionMapper {

    @Mapping(target = "categoryId", source = "category.id")
    TransactionDTO toDto (Transaction transaction);

    @Mapping(target = "category",source = "categoryId")
    Transaction toEntity(TransactionDTO transactionDTO);

    List<TransactionDTO> toDtos(Iterable<Transaction> list);

    List<Transaction> toEntities(Iterable<TransactionDTO> list);

    Transaction merge(@MappingTarget Transaction entity, TransactionDTO dto);
}
