package com.vinsguru.sec06;

import com.vinsguru.models.sec06.AccountBalance;
import com.vinsguru.models.sec06.BalanceCheckRequest;
import com.vinsguru.models.sec06.BankServiceGrpc;
import com.vinsguru.sec06.repository.AccountRepository;
import io.grpc.stub.StreamObserver;

public class BankService extends BankServiceGrpc.BankServiceImplBase{

    @Override
    public void getAccountBalance(BalanceCheckRequest request, StreamObserver<AccountBalance> responseObserver) {
        var accountsNumber = request.getAccountNumber();

        var balance = AccountRepository.getBalance(accountsNumber);

        var accontsBalance = AccountBalance.newBuilder()
                .setAccountNumber(accountsNumber)
                .setBalance(balance)
                .build();
        responseObserver.onNext(accontsBalance);
        responseObserver.onCompleted();
    }
}
