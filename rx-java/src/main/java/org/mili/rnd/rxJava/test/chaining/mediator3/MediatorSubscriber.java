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

package org.mili.rnd.rxJava.test.chaining.mediator3;

import rx.Subscriber;

/**
 * This class represents Subscriber object for mediators
 */
public class MediatorSubscriber extends Subscriber<RxContext> {

    private RxContext context = null;

    @Override
    public void onCompleted() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START MediatorSubscriber onCompleted");
        context.getCurrentMediator().next(context);
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
