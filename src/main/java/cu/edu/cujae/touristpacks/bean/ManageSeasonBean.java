package cu.edu.cujae.touristpacks.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import cu.edu.cujae.touristpacks.service.season.ISeasonService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.SeasonDto;

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
        seasons = seasons == null ? service.getSeasons() : seasons;
    }

    public void openNew() {
        this.selectedSeason = new SeasonDto();
    }

    public void openForEdit() {

    }

    public void saveSeason() {
        if (this.selectedSeason.getIdSeason() == 0) {
            int r = (int) (Math.random() * 10000);

            this.selectedSeason.setIdSeason(r);
            this.seasons.add(this.selectedSeason);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Temporada insertada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Temporada modificada"));
        }

        PrimeFaces.current().executeScript("PF('manageSeasonDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-seasons");

    }

    public void deleteSeason() {

        this.seasons.remove(this.selectedSeason);
        this.selectedSeason = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Temporada eliminada"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-seasons");

    }

    public List<SeasonDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonDto> seasons) {
        this.seasons = seasons;
    }

    public SeasonDto getSelectedSeason() {
        return selectedSeason;
    }

    public void setSelectedSeason(SeasonDto selectedSeason) {
        this.selectedSeason = selectedSeason;
    }

    public ISeasonService getService() {
        return service;
    }

    public void setService(ISeasonService service) {
        this.service = service;
    }
}
