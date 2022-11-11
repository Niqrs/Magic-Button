package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

open class ColorGeneratorImpl(
    private val storeColorGenerationPreferences: StoreColorGenerationPreferences
) : ColorGenerator {
    private val _colorGenerator = MutableStateFlow(StoreColorGenerationPreferences.defaultGenerator())
    override val colorGenerator = _colorGenerator.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch { // Update color generator on each preferences change
            storeColorGenerationPreferences.getPreferences.onEach { newGenerator ->
                _colorGenerator.value = newGenerator
            }.collect()
        }
    }

    override fun updateColorGenerator(magickColorGenerator: MagickColorGenerator) {
        CoroutineScope(Dispatchers.IO).launch {
            storeColorGenerationPreferences.savePreferences(magickColorGenerator)
        }
    }
}

