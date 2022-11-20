package com.niqr.magicbutton.ui.screens.magick.utils

import androidx.paging.PagingConfig

val pagingConfig = PagingConfig(
    pageSize = 1,
    prefetchDistance = 30,
    initialLoadSize = 50,
    maxSize = 150,
    jumpThreshold = 20
)