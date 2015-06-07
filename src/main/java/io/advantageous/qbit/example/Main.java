package io.advantageous.qbit.example;

import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;

public class Main {

    public static void main(String... args) {
        final ServiceEndpointServer serviceServer = EndpointServerBuilder
                .endpointServerBuilder()
                .setUri("/main")
                .setPort(9090).build();
        serviceServer.initServices(
                new PongService(),
                new SimpleService(),
                new EmployeeDirectoryService());
        serviceServer.startServer();
    }
}