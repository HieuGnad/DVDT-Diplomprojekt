package at.spengergasse.bloodpressurecheck.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder

@Table(name="b_backboneelement")
public class BackboneElement extends Element{
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="b_id")
    private List<Identifier> identifier = new ArrayList<Identifier>();

    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name="b_pr_id")
    //private List<Practitioner> practitioner = new ArrayList<Practitioner>();
}
