package io.advantageous.qbit.example;

import io.advantageous.qbit.server.ServiceServer;
import io.advantageous.qbit.server.ServiceServerBuilder;

public class PingMain {

    public static void main(String... args) {
        final ServiceServer serviceServer = ServiceServerBuilder
                .serviceServerBuilder()
                .setPort(9090).build();
        serviceServer.initServices(new PongService());
        serviceServer.startServer();
    }
}