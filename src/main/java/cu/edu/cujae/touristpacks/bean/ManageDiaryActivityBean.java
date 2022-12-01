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
import cu.edu.cujae.touristpacks.utils.JsfUtils;

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

            JsfUtils.addInfoMessageFromBundle("message_inserted_diary_activity");
        } else {
            service.updateDiaryActivity(selectedDiaryActivity);

            JsfUtils.addInfoMessageFromBundle("message_updated_diary_activity");
        }

        diaryActivities = service.getDiaryActivities();

        PrimeFaces.current().executeScript("PF('manageDiaryActivityDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-diaryActivities");

    }

    public void deleteDiaryActivity() {

        service.deleteDiaryActivity(selectedDiaryActivity.getIdDiaryActivity());
        this.selectedDiaryActivity = null;

        diaryActivities = service.getDiaryActivities();

        JsfUtils.addInfoMessageFromBundle("message_deleted_diary_activity");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-diaryActivities");

    }

    public List<DiaryActivityDto> getDiaryActivities() {
        return this.diaryActivities;
    }

    public void setDiaryActivities(List<DiaryActivityDto> diaryActivities) {
        this.diaryActivities = diaryActivities;
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