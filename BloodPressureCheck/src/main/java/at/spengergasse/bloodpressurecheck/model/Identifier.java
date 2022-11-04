package at.spengergasse.bloodpressurecheck.model;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="i_identifier")
public class Identifier extends Element{
    public enum UseCode{
        usual, official, temp, secondary, old
    }
    @Enumerated(EnumType.STRING)
    @Column(name="i_use")
    private UseCode code;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="i_cc_id")
    private CodeableConcept type;
    @Column(name="i_system")
    private String system;
    @Column(name="i_value")
    private String value;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "i_pe_id", referencedColumnName = "id")
    private Period period;

    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "i_re_id", referencedColumnName = "id")
    //private Reference assigner;
}
