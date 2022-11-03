package cu.edu.cujae.touristpacks.service.other_service_contract_diary_activity;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.OtherServiceContractDiaryActivityDto;

public interface IOtherServiceContractDiaryActivityService {
    List<OtherServiceContractDiaryActivityDto> getOtherServiceContractDiaryActivities();

    OtherServiceContractDiaryActivityDto getOtherServiceContractDiaryActivityById(
            int otherServiceContractDiaryActivityId);

    void createOtherServiceContractDiaryActivity(
            OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity);

    void updateOtherServiceContractDiaryActivity(
            OtherServiceContractDiaryActivityDto otherServiceContractDiaryActivity);

    void deleteOtherServiceContractDiaryActivity(int idOtherServiceContractDiaryActivity);
}
