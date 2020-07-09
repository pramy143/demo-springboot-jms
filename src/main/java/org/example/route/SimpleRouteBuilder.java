package org.example.route;

import javax.jms.ConnectionFactory;
import javax.xml.bind.JAXBContext;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.example.model.Employee;
import org.example.processor.MyProcessor;

import javax.xml.bind.JAXBContext;

public class SimpleRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// XML Data Format
		JaxbDataFormat xmlDataFormat = new JaxbDataFormat();
		JAXBContext con = JAXBContext.newInstance(Employee.class);
		xmlDataFormat.setContext(con);

		// JSON Data Format
		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(Employee.class);

		/*from("file:C:/inputFolder").doTry().unmarshal(xmlDataFormat).
		process(new MyProcessor()).marshal(jsonDataFormat).
		to("jms:queue:inPaymentQueue").doCatch(Exception.class).process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
						System.out.println(cause);
					}
				});*/

		from("jms:queue:inPaymentQueue")
			.doTry()
			.unmarshal(xmlDataFormat)
			.process(new MyProcessor())
			.to("jms:queue:outPaymentQueue")
			.bean()
			.doCatch(Exception.class).process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
				System.out.println(cause);
			}
		});
	}

	public static void main(String[] args) {
		SimpleRouteBuilder routeBuilder = new SimpleRouteBuilder();
		CamelContext ctx = new DefaultCamelContext();

		//configure jms component
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
		ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

		try {
			ctx.addRoutes(routeBuilder);
			ctx.start();
			Thread.sleep(5 * 60 * 1000);
			ctx.stop();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
}