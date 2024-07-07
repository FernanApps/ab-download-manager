package com.abdownloadmanager.desktop.utils.mvi

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

interface ContainsScreenState<ScreenState> {
    val state: StateFlow<ScreenState>
    fun setState(state:ScreenState)
}

class SupportsScreenState<ScreenState>(
    initialState:ScreenState
): ContainsScreenState<ScreenState> {
    private val _state = MutableStateFlow<ScreenState>(initialState)
    override val state = _state.asStateFlow()
    override fun setState(state: ScreenState) {
        _state.update { state }
    }
}


