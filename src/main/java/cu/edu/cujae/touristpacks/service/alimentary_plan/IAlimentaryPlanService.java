package cu.edu.cujae.touristpacks.service.alimentary_plan;

import java.util.List;

import cu.edu.cujae.touristpacks.dto.AlimentaryPlanDto;

public interface IAlimentaryPlanService {
    List<AlimentaryPlanDto> getAlimentaryPlans();

    AlimentaryPlanDto getAlimentaryPlanById(int alimentaryPlanId);

    AlimentaryPlanDto getAlimentaryPlanByName(String alimentaryPlanName);

    void createAlimentaryPlan(AlimentaryPlanDto alimentaryPlan);

    void updateAlimentaryPlan(AlimentaryPlanDto alimentaryPlan);

    void deleteAlimentaryPlan(int idAlimentaryPlan);
}
