package org.example.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.example.model.Employee;

public class MyProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		Employee employee = exchange.getIn().getBody(Employee.class);
		employee.setEmpName("JavaInUse Rocks");
		exchange.getIn().setBody(employee);
	}

}