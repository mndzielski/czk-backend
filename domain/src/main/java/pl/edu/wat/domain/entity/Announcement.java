package pl.edu.wat.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pl.edu.wat.commons.utils.Constans;
import pl.edu.wat.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "T_ANNOUNCEMENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "S_ANNOUNCEMENT", initialValue = 100, sequenceName = "SEQ_ANNOUNCEMENT", allocationSize = 1)
public class Announcement extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_ANNOUNCEMENT")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String province;

    private Boolean wholeProvince;

    @ElementCollection
    @CollectionTable(name = "T_DISTRICTS", joinColumns = @JoinColumn(name = "announcement_id"))
    private List<String> districts = new ArrayList<>();

    private String title;

    @Column(length = 1000)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constans.DATE_TIME_FORMAT)
    private LocalDateTime dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constans.DATE_TIME_FORMAT)
    private LocalDateTime dateTo;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constans.DATE_TIME_FORMAT)
    private LocalDateTime created;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constans.DATE_TIME_FORMAT)
    private LocalDateTime updated;
}
