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

/**
 * Created by milinda on 10/3/16.
 */
public abstract class AbstractMediator implements Mediator {

    Mediator nextMediator;

    @Override
    public boolean next(RxContext context) {
        return nextMediator == null || nextMediator.receive(context);
    }

    public Mediator getNextMediator() {
        return nextMediator;
    }

    public void setNextMediator(Mediator nextMediator) {
        this.nextMediator = nextMediator;
    }

    @Override
    public void error(RxContext context) {
        if (hasNext()) {
            nextMediator.error(context);
        } else {
            System.out.println("Handle Error");
        }
    }

    @Override
    public boolean hasNext() {
        return nextMediator != null;
    }
}
