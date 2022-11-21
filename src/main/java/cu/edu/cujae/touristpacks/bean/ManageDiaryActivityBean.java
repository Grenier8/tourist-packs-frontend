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

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;

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
        diaryActivities = service.getDiaryActivities();
    }

    public void openNew() {
        this.selectedDiaryActivity = new DiaryActivityDto();
    }

    public void openForEdit() {

    }

    public void saveDiaryActivity() {
        if (this.selectedDiaryActivity.getIdDiaryActivity() == 0) {
            service.createDiaryActivity(selectedDiaryActivity);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad diaria insertada"));
        } else {
            service.updateDiaryActivity(selectedDiaryActivity);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad diaria modificada"));
        }

        diaryActivities = service.getDiaryActivities();

        PrimeFaces.current().executeScript("PF('manageDiaryActivityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-diaryActivitys");

    }

    public void deleteDiaryActivity() {

        service.deleteDiaryActivity(selectedDiaryActivity.getIdDiaryActivity());
        this.selectedDiaryActivity = null;

        diaryActivities = service.getDiaryActivities();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Actividad diaria eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-diaryActivitys");

    }

    public List<DiaryActivityDto> getDiaryActivitys() {
        return this.diaryActivities;
    }

    public void setDiaryActivitys(List<DiaryActivityDto> diaryActivitys) {
        this.diaryActivities = diaryActivitys;
    }

    public DiaryActivityDto getSelectedDiaryActivity() {
        return this.selectedDiaryActivity;
    }

    public void setSelectedDiaryActivity(DiaryActivityDto selectedDiaryActivity) {
        this.selectedDiaryActivity = selectedDiaryActivity;
    }

    public IDiaryActivityService getService() {
        return this.service;
    }

    public void setService(IDiaryActivityService service) {
        this.service = service;
    }

}