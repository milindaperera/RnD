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

package org.mili.rnd.rxJava.test.chaining.mediator3.MainTest;

import org.mili.rnd.rxJava.test.chaining.mediator3.Impl.CallMediator;
import org.mili.rnd.rxJava.test.chaining.mediator3.Impl.LogMediator;
import org.mili.rnd.rxJava.test.chaining.mediator3.Mediator;
import org.mili.rnd.rxJava.test.chaining.mediator3.RxContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Main test class to demonstrate ussage
 */
public class MainTest {

    public static void main(String[] args) {

        List <Mediator> mediatorList = new ArrayList<>();

        LogMediator log_1 = new LogMediator();
        LogMediator log_2 = new LogMediator();
        CallMediator call_1 = new CallMediator("callA", 5000);

        log_1.setNextMediator(call_1);
        call_1.setNextMediator(log_2);

        mediatorList.add(log_1);
        mediatorList.add(call_1);
        mediatorList.add(log_2);


        mediatorList.get(0).receive(new RxContext("context Init"));
        //mediatorList.get(0).receive(new RxContext("context Init"));
    }

}
