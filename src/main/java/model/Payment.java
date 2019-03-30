package model;

import lombok.Data;
import javax.persistence.*;

@Data
@NamedQuery(name="Payment_findById", query="from Payment where paymentID =: paymentID")
@Entity
@Table (name="payments")

public class Payment {
    @Id
    private int paymentID;
    private int orderID;
    private String paymentDate;
    private int valueOfPayment;
    private int firmID;
}
