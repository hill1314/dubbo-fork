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
package org.apache.dubbo.common.compiler.support;

import org.apache.dubbo.common.compiler.Compiler;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.rpc.model.FrameworkModel;
import org.apache.dubbo.rpc.model.ScopeModelAware;

/**
 * Compiler 的自适应扩展点
 * AdaptiveCompiler. (SPI, Singleton, ThreadSafe)
 *
 * @author huleilei9
 * @date 2024/05/18
 */
@Adaptive
public class AdaptiveCompiler implements Compiler, ScopeModelAware {
    private FrameworkModel frameworkModel;

    @Override
    public void setFrameworkModel(FrameworkModel frameworkModel) {
        this.frameworkModel = frameworkModel;
    }

    private static volatile String DEFAULT_COMPILER;

    public static void setDefaultCompiler(String compiler) {
        DEFAULT_COMPILER = compiler;
    }

    @Override
    public Class<?> compile(Class<?> neighbor, String code, ClassLoader classLoader) {
        Compiler compiler;
        ExtensionLoader<Compiler> loader = frameworkModel.getExtensionLoader(Compiler.class);
        // copy reference
        String name = DEFAULT_COMPILER;
        if (name != null && name.length() > 0) {
            //名称不为空时，根据名称获取 扩展点实现
            compiler = loader.getExtension(name);
        } else {
            //为空时，取默认实现
            compiler = loader.getDefaultExtension();
        }

        return compiler.compile(neighbor, code, classLoader);
    }
}
