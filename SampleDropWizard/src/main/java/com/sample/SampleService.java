package com.sample;

import com.sample.controller.EmployeeRestController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class SampleService extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new SampleService().run(args);
    }
    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }
    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(new EmployeeRestController());
    }
}
