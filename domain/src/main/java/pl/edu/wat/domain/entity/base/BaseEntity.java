package pl.edu.wat.domain.entity.base;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {

}
