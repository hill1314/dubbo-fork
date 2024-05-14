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
 * 提供针对扩展点的统一访问
 * Uniform accessor for extension
 *
 * @author huleilei9
 * @date 2024/05/14
 */
public interface ExtensionAccessor {

    /**
     * 获取扩展控制器
     *
     * @return {@link ExtensionDirector }
     */
    ExtensionDirector getExtensionDirector();

    /**
     * 获取扩展加载程序
     *
     * @param type 类型
     * @return {@link ExtensionLoader }<{@link T }>
     */
    default <T> ExtensionLoader<T> getExtensionLoader(Class<T> type) {
        return this.getExtensionDirector().getExtensionLoader(type);
    }

    /**
     * 获取扩展
     *
     * @param type 类型
     * @param name 名称
     * @return {@link T }
     */
    default <T> T getExtension(Class<T> type, String name) {
        ExtensionLoader<T> extensionLoader = getExtensionLoader(type);
        return extensionLoader != null ? extensionLoader.getExtension(name) : null;
    }

    /**
     * 获得自适应扩展
     *
     * @param type 类型
     * @return {@link T }
     */
    default <T> T getAdaptiveExtension(Class<T> type) {
        ExtensionLoader<T> extensionLoader = getExtensionLoader(type);
        return extensionLoader != null ? extensionLoader.getAdaptiveExtension() : null;
    }

    /**
     * 获取默认扩展名
     *
     * @param type 类型
     * @return {@link T }
     */
    default <T> T getDefaultExtension(Class<T> type) {
        ExtensionLoader<T> extensionLoader = getExtensionLoader(type);
        return extensionLoader != null ? extensionLoader.getDefaultExtension() : null;
    }
}
