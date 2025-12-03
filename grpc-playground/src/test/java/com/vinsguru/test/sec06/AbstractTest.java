package com.vinsguru.test.sec06;

import com.vinsguru.common.GrpcServer;
import com.vinsguru.models.sec06.BankServiceGrpc;
import com.vinsguru.sec06.BankService;
import com.vinsguru.test.common.AbstractChannelTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class AbstractTest extends AbstractChannelTest {
    private final GrpcServer grpcServer = GrpcServer.create(new BankService());
    protected BankServiceGrpc.BankServiceStub stub;
    protected BankServiceGrpc.BankServiceBlockingStub blockingStub;

    @BeforeAll
    public void setup() {
        this.grpcServer.start();
        this.stub = BankServiceGrpc.newStub(channel);
        this.blockingStub = BankServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    public void stop() {
        this.grpcServer.stop();
    }

}
