package cu.edu.cujae.touristpacks.bean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cu.edu.cujae.touristpacks.dto.DiaryActivityDto;
import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;
import cu.edu.cujae.touristpacks.service.other_service_contract.IOtherServiceContractService;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;
import cu.edu.cujae.touristpacks.service.service_type.IServiceTypeService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageOtherServiceContractBean {

    private List<OtherServiceContractDto> otherServiceContracts;
    private OtherServiceContractDto selectedOtherServiceContract;

    private String selectedServiceTypeName;
    private String selectedProvinceName;
    private List<String> selectedDiaryActivitiesNames;

    @Autowired
    private IOtherServiceContractService service;

    @Autowired
    private IServiceTypeService serviceTypeService;

    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IDiaryActivityService diaryActivityService;

    public ManageOtherServiceContractBean() {

    }

    public void openNew() {
        this.selectedOtherServiceContract = new OtherServiceContractDto();
    }

    public void openForEdit() {

    }

    public void saveOtherServiceContract() {
        selectedOtherServiceContract.setServiceType(serviceTypeService.getServiceTypeByName(selectedServiceTypeName));
        selectedOtherServiceContract.setProvince(provinceService.getProvinceByName(selectedProvinceName));

        List<DiaryActivityDto> diaryActivities = new ArrayList<>();
        for (String name : selectedDiaryActivitiesNames) {
            diaryActivities.add(diaryActivityService.getDiaryActivityByName(name));
        }
        selectedOtherServiceContract.setDiaryActivities(diaryActivities);

        if (this.selectedOtherServiceContract.getIdOtherServiceContract() == 0) {
            service.createOtherServiceContract(selectedOtherServiceContract);

            JsfUtils.addInfoMessageFromBundle("message_inserted_other_service_contract");
        } else {
            service.updateOtherServiceContract(selectedOtherServiceContract);

            JsfUtils.addInfoMessageFromBundle("message_updated_other_service_contract");
        }

        otherServiceContracts = service.getOtherServiceContracts();

        PrimeFaces.current().executeScript("PF('manageOtherServiceContractDialog').hide()");
        PrimeFaces.current().ajax().update("form:dt-otherServiceContracts");

    }

    public void deleteOtherServiceContract() {

        service.deleteOtherServiceContract(selectedOtherServiceContract.getIdOtherServiceContract());
        this.selectedOtherServiceContract = null;

        otherServiceContracts = service.getOtherServiceContracts();

        JsfUtils.addInfoMessageFromBundle("message_deleted_other_service_contract");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-otherServiceContracts");

    }

    public String getDiaryActivitiesNames(OtherServiceContractDto otherServiceContract) {
        String names = "";
        for (DiaryActivityDto diaryActivity : otherServiceContract.getDiaryActivities()) {
            names += diaryActivity.getDiaryActivityName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
    }

    public List<OtherServiceContractDto> getOtherServiceContracts() {
        return this.otherServiceContracts;
    }

    public void setOtherServiceContracts(List<OtherServiceContractDto> otherServiceContracts) {
        this.otherServiceContracts = otherServiceContracts;
    }

    public OtherServiceContractDto getSelectedOtherServiceContract() {
        return this.selectedOtherServiceContract;
    }

    public void setSelectedOtherServiceContract(OtherServiceContractDto selectedOtherServiceContract) {
        this.selectedOtherServiceContract = selectedOtherServiceContract;
    }

    public IOtherServiceContractService getService() {
        return this.service;
    }

    public void setService(IOtherServiceContractService service) {
        this.service = service;
    }

    public String getSelectedServiceTypeName() {
        return this.selectedServiceTypeName;
    }

    public void setSelectedServiceTypeName(String selectedServiceTypeName) {
        this.selectedServiceTypeName = selectedServiceTypeName;
    }

    public String getSelectedProvinceName() {
        return this.selectedProvinceName;
    }

    public void setSelectedProvinceName(String selectedProvinceName) {
        this.selectedProvinceName = selectedProvinceName;
    }

    public List<String> getSelectedDiaryActivitiesNames() {
        return this.selectedDiaryActivitiesNames;
    }

    public void setSelectedDiaryActivitiesNames(List<String> selectedDiaryActivitiesNames) {
        this.selectedDiaryActivitiesNames = selectedDiaryActivitiesNames;
    }

    public IServiceTypeService getServiceTypeService() {
        return this.serviceTypeService;
    }

    public void setServiceTypeService(IServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

    public IProvinceService getProvinceService() {
        return this.provinceService;
    }

    public void setProvinceService(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    public IDiaryActivityService getDiaryActivityService() {
        return this.diaryActivityService;
    }

    public void setDiaryActivityService(IDiaryActivityService diaryActivityService) {
        this.diaryActivityService = diaryActivityService;
    }

}