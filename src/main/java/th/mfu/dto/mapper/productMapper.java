package th.mfu.dto.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.product;
import th.mfu.dto.productDTO;

@Mapper(componentModel="spring")
public interface productMapper {

    // map from DTO to Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateproductFromDto(productDTO dto, @MappingTarget product entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateproductFromEntity(product entity,@MappingTarget productDTO dto);

}
