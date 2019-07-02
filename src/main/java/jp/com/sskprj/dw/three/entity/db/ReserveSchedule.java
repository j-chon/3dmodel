package jp.com.sskprj.dw.three.entity.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "reserveSchedule")
@NamedQueries(
        {
                @NamedQuery(
                        name = "jp.com.sskprj.dw.three.reserveSchedule.findByMonth",
                        query = "SELECT r FROM jp.com.sskprj.dw.three.entity.db.ReserveSchedule r"
                )
        })
public class ReserveSchedule {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private String dateTime;
}
