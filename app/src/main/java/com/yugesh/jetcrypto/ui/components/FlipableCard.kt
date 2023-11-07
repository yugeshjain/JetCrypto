package com.yugesh.jetcrypto.ui.components

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex

@Composable
fun FlippableCard(
    frontSide: @Composable () -> Unit,
    backSide: @Composable () -> Unit,
    onFlipToBack: () -> Unit,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    flipDurationMs: Int = 400,
    cameraDistance: Float = 30.0F
) {
    var flippableState by remember { mutableStateOf(FlipState.FRONT) }
    val transition: Transition<FlipState> = updateTransition(
        targetState = flippableState,
        label = "Flip Transition",
    )

    val frontRotation: Float by transition.animateFloat(
        transitionSpec = {
            when {
                FlipState.FRONT isTransitioningTo FlipState.BACK -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        0f at 0
                        90f at flipDurationMs / 2
                        90f at flipDurationMs
                    }
                }

                FlipState.BACK isTransitioningTo FlipState.FRONT -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        90f at 0
                        90f at flipDurationMs / 2
                        0f at flipDurationMs
                    }
                }

                else -> snap()
            }
        },
        label = "Front Rotation"
    ) { state ->
        when (state) {
            FlipState.FRONT -> 0f
            FlipState.BACK -> 180f
        }
    }

    val backRotation: Float by transition.animateFloat(
        transitionSpec = {
            when {
                FlipState.FRONT isTransitioningTo FlipState.BACK -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        -90f at 0
                        -90f at flipDurationMs / 2
                        0f at flipDurationMs
                    }
                }

                FlipState.BACK isTransitioningTo FlipState.FRONT -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        0f at 0
                        -90f at flipDurationMs / 2
                        -90f at flipDurationMs
                    }
                }

                else -> snap()
            }
        },
        label = "Back Rotation"
    ) { state ->
        when (state) {
            FlipState.FRONT -> 180f
            FlipState.BACK -> 0f
        }
    }

    val frontOpacity: Float by transition.animateFloat(
        transitionSpec = {
            when {
                FlipState.FRONT isTransitioningTo FlipState.BACK -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        1f at 0
                        1f at (flipDurationMs / 2) - 1
                        0f at flipDurationMs / 2
                        0f at flipDurationMs
                    }
                }

                FlipState.BACK isTransitioningTo FlipState.FRONT -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        0f at 0
                        0f at (flipDurationMs / 2) - 1
                        1f at flipDurationMs / 2
                        1f at flipDurationMs
                    }
                }

                else -> snap()
            }
        },
        label = "Front Opacity"
    ) { state ->
        when (state) {
            FlipState.FRONT -> 1f
            FlipState.BACK -> 0f
        }
    }

    val backOpacity: Float by transition.animateFloat(
        transitionSpec = {
            when {
                FlipState.FRONT isTransitioningTo FlipState.BACK -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        0f at 0
                        0f at (flipDurationMs / 2) - 1
                        1f at flipDurationMs / 2
                        1f at flipDurationMs
                    }
                }

                FlipState.BACK isTransitioningTo FlipState.FRONT -> {
                    keyframes {
                        durationMillis = flipDurationMs
                        1f at 0
                        1f at (flipDurationMs / 2) - 1
                        0f at flipDurationMs / 2
                        0f at flipDurationMs
                    }
                }

                else -> snap()
            }
        },
        label = "Back Opacity"
    ) { state ->
        when (state) {
            FlipState.FRONT -> 0f
            FlipState.BACK -> 1f
        }
    }

    Box(
        modifier = modifier
            .clickable(
                onClick = {
                    flippableState = if (flippableState == FlipState.FRONT) {
                        onFlipToBack()
                        FlipState.BACK
                    } else {
                        FlipState.FRONT
                    }
                },
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ),
        contentAlignment = contentAlignment
    ) {

        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.cameraDistance = cameraDistance
                    rotationY = backRotation
                }
                .alpha(backOpacity)
                .zIndex(1F - backOpacity)
        ) {
            backSide()
        }

        Box(
            modifier = Modifier
                .graphicsLayer {
                    this.cameraDistance = cameraDistance
                    rotationY = frontRotation
                }
                .alpha(frontOpacity)
                .zIndex(1F - frontRotation)
        ) {
            frontSide()
        }
    }
}

enum class FlipState {
    FRONT,
    BACK
}
