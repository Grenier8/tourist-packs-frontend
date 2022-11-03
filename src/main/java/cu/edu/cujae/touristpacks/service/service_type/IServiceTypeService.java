package cu.edu.cujae.touristpacks.service.service_type;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.ServiceTypeDto;

public interface IServiceTypeService {
    List<ServiceTypeDto> getServiceTypes();

    ServiceTypeDto getServiceTypeById(int serviceTypeId);

    ServiceTypeDto getServiceTypeByName(String serviceTypeName);

    void createServiceType(ServiceTypeDto serviceType);

    void updateServiceType(ServiceTypeDto serviceType);

    void deleteServiceType(int idServiceType);
}
