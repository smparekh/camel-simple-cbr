package com.shaishav.camel.simple.cbr.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Before;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {

	@EndpointInject(uri = "mock:car")
    protected MockEndpoint car;

    @EndpointInject(uri = "mock:airplane")
    protected MockEndpoint airplane;

    @EndpointInject(uri = "mock:train")
    protected MockEndpoint train;

    @EndpointInject(uri = "mock:boat")
    protected MockEndpoint boat;

    @EndpointInject(uri = "mock:error")
    protected MockEndpoint error;

    @Produce(uri = "direct:start")
    protected ProducerTemplate directStart;

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

    @Override
    public boolean isCreateCamelContextPerClass() {
        return true;
    }

    /*
    @Before
    public void resetMockEndpoints() {
        car.reset();
        airplane.reset();
        train.reset();
        boat.reset();
        error.reset();
    }
    */

	@Test
	public void testSendCar() throws Exception {
		car.expectedMessageCount(1);
        car.expectedHeaderReceived("TYPE", "CAR");

        directStart.sendBody("car");

		// assert expectations
		assertMockEndpointsSatisfied();
	}

    @Test
    public void testSendTruck() throws Exception {
        car.expectedMessageCount(1);
        car.expectedHeaderReceived("TYPE", "TRUCK");

        directStart.sendBody("truck");

        // assert expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSendAirplane() throws Exception {
        airplane.expectedMessageCount(1);
        airplane.expectedHeaderReceived("TYPE", "AIRPLANE");


        directStart.sendBody("airplane");

        // assert expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSendTrain() throws Exception {
        train.expectedMessageCount(1);
        train.expectedHeaderReceived("TYPE", "TRAIN");

        directStart.sendBody("train");

        // assert expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testSendBoat() throws Exception {
        boat.expectedMessageCount(1);
        boat.expectedHeaderReceived("TYPE", "BOAT");

        directStart.sendBody("boat");

        // assert expectations
        assertMockEndpointsSatisfied();
    }

    @Test
    public void testOtherwise() throws Exception {
        error.expectedMessageCount(1);

        directStart.sendBody("Dummy");

        // assert expectations
        assertMockEndpointsSatisfied();
    }

}
