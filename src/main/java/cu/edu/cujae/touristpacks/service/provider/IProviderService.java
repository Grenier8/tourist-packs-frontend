package cu.edu.cujae.touristpacks.service.provider;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.ProviderDto;

public interface IProviderService {
    List<ProviderDto> getProviders();

    ProviderDto getProviderById(int providerId);

    ProviderDto getProviderByName(String providerName);

    void createProvider(ProviderDto provider);

    void updateProvider(ProviderDto provider);

    void deleteProvider(int id);
}
