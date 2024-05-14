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
 * 扩展点后置处理器，扩展初始化前后调用
 * A Post-processor called before or after extension initialization.
 *
 * @author huleilei9
 * @date 2024/05/14
 */
public interface ExtensionPostProcessor {

    /**
     * 初始化前 处理
     *
     * @param instance 例子
     * @param name     名称
     * @return {@link Object }
     * @throws Exception 例外
     */
    default Object postProcessBeforeInitialization(Object instance, String name) throws Exception {
        return instance;
    }

    /**
     * 初始化后 处理
     *
     * @param instance 例子
     * @param name     名称
     * @return {@link Object }
     * @throws Exception 例外
     */
    default Object postProcessAfterInitialization(Object instance, String name) throws Exception {
        return instance;
    }
}
