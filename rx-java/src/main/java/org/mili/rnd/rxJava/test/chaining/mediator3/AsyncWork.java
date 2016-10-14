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

import rx.subjects.BehaviorSubject;

/**
 * This represent some Asynchronous work
 */
public class AsyncWork implements Runnable {

    private long sleepTime = 0;
    private String name;
    private BehaviorSubject<RxContext> subject;
    private RxContext context;

    public AsyncWork(long sleepTime, String workName, BehaviorSubject<RxContext> behaviorSubject, RxContext rxContext) {
        this.sleepTime = sleepTime;
        this.name = workName;
        this.subject = behaviorSubject;
        this.context = rxContext;
    }

    @Override
    public void run() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START AsyncWork : " + name);

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        subject.onNext(context);
        subject.onCompleted();

        System.out.println("[THREAD " + Thread.currentThread().getId() + "] END AsyncWork : " + name);
    }

    public void exec () {
        new Thread(this).start();
    }

}
