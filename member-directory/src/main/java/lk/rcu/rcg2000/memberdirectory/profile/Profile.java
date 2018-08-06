package lk.rcu.rcg2000.memberdirectory.profile;

import lk.rcu.rcg2000.memberdirectory.company.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = true)
    private String email;

    @Column(name = "PHONE_NUMBER", nullable = true)
    private String phoneNumber;

    @Column(name = "PROFESSION", nullable = true)
    private String profession;

    @Column(name = "CITY", nullable = true)
    private String city;

    @Column(name = "COUNTRY", nullable = true)
    private String country;

    @Column(name = "NICK_NAME", nullable = true)
    private String nickName;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COMPANY_ID")
    private Company company;


}
