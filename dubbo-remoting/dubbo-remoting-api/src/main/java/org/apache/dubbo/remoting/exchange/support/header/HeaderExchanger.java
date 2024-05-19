/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.dubbo.remoting.exchange.support.header;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.Transporters;
import org.apache.dubbo.remoting.exchange.ExchangeClient;
import org.apache.dubbo.remoting.exchange.ExchangeHandler;
import org.apache.dubbo.remoting.exchange.ExchangeServer;
import org.apache.dubbo.remoting.exchange.Exchanger;
import org.apache.dubbo.remoting.exchange.PortUnificationExchanger;
import org.apache.dubbo.remoting.transport.DecodeHandler;

import static org.apache.dubbo.remoting.Constants.IS_PU_SERVER_KEY;

/**
 * DefaultMessenger
 *
 * @author huleilei9
 * @date 2024/05/19
 */
public class HeaderExchanger implements Exchanger {

    public static final String NAME = "header";

    /**
     * 连接
     *
     * @param url     url
     * @param handler 处理程序
     * @return {@link ExchangeClient}
     * @throws RemotingException RemotingException.(API,Prototype,ThreadSafe)
     */
    @Override
    public ExchangeClient connect(URL url, ExchangeHandler handler) throws RemotingException {
        return new HeaderExchangeClient(
                Transporters.connect(url, new DecodeHandler(new HeaderExchangeHandler(handler))), true);
    }

    /**
     * 绑定
     *
     * @param url     url
     * @param handler 处理程序
     * @return {@link ExchangeServer}
     * @throws RemotingException RemotingException.(API,Prototype,ThreadSafe)
     */
    @Override
    public ExchangeServer bind(URL url, ExchangeHandler handler) throws RemotingException {
        ExchangeServer server;
        boolean isPuServerKey = url.getParameter(IS_PU_SERVER_KEY, false);
        if (isPuServerKey) {
            server = new HeaderExchangeServer(
                    //多协议端口复用 https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/advanced-features-and-usage/service/port-unification/
                    //对原始 handler 又进行了两层包装
                    PortUnificationExchanger.bind(url, new DecodeHandler(new HeaderExchangeHandler(handler))));
        } else {
            server = new HeaderExchangeServer(
                    Transporters.bind(url, new DecodeHandler(new HeaderExchangeHandler(handler))));
        }
        return server;
    }
}
