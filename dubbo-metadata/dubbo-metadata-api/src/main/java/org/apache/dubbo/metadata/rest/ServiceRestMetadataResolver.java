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
package org.apache.dubbo.metadata.rest;

import org.apache.dubbo.common.extension.ExtensionScope;
import org.apache.dubbo.common.extension.SPI;

/**
 * rest注解解析器
 * https://cn.dubbo.apache.org/zh-cn/overview/reference/proposals/protocol-http/#rest%E6%B3%A8%E8%A7%A3%E8%A7%A3%E6%9E%90
 * The interface to resolve the {@link ServiceRestMetadata REST metadata} from the specified
 * <p>
 * <p>
 * Dubbo Service interface or type.
 *
 * @author huleilei9
 * @date 2024/05/16
 */
@SPI(scope = ExtensionScope.APPLICATION)
public interface ServiceRestMetadataResolver {

    /**
     * Support to resolve {@link ServiceRestMetadata REST metadata} or not
     *
     * @param serviceType Dubbo Service interface or type
     * @return If supports, return <code>true</code>, or <code>false</code>
     */
    boolean supports(Class<?> serviceType);

    boolean supports(Class<?> serviceType, boolean consumer);

    /**
     * Resolve the {@link ServiceRestMetadata REST metadata} from the specified
     * Dubbo Service interface or type
     *
     * @param serviceType Dubbo Service interface or type
     * @return
     */
    ServiceRestMetadata resolve(Class<?> serviceType);

    ServiceRestMetadata resolve(Class<?> serviceType, ServiceRestMetadata serviceRestMetadata);
}
