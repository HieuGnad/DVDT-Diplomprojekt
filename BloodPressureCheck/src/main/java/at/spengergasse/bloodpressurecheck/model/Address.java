package at.spengergasse.bloodpressurecheck.model;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="a_address")
public class Address extends Element{
    public enum UseCode {home, work, temp, old, billing}
    public enum TypeCode {postal, physical, both}
    @Enumerated(EnumType.STRING)
    @Column(name = "a_use")
    private UseCode use;
    @Enumerated(EnumType.STRING)
    @Column(name = "a_type")
    private TypeCode type;
    @Column(name = "a_text")
    private String text;
    @Column (name = "a_address_line")
    private String line;
    @Column(name = "a_city")
    private String city;
    @Column(name = "a_district")
    private String district;
    @Column(name = "a_state")
    private String state;
    @Column(name = "a_postalcode")
    private String postalcode;
    @Column(name = "a_country")
    private String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "a_pe_id", referencedColumnName = "id")
    private Period period;
}