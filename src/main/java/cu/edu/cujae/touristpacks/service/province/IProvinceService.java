package cu.edu.cujae.touristpacks.service.province;

import cu.edu.cujae.touristpacks.dto.ProvinceDto;

import java.util.List;

public interface IProvinceService {
    List<ProvinceDto> getProvinces();

    ProvinceDto getProvinceById(int provinceId);

    ProvinceDto getProvinceByName(String provinceName);

    void createProvince(ProvinceDto province);

    void updateProvince(ProvinceDto province);

    void deleteProvince(int id);
}
