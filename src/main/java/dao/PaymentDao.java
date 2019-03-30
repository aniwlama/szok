package dao;

import model.Payment;
import java.util.List;

public interface PaymentDao {

    List<Payment>findAll();
    Payment getPaymentById(int paymentID);
    boolean insertPayment(Payment payment);
    boolean deletePayment(int paymentID);
}
