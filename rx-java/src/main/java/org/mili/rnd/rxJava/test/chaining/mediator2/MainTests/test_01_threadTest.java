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

package org.mili.rnd.rxJava.test.chaining.mediator2.MainTests;

import org.mili.rnd.rxJava.test.chaining.mediator2.Impl.CallMediator;
import org.mili.rnd.rxJava.test.chaining.mediator2.Impl.LogMediator;
import org.mili.rnd.rxJava.test.chaining.mediator2.RxContext;

/**
 * Created by milinda on 10/3/16.
 */
public class test_01_threadTest {
    public static void main(String[] args) {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START Main Thread");

        CallMediator callMediatorA = new CallMediator("A", 1000);
        LogMediator logMediatorA = new LogMediator();
        CallMediator callMediatorB = new CallMediator("B", 1000);

        callMediatorA.subscribe(logMediatorA);
        logMediatorA.subscribe(callMediatorB);

        callMediatorA.receive(new RxContext("Initial RxContext"));

        System.out.println("[THREAD " + Thread.currentThread().getId() + "] END Main Thread");
    }
}
