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

import rx.Subscriber;

/**
 * Created by milinda on 9/29/16.
 */
public class ServiceSubscriber extends Subscriber<String> {

    private Service service;

    public ServiceSubscriber(Service service) {
        this.service = service;
    }

    @Override
    public void onCompleted() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] onCompleted");
        (new Thread(service)).start();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] onError");
    }

    @Override
    public void onNext(String s) {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] onNext : " + s);
    }

}
