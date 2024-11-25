package pe.edu.I202222350.cl1_jpa_data_sihuinta_andy.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LanguagePk {
    private String Language;
    private String CountryCode;
}