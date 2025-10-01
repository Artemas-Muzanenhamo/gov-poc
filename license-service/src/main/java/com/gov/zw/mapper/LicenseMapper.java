package com.gov.zw.mapper;

import com.gov.zw.json.LicenseJson;
import com.gov.zw.domain.License;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LicenseMapper {
    private LicenseMapper() {
        // Hide implicit public constructor
    }

    public static License toLicenseDTO(LicenseJson json) {
        return Optional.ofNullable(json)
                .map(licenseJson -> new License(
                        licenseJson.id(),
                        licenseJson.identityRef(),
                        licenseJson.surname(),
                        licenseJson.firstNames(),
                        licenseJson.dateOfBirth(),
                        licenseJson.country(),
                        licenseJson.dateOfIssue(),
                        licenseJson.expiryDate(),
                        licenseJson.agency(),
                        licenseJson.licenseNumber(),
                        licenseJson.signatureImage(),
                        licenseJson.address()))
                .orElse(License.empty());
    }

    public static LicenseJson toLicenseJson(License license) {
        return Optional.ofNullable(license)
                .map(licenseDto -> new LicenseJson(
                        licenseDto.id(),
                        licenseDto.identityRef(),
                        licenseDto.surname(),
                        licenseDto.firstNames(),
                        licenseDto.dateOfBirth(),
                        licenseDto.country(),
                        licenseDto.dateOfIssue(),
                        licenseDto.expiryDate(),
                        licenseDto.agency(),
                        licenseDto.licenseNumber(),
                        licenseDto.signatureImage(),
                        licenseDto.address()))
                .orElse(LicenseJson.empty());
    }

    public static List<LicenseJson> toLicenseJsonList(List<License> licenses) {
        return Optional.ofNullable(licenses)
                .orElseGet(List::of)
                .stream()
                .map(LicenseMapper::toLicenseJson)
                .toList();
    }
}
