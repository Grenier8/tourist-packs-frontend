package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageDiaryActivityBean {

    private List<DiaryActivityDto> diaryActivities;
    private DiaryActivityDto selectedDiaryActivity;

    @Autowired
    private IDiaryActivityService service;

    public ManageDiaryActivityBean() {

    }

    @PostConstruct
    public void init() {
        diaryActivities = diaryActivities == null ? service.getDiaryActivities() : diaryActivities;
    }

    public void openNew() {
        this.selectedDiaryActivity = new DiaryActivityDto();
    }

    public void openForEdit() {

    }

    public void saveDiaryActivity() {
        if (this.selectedDiaryActivity.getIdDiaryActivity() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedDiaryActivity.setIdDiaryActivity(r);
            this.diaryActivities.add(this.selectedDiaryActivity);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageDiaryActivityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-diaryActivities");

    }

    public void deleteDiaryActivity() {

        this.diaryActivities.remove(this.selectedDiaryActivity);
        this.selectedDiaryActivity = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-diaryActivities");

    }

    public List<DiaryActivityDto> getdiaryActivities() {
        return diaryActivities;
    }

    public void setdiaryActivities(List<DiaryActivityDto> diaryActivities) {
        this.diaryActivities = diaryActivities;
    }

    public DiaryActivityDto getSelectedDiaryActivity() {
        return selectedDiaryActivity;
    }

    public void setSelectedDiaryActivity(DiaryActivityDto selectedDiaryActivity) {
        this.selectedDiaryActivity = selectedDiaryActivity;
    }

    public IDiaryActivityService getService() {
        return service;
    }

    public void setService(IDiaryActivityService service) {
        this.service = service;
    }
}
