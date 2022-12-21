package cu.edu.cujae.touristpacks.bean.reports;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.dto.TouristPackDto;
import cu.edu.cujae.touristpacks.dto.TransportServiceDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;
import cu.edu.cujae.touristpacks.service.hotel.IHotelService;
import cu.edu.cujae.touristpacks.service.room_plan_season.IRoomPlanSeasonService;
import cu.edu.cujae.touristpacks.service.tourist_pack.ITouristPackService;
import cu.edu.cujae.touristpacks.service.transport_service.ITransportServiceService;

@Component
@ManagedBean
@ViewScoped
public class ManageReportChristmasPackBean {

    private List<TouristPackDto> touristPacks;
    private TouristPackDto selectedTouristPack;

    private String selectedHotelName;
    private String selectedRoomPlanSeasonName;
    private List<String> selectedTransportServicesNames;
    private List<String> selectedDiaryActivitiesNames;

    @Autowired
    private IHotelService hotelService;

    @Autowired
    private IRoomPlanSeasonService roomPlanSeasonService;

    @Autowired
    private ITouristPackService service;

    @Autowired
    private ITransportServiceService transportServiceService;

    @Autowired
    private IDiaryActivityService diaryActivityService;

    public ManageReportChristmasPackBean() {

    }

    public String getTransportServicesNames(TouristPackDto touristPack) {
        String names = "";
        for (TransportServiceDto transportService : touristPack.getTransportServices()) {
            names += transportService.getTransportServiceName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
    }

    public String getDiaryActivitiesNames(TouristPackDto touristPack) {
        String names = "";
        for (DiaryActivityDto diaryActivity : touristPack.getDiaryActivities()) {
            names += diaryActivity.getDiaryActivityName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
    }

    public List<TouristPackDto> getTouristPacks() {
        touristPacks = service.getChristmasTouristPacks();
        return this.touristPacks;
    }

    public void setTouristPacks(List<TouristPackDto> touristPacks) {
        this.touristPacks = touristPacks;
    }

    public TouristPackDto getSelectedTouristPack() {
        return this.selectedTouristPack;
    }

    public void setSelectedTouristPack(TouristPackDto selectedTouristPack) {
        this.selectedTouristPack = selectedTouristPack;
    }

    public ITouristPackService getService() {
        return this.service;
    }

    public void setService(ITouristPackService service) {
        this.service = service;
    }

    public String getSelectedHotelName() {
        return this.selectedHotelName;
    }

    public void setSelectedHotelName(String selectedHotelName) {
        this.selectedHotelName = selectedHotelName;
    }

    public String getSelectedRoomPlanSeasonName() {
        return this.selectedRoomPlanSeasonName;
    }

    public void setSelectedRoomPlanSeasonName(String selectedRoomPlanSeasonName) {
        this.selectedRoomPlanSeasonName = selectedRoomPlanSeasonName;
    }

    public IHotelService getHotelService() {
        return this.hotelService;
    }

    public void setHotelService(IHotelService hotelService) {
        this.hotelService = hotelService;
    }

    public IRoomPlanSeasonService getRoomPlanSeasonService() {
        return this.roomPlanSeasonService;
    }

    public void setRoomPlanSeasonService(IRoomPlanSeasonService roomPlanSeasonService) {
        this.roomPlanSeasonService = roomPlanSeasonService;
    }

    public List<String> getSelectedTransportServicesNames() {
        return this.selectedTransportServicesNames;
    }

    public void setSelectedTransportServicesNames(List<String> selectedTransportServicesNames) {
        this.selectedTransportServicesNames = selectedTransportServicesNames;
    }

    public List<String> getSelectedDiaryActivitiesNames() {
        return this.selectedDiaryActivitiesNames;
    }

    public void setSelectedDiaryActivitiesNames(List<String> selectedDiaryActivitiesNames) {
        this.selectedDiaryActivitiesNames = selectedDiaryActivitiesNames;
    }

    public ITransportServiceService getTransportServiceService() {
        return this.transportServiceService;
    }

    public void setTransportServiceService(ITransportServiceService transportServiceService) {
        this.transportServiceService = transportServiceService;
    }

    public IDiaryActivityService getDiaryActivityService() {
        return this.diaryActivityService;
    }

    public void setDiaryActivityService(IDiaryActivityService diaryActivityService) {
        this.diaryActivityService = diaryActivityService;
    }

}