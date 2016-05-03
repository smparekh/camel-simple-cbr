package com.shaishav.camel.simple.cbr;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created by sparekh on 4/18/16.
 */
public class SimpleCBRRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:start")
                .choice()
                .when(body(String.class).isEqualTo("car"))
                .setHeader("TYPE").constant("CAR").processRef("bodyProcessor").log("${body}").to("mock:car")
                .when(body(String.class).isEqualTo("truck"))
                .setHeader("TYPE").constant("TRUCK").processRef("bodyProcessor").log("${body}").to("mock:car")
                .when(body(String.class).isEqualTo("airplane"))
                .setHeader("TYPE").constant("AIRPLANE").processRef("bodyProcessor").log("${body}").to("mock:airplane")
                .when(body(String.class).isEqualTo("train"))
                .setHeader("TYPE").constant("TRAIN").processRef("bodyProcessor").log("${body}").to("mock:train")
                .when(body(String.class).isEqualTo("boat"))
                .setHeader("TYPE").constant("BOAT").processRef("bodyProcessor").log("${body}").to("mock:boat")
                .endChoice()
                .otherwise()
                .log(LoggingLevel.ERROR, "Unknown type.").to("mock:error");
    }
}

