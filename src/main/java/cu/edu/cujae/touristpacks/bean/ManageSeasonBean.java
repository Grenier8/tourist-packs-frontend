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

import cu.edu.cujae.touristpacks.dto.SeasonDto;
import cu.edu.cujae.touristpacks.service.season.ISeasonService;

@Component
@ManagedBean
@ViewScoped
public class ManageSeasonBean {

    private List<SeasonDto> seasons;
    private SeasonDto selectedSeason;

    @Autowired
    private ISeasonService service;

    public ManageSeasonBean() {

    }

    @PostConstruct
    public void init() {
        seasons = service.getSeasons();
    }

    public void openNew() {
        this.selectedSeason = new SeasonDto();
    }

    public void openForEdit() {

    }

    public void saveSeason() {
        if (this.selectedSeason.getIdSeason() == 0) {
            service.createSeason(selectedSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo insertada"));
        } else {
            service.updateSeason(selectedSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo modificada"));
        }

        seasons = service.getSeasons();

        PrimeFaces.current().executeScript("PF('manageSeasonDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-seasons");

    }

    public void deleteSeason() {

        service.deleteSeason(selectedSeason.getIdSeason());
        this.selectedSeason = null;

        seasons = service.getSeasons();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Espanolo eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-seasons");

    }

    public List<SeasonDto> getSeasons() {
        return this.seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }

    public SeasonDto getSelectedSeason() {
        return this.selectedSeason;
    }

    public void setSelectedSeason(SeasonDto selectedSeason) {
        this.selectedSeason = selectedSeason;
    }

    public ISeasonService getService() {
        return this.service;
    }

    public void setService(ISeasonService service) {
        this.service = service;
    }

}