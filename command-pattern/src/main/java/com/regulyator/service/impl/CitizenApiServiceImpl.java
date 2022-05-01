package com.regulyator.service.impl;

import com.regulyator.dto.CitizenshipDto;
import com.regulyator.dto.HousingDto;
import com.regulyator.dto.request.CreateCitizenRequestDto;
import com.regulyator.dto.response.CreateCitizenResponseDto;
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
import org.springframework.util.CollectionUtils;

import java.util.List;

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
    public CreateCitizenResponseDto createCitizen(@NonNull CreateCitizenRequestDto createCitizenRequestDto) {
        var citizen = citizenMapper.fromDto(createCitizenRequestDto.getCitizen());

        CommandRunner<StorageEntity> entityCommandRunner = new CommandRunnerImpl();

        try {
            var createdCitizen = createCitizen(createCitizenRequestDto, citizen, entityCommandRunner);
            return CreateCitizenResponseDto.builder()
                    .idCitizenCreated(createdCitizen.getId())
                    .success(true)
                    .build();
        } catch (CitizenCreationException ex) {
            while (entityCommandRunner.undo()) {
                entityCommandRunner.undo();
            }

            return CreateCitizenResponseDto.builder()
                    .success(false)
                    .build();
        }

    }

    private StorageEntity createCitizen(CreateCitizenRequestDto createCitizenRequestDto,
                                        Citizen citizen,
                                        CommandRunner<StorageEntity> entityCommandRunner) {
        var createdCitizen = entityCommandRunner
                .execute(new CitizenSaveCommand(citizenService, citizen));
        if (createdCitizen.getId() != 0) {
            var idCitizen = createdCitizen.getId();
            createCitizenShips(createCitizenRequestDto.getCitizenShips(), entityCommandRunner, idCitizen);
            createHousings(createCitizenRequestDto.getHousings(), entityCommandRunner, idCitizen);
        }
        return createdCitizen;
    }


    private void createCitizenShips(List<CitizenshipDto> citizenShipsDto,
                                    CommandRunner<StorageEntity> entityCommandRunner,
                                    long idCitizen) {
        if (!CollectionUtils.isEmpty(citizenShipsDto)) {
            citizenShipsDto.stream()
                    .map(citizenshipMapper::fromDto)
                    .forEach(citizenship -> {
                        citizenship.setIdCitizen(idCitizen);
                        entityCommandRunner.execute(new CitizenshipSaveCommand(citizenshipService, citizenship));
                    });
        }

    }

    private void createHousings(List<HousingDto> housingsDto,
                                CommandRunner<StorageEntity> entityCommandRunner,
                                long idCitizen) {
        if (!CollectionUtils.isEmpty(housingsDto)) {
            housingsDto.stream()
                    .map(housingMapper::fromDto).forEach(housing -> {
                        housing.setIdCitizen(idCitizen);
                        entityCommandRunner.execute(new HousingSaveCommand(housingService, housing));
                    });
        }
    }
}
