package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.service.alimentary_plan.IAlimentaryPlanService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageAlimentaryPlanBean {

    private List<AlimentaryPlanDto> alimentaryPlans;
    private AlimentaryPlanDto selectedAlimentaryPlan;

    @Autowired
    private IAlimentaryPlanService service;

    public ManageAlimentaryPlanBean() {

    }

    @PostConstruct
    public void init() {
        alimentaryPlans = alimentaryPlans == null ? service.getAlimentaryPlans() : alimentaryPlans;
    }

    public void openNew() {
        this.selectedAlimentaryPlan = new AlimentaryPlanDto();
    }

    public void openForEdit() {

    }

    public void saveAlimentaryPlan() {
        if (this.selectedAlimentaryPlan.getIdAlimentaryPlan() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedAlimentaryPlan.setIdAlimentaryPlan(r);
            this.alimentaryPlans.add(this.selectedAlimentaryPlan);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageAlimentaryPlanDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-alimentaryPlans");

    }

    public void deleteAlimentaryPlan() {

        this.alimentaryPlans.remove(this.selectedAlimentaryPlan);
        this.selectedAlimentaryPlan = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-alimentaryPlans");

    }

    public List<AlimentaryPlanDto> getAlimentaryPlans() {
        return alimentaryPlans;
    }

    public void setAlimentaryPlans(List<AlimentaryPlanDto> alimentaryPlans) {
        this.alimentaryPlans = alimentaryPlans;
    }

    public AlimentaryPlanDto getSelectedAlimentaryPlan() {
        return selectedAlimentaryPlan;
    }

    public void setSelectedAlimentaryPlan(AlimentaryPlanDto selectedAlimentaryPlan) {
        this.selectedAlimentaryPlan = selectedAlimentaryPlan;
    }

    public IAlimentaryPlanService getService() {
        return service;
    }

    public void setService(IAlimentaryPlanService service) {
        this.service = service;
    }
}
