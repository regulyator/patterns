package com.regulyator.service.impl;

import com.regulyator.dto.CitizenDto;
import com.regulyator.dto.CitizenshipDto;
import com.regulyator.dto.HousingDto;
import com.regulyator.dto.request.CreateCitizenRequestDto;
import com.regulyator.repository.CitizenRepository;
import com.regulyator.repository.CitizenshipRepository;
import com.regulyator.repository.HousingRepository;
import com.regulyator.service.CitizenApiService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DisplayName("CitizenApiService should ")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CitizenApiServiceImplTest {

    private static final String FORBIDDEN_COUNTRY_CODE = "forbidden code";
    private static final String FORBIDDEN_HOUSING = "forbidden address";

    @Autowired
    private CitizenApiService citizenApiService;
    @Autowired
    private CitizenRepository citizenRepository;
    @Autowired
    private CitizenshipRepository citizenshipRepository;
    @Autowired
    private HousingRepository housingRepository;

    @Test
    @DisplayName("create citizen")
    void createCitizen() {
        var createCitizenRequest = getDataRequest();
        var result = citizenApiService.createCitizen(createCitizenRequest);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isTrue();

        var idCitizenCreated = result.getIdCitizenCreated();
        assertThat(idCitizenCreated).isPositive();
        assertThat(citizenRepository.existsById(idCitizenCreated)).isTrue();

        var citizenShipCreated = citizenshipRepository.findAll();
        assertThat(citizenShipCreated).hasSize(createCitizenRequest.getCitizenShips().size())
                .allMatch(citizenship -> citizenship.getIdCitizen() == idCitizenCreated);

        var housingCreated = housingRepository.findAll();
        assertThat(housingCreated).hasSize(createCitizenRequest.getHousings().size())
                .allMatch(housing -> housing.getIdCitizen() == idCitizenCreated);

    }

    @Test
    @DisplayName("not create citizen on case of forbidden country code")
    void notCreateCitizenForbiddenCountryCode() {
        var createCitizenRequest = getDataRequest();
        createCitizenRequest.getCitizenShips().add(CitizenshipDto.builder()
                .countryCode(FORBIDDEN_COUNTRY_CODE)
                .build());
        var result = citizenApiService.createCitizen(createCitizenRequest);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getIdCitizenCreated()).isZero();

        assertThat(citizenRepository.findAll()).isEmpty();
        assertThat(citizenshipRepository.findAll()).isEmpty();
        assertThat(housingRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("not create citizen on case of forbidden address")
    void notCreateCitizenForbiddenAddress() {
        var createCitizenRequest = getDataRequest();
        createCitizenRequest.getHousings().add(HousingDto.builder()
                .address(FORBIDDEN_HOUSING)
                .build());
        var result = citizenApiService.createCitizen(createCitizenRequest);
        assertThat(result).isNotNull();
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getIdCitizenCreated()).isZero();

        assertThat(citizenRepository.findAll()).isEmpty();
        assertThat(citizenshipRepository.findAll()).isEmpty();
        assertThat(housingRepository.findAll()).isEmpty();
    }

    private CreateCitizenRequestDto getDataRequest() {
        var citizenShipsDto = new ArrayList<CitizenshipDto>();
        citizenShipsDto.add(CitizenshipDto.builder()
                .countryCode("RU")
                .build());
        citizenShipsDto.add(CitizenshipDto.builder()
                .countryCode("EN")
                .build());

        var housingsDto = new ArrayList<HousingDto>();
        housingsDto.add(HousingDto.builder()
                .address("address 1")
                .build());
        housingsDto.add(HousingDto.builder()
                .address("address 2")
                .build());

        return CreateCitizenRequestDto.builder()
                .citizen(CitizenDto.builder()
                        .name("citizen name")
                        .hasFreedom(true)
                        .incomeValue(1000)
                        .build())
                .citizenShips(citizenShipsDto)
                .housings(housingsDto)
                .build();
    }
}