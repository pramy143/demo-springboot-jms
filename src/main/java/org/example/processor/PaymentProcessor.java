package org.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PaymentProcessor implements Processor {

    @Override
    public void process(final Exchange exchange) throws Exception {
        System.out.println("Hai");

        /*{
            public void process(Exchange xchg) throws Exception {
            //the camel jdbc select query has been executed. We get the list of employees.
            ArrayList<Map<String, String>> dataList =
                (ArrayList<Map<String, String>>) xchg.getIn().getBody();
            List<Payment> payments = new ArrayList<>();
            System.out.println(dataList);
            for (Map<String, String> data : dataList) {
                Payment payment = new Payment.Builder()
                    .paymentId(Long.valueOf(data.get("paymentId")))
                    .paymentType(data.get("paymentType"))
                    .paymentStatus(data.get("paymentStatus"))
                    .description(data.get("description"))
                    .build();
                payments.add(payment);
            }
            xchg.getIn().setBody(payments);
        }
        }*/
    }
}
