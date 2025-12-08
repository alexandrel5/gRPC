package com.vinsguru.test.sec06;

import com.vinsguru.models.sec06.AccountBalance;
import com.vinsguru.models.sec06.DepositRequest;
import com.vinsguru.models.sec06.Money;
import com.vinsguru.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

public class Lec04ClientStreamingTest extends AbstractTest {

    @Test
    public void depositTest(){
        var responseObserver = ResponseObserver.<AccountBalance>create();
        var requestObserver = this.bankStub.deposit(responseObserver);

        //initial message
        requestObserver.onNext(DepositRequest.newBuilder().setAccountNumber(5).build());
        //sending string of money
        IntStream.rangeClosed(1, 10)
                .mapToObj(i -> Money.newBuilder().setAmount(10).build())
                .map(m -> DepositRequest.newBuilder().setMoney(m).build())
                .forEach(requestObserver::onNext);
        //notifying the server that we are done
        requestObserver.onCompleted();

        //at this point out response observer should receiver a response
        responseObserver.await();

        Assertions.assertEquals(1, responseObserver.getItems().size());
        Assertions.assertEquals(200, responseObserver.getItems().get(0).getBalance());
        Assertions.assertNull(responseObserver.getThrowable());
    }

}
