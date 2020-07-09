package org.example.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.example.model.Employee;
import org.example.model.Payment;
import org.example.processor.PaymentProcessor;
import org.example.service.PaymentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl extends RouteBuilder {

	@Autowired
	DataSource dataSource;

	@Autowired PaymentProcessingService paymentProcessingService;


	@Override
	public void configure() throws Exception {
        
	       	 //Insert Route
	from("direct:insert")
		.process(new Processor() {
			public void process(Exchange xchg) throws Exception {
			//Take the Employee object from the exchange and create the insert query
				Employee employee = xchg.getIn().getBody(Employee.class);
				String query = "INSERT INTO employee(empId,empName)values('" + employee.getEmpId() + "','"
						+ employee.getEmpName() + "')";
			// Set the insert query in body and call camel jdbc
				xchg.getIn().setBody(query);
			}
		}).to("jdbc:dataSource");
		
		// Select Route
		from("direct:select")
			.routeId("cxfCamelFileOperation")
			.autoStartup(true)
			.setBody(constant("select * from payments"))
			.to("jdbc:dataSource")
			.process(new PaymentProcessor())
			.bean(paymentProcessingService, "converter")
		.end();

		
	}
}