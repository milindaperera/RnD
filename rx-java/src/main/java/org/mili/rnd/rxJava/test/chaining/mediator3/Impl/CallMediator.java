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

package org.mili.rnd.rxJava.test.chaining.mediator3.Impl;

import org.mili.rnd.rxJava.test.chaining.mediator3.AbstractMediator;
import org.mili.rnd.rxJava.test.chaining.mediator3.AsyncWork;
import org.mili.rnd.rxJava.test.chaining.mediator3.MediatorSubscriber;
import org.mili.rnd.rxJava.test.chaining.mediator3.RxContext;
import rx.subjects.BehaviorSubject;

/**
 * Created by milinda on 10/3/16.
 */
public class CallMediator extends AbstractMediator {

    private String name;
    private long sleepTime = 1000;


    public CallMediator(String name, long sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }


    @Override
    public boolean receive(RxContext context) {
        //set current executing mediator
        context.setCurrentMediator(this);

        BehaviorSubject<RxContext> behaviorSubject = BehaviorSubject.create();
        behaviorSubject.subscribe(new MediatorSubscriber());

        AsyncWork callBE = new AsyncWork(sleepTime, "callBE", behaviorSubject, context);
        callBE.exec();

        return true;
    }
}
