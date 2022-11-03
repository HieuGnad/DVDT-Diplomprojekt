package at.spengergasse.bloodpressurecheck.model;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//Entity bewirkt, dass die Klasse Patient von JPA beachtet wird
// und dafür eine Tabelle in der DB erzeugt wird
@Entity
@Table(name="p_patient")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Patient extends DomainResource{
    public enum GenderCode{
        male, female, other, unknown
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_p_id", referencedColumnName = "id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    @Column(name = "p_active")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="hn_p_id",nullable = true, referencedColumnName = "id")
    private List<HumanName> name = new ArrayList<HumanName>();

    @Enumerated(EnumType.STRING)
    @Column(name = "p_gender")
    private GenderCode gender;

    @Column(name = "p_birthdate")
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name= "a_p_id", referencedColumnName = "id")
    private List<Address> address = new ArrayList<Address>();

    @Column(name="p_svnr")
    private String svnr;

}
