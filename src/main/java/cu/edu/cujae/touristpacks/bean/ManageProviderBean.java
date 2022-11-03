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

    @PostConstruct
    public void init() {
        providers = providers == null ? service.getProviders() : providers;
    }

    public void openNew() {
        this.selectedProvider = new ProviderDto();
    }

    public void openForEdit() {

    }

    public void saveProvider() {
        if (this.selectedProvider.getIdProvider() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedProvider.setIdProvider(r);
            this.providers.add(this.selectedProvider);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor insertado"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor modificado"));
        }

        PrimeFaces.current().executeScript("PF('manageProviderDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-providers");

    }

    public void deleteProvider() {

        this.providers.remove(this.selectedProvider);
        this.selectedProvider = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-providers");

    }

    public List<ProviderDto> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderDto> providers) {
        this.providers = providers;
    }

    public ProviderDto getSelectedProvider() {
        return selectedProvider;
    }

    public void setSelectedProvider(ProviderDto selectedProvider) {
        this.selectedProvider = selectedProvider;
    }

    public IProviderService getService() {
        return service;
    }

    public void setService(IProviderService service) {
        this.service = service;
    }
}
