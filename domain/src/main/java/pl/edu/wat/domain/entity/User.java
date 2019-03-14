package pl.edu.wat.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.edu.wat.domain.entity.base.BaseEntity;

import javax.persistence.*;
import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Entity
@Builder
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "T_USER")
@SequenceGenerator(name = "S_USER", initialValue = 100, sequenceName = "SEQ_USER", allocationSize = 1)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "S_USER")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_users_roles", uniqueConstraints = {@UniqueConstraint(columnNames={"user_id", "role_id"})},
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public User() {
        super();
        this.enabled = true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", name=").append(name);
        return builder.toString();
    }

}
