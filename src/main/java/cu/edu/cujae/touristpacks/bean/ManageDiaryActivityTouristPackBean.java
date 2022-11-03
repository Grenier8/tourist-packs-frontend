package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.dto.DiaryActivityTouristPackDto;
import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;
import cu.edu.cujae.touristpacks.service.diary_activity_tourist_pack.IDiaryActivityTouristPackService;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean
@ViewScoped
public class ManageDiaryActivityTouristPackBean {

    private List<DiaryActivityTouristPackDto> diaryActivityTouristPacks;
    private DiaryActivityTouristPackDto selectedDiaryActivityTouristPack;

    private String selectedTouristPackName;
    private String selectedDiaryActivityName;

    @Autowired
    private IDiaryActivityTouristPackService service;

    @Autowired
    private ITouristPackService touristPackService;

    @Autowired
    private IDiaryActivityService diaryActivityService;

    public ManageDiaryActivityTouristPackBean() {

    }

    @PostConstruct
    public void init() {
        diaryActivityTouristPacks = diaryActivityTouristPacks == null ? service.getDiaryActivityTouristPacks()
                : diaryActivityTouristPacks;
    }

    public void openNew() {
        this.selectedDiaryActivityTouristPack = new DiaryActivityTouristPackDto();
    }

    public void openForEdit() {

    }

    public void saveDiaryActivityTouristPack() {
        if (this.selectedDiaryActivityTouristPack.getIdDiaryActivityTouristPack() == 0) {
            int r = (int) (Math.random() * 10000);
            this.selectedDiaryActivityTouristPack.setIdDiaryActivityTouristPack(r);

            TouristPackDto touristPack = touristPackService.getTouristPackByName(selectedTouristPackName);
            this.selectedDiaryActivityTouristPack.setTouristPack(touristPack);

            DiaryActivityDto diaryActivity = diaryActivityService.getDiaryActivityByName(selectedDiaryActivityName);
            this.selectedDiaryActivityTouristPack.setDiaryActivity(diaryActivity);

            this.diaryActivityTouristPacks.add(this.selectedDiaryActivityTouristPack);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageDiaryActivityTouristPackDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-diaryActivityTouristPack");

    }

    public void deleteDiaryActivityTouristPack() {

        this.diaryActivityTouristPacks.remove(this.selectedDiaryActivityTouristPack);
        this.selectedDiaryActivityTouristPack = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cambiar eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-diaryActivityTouristPack");

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

    public String getSelectedTouristPackName() {
        return this.selectedTouristPackName;
    }

    public void setSelectedTouristPackName(String selectedTouristPackName) {
        this.selectedTouristPackName = selectedTouristPackName;
    }

    public String getSelectedDiaryActivityName() {
        return this.selectedDiaryActivityName;
    }

    public void setSelectedDiaryActivityName(String selectedDiaryActivityName) {
        this.selectedDiaryActivityName = selectedDiaryActivityName;
    }

    public IDiaryActivityTouristPackService getService() {
        return this.service;
    }

    public void setService(IDiaryActivityTouristPackService service) {
        this.service = service;
    }

    public ITouristPackService getTouristPackService() {
        return this.touristPackService;
    }

    public void setTouristPackService(ITouristPackService touristPackService) {
        this.touristPackService = touristPackService;
    }

    public IDiaryActivityService getDiaryActivityService() {
        return this.diaryActivityService;
    }

    public void setDiaryActivityService(IDiaryActivityService diaryActivityService) {
        this.diaryActivityService = diaryActivityService;
    }

}
