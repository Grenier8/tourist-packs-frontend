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

import cu.edu.cujae.touristpacks.dto.DiaryActivityTouristPackDto;
import cu.edu.cujae.touristpacks.service.diary_activity_tourist_pack.IDiaryActivityTouristPackService;

@Component
@ManagedBean
@ViewScoped
public class ManageDiaryActivityTouristPackBean {

    private List<DiaryActivityTouristPackDto> diaryActivityTouristPacks;
    private DiaryActivityTouristPackDto selectedDiaryActivityTouristPack;

    @Autowired
    private IDiaryActivityTouristPackService service;

    public ManageDiaryActivityTouristPackBean() {

    }

    @PostConstruct
    public void init() {
        diaryActivityTouristPacks = service.getDiaryActivityTouristPacks();
    }

    public void openNew() {
        this.selectedDiaryActivityTouristPack = new DiaryActivityTouristPackDto();
    }

    public void openForEdit() {

    }

    public void saveDiaryActivityTouristPack() {
        if (this.selectedDiaryActivityTouristPack.getIdDiaryActivityTouristPack() == 0) {
            service.createDiaryActivityTouristPack(selectedDiaryActivityTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateDiaryActivityTouristPack(selectedDiaryActivityTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        diaryActivityTouristPacks = service.getDiaryActivityTouristPacks();

        PrimeFaces.current().executeScript("PF('manageDiaryActivityTouristPackDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-diaryActivityTouristPacks");

    }

    public void deleteDiaryActivityTouristPack() {

        service.deleteDiaryActivityTouristPack(selectedDiaryActivityTouristPack.getIdDiaryActivityTouristPack());
        this.selectedDiaryActivityTouristPack = null;

        diaryActivityTouristPacks = service.getDiaryActivityTouristPacks();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-diaryActivityTouristPacks");

    }

    public List<DiaryActivityTouristPackDto> getDiaryActivityTouristPacks() {
        return this.diaryActivityTouristPacks;
    }

    public void setDiaryActivityTouristPacks(List<DiaryActivityTouristPackDto> diaryActivityTouristPacks) {
        this.diaryActivityTouristPacks = diaryActivityTouristPacks;
    }

    public DiaryActivityTouristPackDto getSelectedDiaryActivityTouristPack() {
        return this.selectedDiaryActivityTouristPack;
    }

    public void setSelectedDiaryActivityTouristPack(DiaryActivityTouristPackDto selectedDiaryActivityTouristPack) {
        this.selectedDiaryActivityTouristPack = selectedDiaryActivityTouristPack;
    }

    public IDiaryActivityTouristPackService getService() {
        return this.service;
    }

    public void setService(IDiaryActivityTouristPackService service) {
        this.service = service;
    }

}