package com.vinsguru.test.sec06;

import com.google.protobuf.Empty;
import com.vinsguru.models.sec06.AllAccountResponse;
import com.vinsguru.models.sec06.Money;
import com.vinsguru.models.sec06.WithdrawRequest;
import com.vinsguru.test.common.ResponseObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec03ServerStramingClientTest extends AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(Lec03ServerStramingClientTest.class);

    @Test
    public void blockingClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAccountsNumber(2)
                .setAmount(20)
                .build();
        var iterator = this.blockingStub.withdraw(request);
        int count = 0;
        while (iterator.hasNext()) {
            log.info("received money: {}", iterator.next());
            count++;
        }
        Assertions.assertEquals(2, count);
    }

    @Test
    public void asyncClientWithdrawTest() {
        var request = WithdrawRequest.newBuilder()
                .setAccountsNumber(2)
                .setAmount(20)
                .build();
        var observer = ResponseObserver.<Money>create();
        this.stub.withdraw(request, observer);
        observer.await();
        Assertions.assertEquals(2, observer.getItens().size());
        Assertions.assertEquals(10, observer.getItens().get(0).getAmount());
        Assertions.assertNull(observer.getThrowable());
    }

}
