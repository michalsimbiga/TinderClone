package com.example.common_domain

import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module

abstract class ModuleLoader {
    private var modulesLoaded = false
    abstract val modules: List<Module>

    fun load() {
        if (modulesLoaded.not()) {
            loadKoinModules(modules)
            modulesLoaded = true
            onLoad()
        }
    }

    fun unload() {
        if (modulesLoaded) {
            unloadKoinModules(modules)
            modulesLoaded = false
            onUnLoad()
        }
    }

    open fun onLoad() {}

    open fun onUnLoad() {}
}
