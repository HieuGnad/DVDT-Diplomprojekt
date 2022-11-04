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
@Table(name="hn_humanname")
public class HumanName extends Element{
    public enum UseCode{
        usual , official , temp , nickname , anonymous , old , maiden
    }
    @Column(name = "hn_use")
    @Enumerated(EnumType.STRING)
    private UseCode use;
    @Column(name="hn_text")
    private String text;
    @Column(name="hn_family")
    private String family;
    @Column(name="pp_end")
    private String end;
    @Column(name="pp_start")
    private String start;
    @ElementCollection
    @CollectionTable(name="given_humanname", joinColumns =
    @JoinColumn(name="id"))

    private List<String> given = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name="prefix_humanname", joinColumns =
    @JoinColumn(name="id"))

    private List<String> prefix = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name="suffix_humanname", joinColumns =
    @JoinColumn(name="id"))

    private List<String> suffix = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="hn_pe_id")
    private Period period;
}