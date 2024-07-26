package th.mfu.dto.mapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import th.mfu.domain.Customer;
import th.mfu.domain.SaleOrder;

import th.mfu.dto.SaleOrderDTO;






@Mapper(componentModel="spring")
public interface SaleOrderMapper {

    // map from DTO to Entity
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSaleOrderFromDto(SaleOrderDTO dto, @MappingTarget Customer entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public static void updateSaleOrderFromEntity(SaleOrder cust,@MappingTarget SaleOrderDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSaleOrderFromEntity'");
    }

}



