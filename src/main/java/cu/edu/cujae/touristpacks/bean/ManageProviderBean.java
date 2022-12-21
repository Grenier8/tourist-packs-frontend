package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.ProviderDto;
import cu.edu.cujae.touristpacks.service.provider.IProviderService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageProviderBean {

    private List<ProviderDto> providers;
    private ProviderDto selectedProvider;

    @Autowired
    private IProviderService service;

    public ManageProviderBean() {

    }

    public void openNew() {
        this.selectedProvider = new ProviderDto();
    }

    public void openForEdit() {

    }

    public void saveProvider() {
        if (this.selectedProvider.getIdProvider() == 0) {
            service.createProvider(selectedProvider);

            JsfUtils.addInfoMessageFromBundle("message_inserted_provider");
        } else {
            service.updateProvider(selectedProvider);

            JsfUtils.addInfoMessageFromBundle("message_updated_provider");
        }

        providers = service.getProviders();

        PrimeFaces.current().executeScript("PF('manageProviderDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-providers");

    }

    public void deleteProvider() {

        service.deleteProvider(selectedProvider.getIdProvider());
        this.selectedProvider = null;

        providers = service.getProviders();

        JsfUtils.addInfoMessageFromBundle("message_deleted_provider");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-providers");

    }

    public List<ProviderDto> getProviders() {
        providers = service.getProviders();
        return this.providers;
    }

    public void setProviders(List<ProviderDto> providers) {
        this.providers = providers;
    }

    public ProviderDto getSelectedProvider() {
        return this.selectedProvider;
    }

    public void setSelectedProvider(ProviderDto selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public IProviderService getService() {
        return this.service;
    }

    public void setService(IProviderService service) {
        this.service = service;
    }
}
