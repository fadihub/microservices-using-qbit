package io.advantageous.qbit.example;

import io.advantageous.qbit.server.ServiceServer;
import io.advantageous.qbit.server.ServiceServerBuilder;

public class Main {

    public static void main(String... args) {
        final ServiceServer serviceServer = ServiceServerBuilder
                .serviceServerBuilder()
                .setUri("/main")
                .setPort(9090).build();
        serviceServer.initServices(
                new PongService(),
                new SimpleService(),
                new EmployeeDirectoryService());
        serviceServer.startServer();
    }
}