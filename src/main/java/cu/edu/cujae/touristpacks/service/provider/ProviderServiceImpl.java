package cu.edu.cujae.touristpacks.service.provider;

import cu.edu.cujae.touristpacks.dto.ProviderDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImpl implements IProviderService {

    @Override
    public List<ProviderDto> getProviders() {
        List<ProviderDto> providers = new ArrayList<>();
        providers.add(new ProviderDto(1, "Gaviota"));
        providers.add(new ProviderDto(2, "Panataxi"));
        return providers;
    }

    @Override
    public ProviderDto getProviderById(int providerId) {
        return getProviders().stream().filter(r -> r.getIdProvider() == providerId).findFirst().get();
    }

    @Override
    public ProviderDto getProviderByName(String providerName) {
        return getProviders().stream().filter(r -> r.getProviderName().equals(providerName)).findFirst().get();
    }

    @Override
    public void createProvider(ProviderDto provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateProvider(ProviderDto provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteProvider(int id) {
        // TODO Auto-generated method stub

    }
}
