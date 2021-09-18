package pl.sda.spring2_travel360.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.spring2_travel360.dto.CountryDto;
import pl.sda.spring2_travel360.mapper.CountryMapper;
import pl.sda.spring2_travel360.repository.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    public List<CountryDto> getAllContury() {
        log.info("Get Conturies");
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void addCountry(CountryDto countryDto) {
        log.info("Add Country: {}", countryDto);
        var country = countryMapper.mapToCountry(countryDto);
        countryRepository.save(country);
    }
}
