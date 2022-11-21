package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.AlimentaryPlanDto;
import cu.edu.cujae.touristpacks.service.alimentary_plan.IAlimentaryPlanService;

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
        alimentaryPlans = service.getAlimentaryPlans();
    }

    public void openNew() {
        this.selectedAlimentaryPlan = new AlimentaryPlanDto();
    }

    public void openForEdit() {

    }

    public void saveAlimentaryPlan() {
        if (this.selectedAlimentaryPlan.getIdAlimentaryPlan() == 0) {
            service.createAlimentaryPlan(selectedAlimentaryPlan);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plan alimenticio insertado"));
        } else {
            service.updateAlimentaryPlan(selectedAlimentaryPlan);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plan alimenticio modificado"));
        }

        alimentaryPlans = service.getAlimentaryPlans();

        PrimeFaces.current().executeScript("PF('manageAlimentaryPlanDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-alimentaryPlans");

    }

    public void deleteAlimentaryPlan() {

        service.deleteAlimentaryPlan(selectedAlimentaryPlan.getIdAlimentaryPlan());
        this.selectedAlimentaryPlan = null;

        alimentaryPlans = service.getAlimentaryPlans();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Plan alimenticio eliminado"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-alimentaryPlans");

    }

    public List<AlimentaryPlanDto> getAlimentaryPlans() {
        return this.alimentaryPlans;
    }

    public void setAlimentaryPlans(List<AlimentaryPlanDto> alimentaryPlan) {
        this.alimentaryPlans = alimentaryPlan;
    }

    public AlimentaryPlanDto getSelectedAlimentaryPlan() {
        return this.selectedAlimentaryPlan;
    }

    public void setSelectedAlimentaryPlan(AlimentaryPlanDto selectedAlimentaryPlan) {
        this.selectedAlimentaryPlan = selectedAlimentaryPlan;
    }

    public IAlimentaryPlanService getService() {
        return this.service;
    }

    public void setService(IAlimentaryPlanService service) {
        this.service = service;
    }

}