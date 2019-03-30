package model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Firms_deleteByFirmName", query = "from Firms where firmName =: firmName")
@Table (name = "firms")
public class Firms {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int firmID;
    private int fixedDiscounts;
    private String firmName;
    private String firmEmail;
}
