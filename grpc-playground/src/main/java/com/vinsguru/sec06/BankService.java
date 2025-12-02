package com.vinsguru.sec06;

import com.vinsguru.models.sec06.AccountBalance;
import com.vinsguru.models.sec06.BalanceCheckRequest;
import com.vinsguru.models.sec06.BankServiceGrpc;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase{

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountsNumber = request.getAccountNumber();
        var accontsBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountsNumber)
                .setBalance(accountsNumber * 10)
                .build();
        responseObserver.onNext(accontsBalance);
        responseObserver.onCompleted();
    }
}
