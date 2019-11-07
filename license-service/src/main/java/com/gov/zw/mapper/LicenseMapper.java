package com.gov.zw.mapper;

import com.gov.zw.dto.License;
import com.gov.zw.domain.LicenseJson;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class LicenseMapper {
    public static License toLicenseDTO(LicenseJson json) {
        return Optional.ofNullable(json)
                .map(licenseJson -> new License(
                        licenseJson.getId(),
                        licenseJson.getIdentityRef(),
                        licenseJson.getSurname(),
                        licenseJson.getFirstNames(),
                        licenseJson.getDateOfBirth(),
                        licenseJson.getCountry(),
                        licenseJson.getDateOfIssue(),
                        licenseJson.getExpiryDate(),
                        licenseJson.getAgency(),
                        licenseJson.getLicenseNumber(),
                        licenseJson.getSignatureImage(),
                        licenseJson.getAddress()))
                .orElse(new License());
    }

    public static LicenseJson toLicenseJson(License license) {
        return Optional.ofNullable(license)
                .map(licenseDto -> new LicenseJson(
                        licenseDto.getId(),
                        licenseDto.getIdentityRef(),
                        licenseDto.getSurname(),
                        licenseDto.getFirstNames(),
                        licenseDto.getDateOfBirth(),
                        licenseDto.getCountry(),
                        licenseDto.getDateOfIssue(),
                        licenseDto.getExpiryDate(),
                        licenseDto.getAgency(),
                        licenseDto.getLicenseNumber(),
                        licenseDto.getSignatureImage(),
                        licenseDto.getAddress()))
                .orElse(new LicenseJson());
    }

    public static List<LicenseJson> toLicenseJsonList(List<License> licenses) {
        return Stream.of(licenses)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(LicenseMapper::toLicenseJson)
                .collect(Collectors.toList());
    }
}
