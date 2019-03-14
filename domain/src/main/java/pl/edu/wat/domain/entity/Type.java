package pl.edu.wat.domain.entity;

import lombok.*;
import pl.edu.wat.domain.entity.base.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_TYPE")
@SequenceGenerator(name = "S_TYPE", initialValue = 100, sequenceName = "SEQ_TYPE", allocationSize = 1)
public class Type extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_TYPE")
    private Long id;

    @Column(unique = true)
    String name;

    String icon;
}
