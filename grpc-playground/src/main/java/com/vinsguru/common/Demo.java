package com.vinsguru.common;

import com.vinsguru.sec06.BankService;
import com.vinsguru.sec06.TransferService;
import com.vinsguru.sec07.FlowControlService;

public class Demo {

    public static void main(String[] args) {
        //GrpcServer.create(new FlowControlService(), new TransferService())
        GrpcServer.create(new FlowControlService())
                .start()
                .await();
    }
}
