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
import rx.Observer;
import rx.Subscriber;

/**
 * Created by milinda on 9/28/16.
 */
public class Service implements Runnable {

    private String name;
    private long sleepTime = 1000;
    private Subscriber<? super String> serviceSubscriber;
    private String request;

    public Service(String name) {
        this.name = name;
    }

    public Service(String name, long sleepTime) {
        this.name = name;
        this.sleepTime = sleepTime;
    }

    public Observable<String> callAsynch (String requestMsg) {

        request = requestMsg;
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                serviceSubscriber = subscriber;
            }
        });

        return observable;
    }

    public void start() {
        (new Thread(this)).start();
    }



    @Override
    public void run() {
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] START Processing Service : " +name);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("[THREAD " + Thread.currentThread().getId() + "] END Processing Service : " +name);
        if (serviceSubscriber != null) {
            serviceSubscriber.onNext("<response" + name + ">" + request);
            serviceSubscriber.onCompleted();
        }
    }
}
