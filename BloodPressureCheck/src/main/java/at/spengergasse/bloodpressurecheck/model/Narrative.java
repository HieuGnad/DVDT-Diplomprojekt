package at.spengergasse.bloodpressurecheck.model;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name="n_narrative")
public class Narrative extends Element{
    public enum NarrativeCode{
        generated , extensions , additional , empty
    }
    @Column(name= "n_status")
    private NarrativeCode status;
    @Lob
    @Column(name="n_div")
    private String div;
}