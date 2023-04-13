package com.auditing.lemi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;
import javax.persistence.*;

@Entity
@Table(name = "user")
@OptimisticLocking(type = OptimisticLockType.NONE)
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends AbstractEntity implements Versionable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String name;

    @Version
    private Integer version;

    @Override
//    @JsonIgnore
    public String getSimpleName() {
        return "User";
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", createdBy='" + createdBy + '\'' +
                ", createdAt=" + createdOn +
                ", lastModifiedBy=" + modifiedBy +
                ", lastModifiedAt=" + updatedOn +
                '}';
    }
}
