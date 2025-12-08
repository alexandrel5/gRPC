package com.vinsguru.common;

import com.vinsguru.sec06.BankService;
import com.vinsguru.sec06.TransferService;

public class Demo {

    public static void main(String[] args) {
        GrpcServer.create(new BankService(), new TransferService())
                .start()
                .await();
    }
}
