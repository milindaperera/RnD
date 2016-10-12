/*
 *     Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *     WSO2 Inc. licenses this file to you under the Apache License,
 *     Version 2.0 (the "License"); you may not use this file except
 *     in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing,
 *    software distributed under the License is distributed on an
 *    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *    KIND, either express or implied.  See the License for the
 *    specific language governing permissions and limitations
 *    under the License.
 */

package org.mili.rnd.rxJava.test.chaining.mediator1;

import rx.Subscriber;

/**
 * Created by milinda on 10/3/16.
 */
public class MediatorSubscriber extends Subscriber<RxContext> {

    private Mediator subscribedMediator;
    private RxContext context;

    public MediatorSubscriber(Mediator subscribedMediator) {
        this.subscribedMediator = subscribedMediator;
    }

    public MediatorSubscriber(Subscriber<?> subscriber, Mediator subscribedMediator) {
        super(subscriber);
        this.subscribedMediator = subscribedMediator;
    }

    public MediatorSubscriber(Subscriber<?> subscriber, boolean shareSubscriptions, Mediator subscribedMediator) {
        super(subscriber, shareSubscriptions);
        this.subscribedMediator = subscribedMediator;
    }



    @Override
    public void onCompleted() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START MediatorSubscriber onCompleted");
        subscribedMediator.receive(context);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START MediatorSubscriber onError");
    }

    @Override
    public void onNext(RxContext rxContext) {
        context = rxContext;
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START MediatorSubscriber onNext");
    }

}
