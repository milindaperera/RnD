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

package org.mili.rnd.rxJava.test.chaining.mediator2;

import rx.subjects.Subject;

/**
 * Created by milinda on 10/3/16.
 */
public abstract class AbstractMediator extends Subject<RxContext, RxContext> implements Mediator {

    //For now Assume only one mediator can subscribed
    protected MediatorSubscriptionHandler mediatorSubscriptionHandler;
    private RxContext context;

    protected AbstractMediator(MediatorSubscriptionHandler subscriber) {
        super(subscriber);
        mediatorSubscriptionHandler = subscriber;
    }

    @Override
    public void onCompleted() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] AbstractMediator : onComplete");
        //perform mediator
        this.receive(context);
    }

    @Override
    public void onError(Throwable throwable) {
        //propagate error to subscribed mediators
        mediatorSubscriptionHandler.getSubscriberMediator().onError(throwable);
        if (mediatorSubscriptionHandler.getSubscriberMediator() != null) {
                mediatorSubscriptionHandler.getSubscriberMediator().onError(throwable);
        } else {
            System.out.println("This is last mediator. handle the ERROR");
        }
    }

    @Override
    public void onNext(RxContext rxContext) {
        //Assume onNext invoked only once. If need to invoke mutiple times depending on the mediator type,
        // we have to override in the mediator impl
        //update context
        context = rxContext;
    }

    @Override
    public boolean hasObservers() {
        return mediatorSubscriptionHandler != null;
    }

    @Override
    public boolean next(RxContext context) {
        if (mediatorSubscriptionHandler.getSubscriberMediator() != null) {
            mediatorSubscriptionHandler.getSubscriberMediator().onNext(context);
            mediatorSubscriptionHandler.getSubscriberMediator().onCompleted();
            return true;
        }
        return false;
    }



    public MediatorSubscriptionHandler getMediatorSubscriptionHandler() {
        return mediatorSubscriptionHandler;
    }

    public void setMediatorSubscriptionHandler(MediatorSubscriptionHandler mediatorSubscriptionHandler) {
        this.mediatorSubscriptionHandler = mediatorSubscriptionHandler;
    }

    public RxContext getContext() {
        return context;
    }

    public void setContext(RxContext context) {
        this.context = context;
    }
}
