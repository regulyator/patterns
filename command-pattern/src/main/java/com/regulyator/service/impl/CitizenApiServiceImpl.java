package com.regulyator.service.impl;

import com.regulyator.dto.request.CreateCitizenRequestDto;
import com.regulyator.entity.Citizen;
import com.regulyator.entity.Citizenship;
import com.regulyator.entity.Housing;
import com.regulyator.entity.StorageEntity;
import com.regulyator.exception.CitizenCreationException;
import com.regulyator.mapper.CitizenMapper;
import com.regulyator.mapper.CitizenshipMapper;
import com.regulyator.mapper.HousingMapper;
import com.regulyator.service.CitizenApiService;
import com.regulyator.service.EntityService;
import com.regulyator.service.command.CommandRunner;
import com.regulyator.service.command.impl.CitizenSaveCommand;
import com.regulyator.service.command.impl.CitizenshipSaveCommand;
import com.regulyator.service.command.impl.CommandRunnerImpl;
import com.regulyator.service.command.impl.HousingSaveCommand;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CitizenApiServiceImpl implements CitizenApiService {
    private final EntityService<Citizen, Long> citizenService;
    private final EntityService<Citizenship, Long> citizenshipService;
    private final EntityService<Housing, Long> housingService;
    private final CitizenMapper citizenMapper;
    private final CitizenshipMapper citizenshipMapper;
    private final HousingMapper housingMapper;

    @Override
    public boolean createCitizen(@NonNull CreateCitizenRequestDto createCitizenRequestDto) {
        var citizen = citizenMapper.fromDto(createCitizenRequestDto.getCitizen());
        var citizenShips = createCitizenRequestDto.getCitizenShips().stream()
                .map(citizenshipMapper::fromDto)
                .collect(Collectors.toList());
        var housings = createCitizenRequestDto.getHousings().stream()
                .map(housingMapper::fromDto)
                .collect(Collectors.toList());

        CommandRunner<StorageEntity> entityCommandRunner = new CommandRunnerImpl();

        try {
            var createdCitizen = entityCommandRunner
                    .execute(new CitizenSaveCommand(citizenService, citizen));
            if (createdCitizen.getId() != 0) {
                var idCitizen = createdCitizen.getId();
                citizenShips.forEach(citizenship -> {
                    citizenship.setIdCitizen(idCitizen);
                    entityCommandRunner.execute(new CitizenshipSaveCommand(citizenshipService, citizenship));
                });
                housings.forEach(housing -> {
                    housing.setIdCitizen(idCitizen);
                    entityCommandRunner.execute(new HousingSaveCommand(housingService, housing));
                });
            }
            return true;
        } catch (CitizenCreationException ex) {
            while (entityCommandRunner.undo()) {
                entityCommandRunner.undo();
            }
            return false;
        }

    }
}
