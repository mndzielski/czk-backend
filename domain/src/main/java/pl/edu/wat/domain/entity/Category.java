package pl.edu.wat.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.edu.wat.domain.entity.base.BaseEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "T_CATEGORY")
@SequenceGenerator(name = "S_CATEGORY", initialValue = 100, sequenceName = "SEQ_CATEGORY", allocationSize = 1)
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_CATEGORY")
    private Long id;

    @Column(unique = true)
    String name;

    String icon;

    @Column(length = 500)
    String description;
}
