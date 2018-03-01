package lk.rcu.rcg2000.memberdirectory.profile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ruwan on 3/1/18.
 */

@Entity
@EqualsAndHashCode(of = {"id"})
@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PROFILE")
public class Profile {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", updatable = false, nullable = false)
    private String id;

}
