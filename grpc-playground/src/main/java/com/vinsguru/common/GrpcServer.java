package com.vinsguru.common;

import com.vinsguru.sec06.BankService;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws Exception {
        var server = ServerBuilder.forPort(6565)
                .addService(new BankService())
                .build();
        server.start();

        server.awaitTermination();

    }
}
