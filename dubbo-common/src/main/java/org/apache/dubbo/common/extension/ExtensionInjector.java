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
package org.apache.dubbo.common.extension;

/**
 * 为SPI扩展提供 依赖注入
 * An injector to provide resources for SPI extension.
 *
 * 针对不同的注入范围，有四种实现
 *
 * @author huleilei9
 * @date 2024/05/18
 */
@SPI(scope = ExtensionScope.SELF)
public interface ExtensionInjector extends ExtensionAccessorAware {

    /**
     * Get instance of specify type and name.
     *
     * @param type object type.
     * @param name object name.
     * @return object instance.
     */
    <T> T getInstance(final Class<T> type, final String name);

    @Override
    default void setExtensionAccessor(final ExtensionAccessor extensionAccessor) {}
}
