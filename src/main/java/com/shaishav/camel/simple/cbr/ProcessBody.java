package com.shaishav.camel.simple.cbr;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sparekh on 4/18/16.
 */
public class ProcessBody implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = dateFormat.format(new Date());
        String type = exchange.getIn().getHeader("TYPE", String.class);
        String body = "Received type " + type + " on " + today;
        exchange.getIn().setBody(body);

    }
}
