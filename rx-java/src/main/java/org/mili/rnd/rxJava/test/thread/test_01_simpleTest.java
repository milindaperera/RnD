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

package org.mili.rnd.rxJava.test.thread;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by milinda on 9/28/16.
 */
public class test_01_simpleTest {
    public static void main(String[] args) {

        System.out.println("START Main in THREAD " + Thread.currentThread().getId());
        Service serviceA = new Service("A", 2000);
        Service serviceB = new Service("B", 2000);
        Service serviceC = new Service("C", 2000);

        Subscriber<String> subscriberB = new ServiceSubscriber(serviceB);
        Subscriber<String> subscriberC = new ServiceSubscriber(serviceC);

        Observable<String> observableA = serviceA.callAsynch("<ServiceA>");
        observableA.subscribe(subscriberB);

        Observable<String> observableB = serviceB.callAsynch("<ServiceB>");
        observableB.subscribe(subscriberC);

        Observable<String> observableC = serviceC.callAsynch("<ServiceC>");
        //observableC.subscribe(subscriberB);

        serviceA.start();

        System.out.println("Service chain END");
        System.out.println("END Main in THREAD " + Thread.currentThread().getId());

    }
}
