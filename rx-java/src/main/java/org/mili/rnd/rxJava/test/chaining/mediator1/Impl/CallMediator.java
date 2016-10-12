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

package org.mili.rnd.rxJava.test.chaining.mediator1.Impl;

import org.mili.rnd.rxJava.test.chaining.mediator1.MediatorOnSubscriber;
import org.mili.rnd.rxJava.test.chaining.mediator1.RunnableMediator;
import org.mili.rnd.rxJava.test.chaining.mediator1.RxContext;

/**
 * Created by milinda on 10/3/16.
 */
public class CallMediator extends RunnableMediator {

    private String name;
    private long sleepTime = 1000;


    public CallMediator(String name, long sleepTime) {
        super(new MediatorOnSubscriber());
        this.name = name;
        this.sleepTime = sleepTime;
    }

    /**
     * USED TO DEMONSTRATE ASYNC PROCESSING
     */
    @Override
    public void run() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START CallMediator : " + name);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("[THREAD " + Thread.currentThread().getId() + "] END CallMediator : " + name);

        //send complete event to subscribed mediators
        if (mediatorSubscriber.getSubscriberMediator() != null) {
            //RxContext context = new RxContext("new Context");
            RxContext context = super.getContext();
            context.setMessage("This is call mediator " + name);
            mediatorSubscriber.getSubscriberMediator().onNext(context);
            mediatorSubscriber.getSubscriberMediator().onCompleted();
        }
    }

    @Override
    public boolean receive(RxContext rxContext) {

        super.setContext(rxContext);
        (new Thread(this)).start();


        return false;
    }
}
