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
import cu.edu.cujae.touristpacks.dto.OtherServiceContractDto;
import cu.edu.cujae.touristpacks.service.diary_activity.IDiaryActivityService;
import cu.edu.cujae.touristpacks.service.other_service_contract.IOtherServiceContractService;
import cu.edu.cujae.touristpacks.service.province.IProvinceService;
import cu.edu.cujae.touristpacks.service.service_type.IServiceTypeService;
import cu.edu.cujae.touristpacks.utils.JsfUtils;

@Component
@ManagedBean
@ViewScoped
public class ManageReport1121OtherServiceContractBean {

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

    public ManageReport1121OtherServiceContractBean() {

    }

    public String getDiaryActivitiesNames(OtherServiceContractDto otherServiceContract) {
        String names = "";
        for (DiaryActivityDto diaryActivity : otherServiceContract.getDiaryActivities()) {
            names += diaryActivity.getDiaryActivityName() + ",";
        }
        return names.length() > 0 ? names.substring(0, names.length() - 1) : names;
    }

    public List<OtherServiceContractDto> getOtherServiceContracts() {
        otherServiceContracts = service.get1121OtherServiceContracts();
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