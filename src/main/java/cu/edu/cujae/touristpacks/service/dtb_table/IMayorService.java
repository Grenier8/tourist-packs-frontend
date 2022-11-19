package cu.edu.cujae.touristpacks.service.dtb_table;

import cu.edu.cujae.touristpacks.dto.MayorDto;

import java.util.List;

public interface IMayorService {
    List<MayorDto> getMayors();

    MayorDto getMayorById(int idMayor);

    MayorDto getMayorByName(String minorName);

    void createMayor(MayorDto minor);

    void updateMayor(MayorDto minor);

    void deleteMayor(int idMayor);
}
