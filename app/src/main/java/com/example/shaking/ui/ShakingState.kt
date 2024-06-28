package com.example.shaking.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

class ShakingState(
    private val strength: Strength,
    private val direction: Directions,
) {

    val xPosition = Animatable(0f)

    suspend fun shake(animationDuration: Int = 50) {

        val shakeAnimationSpec: AnimationSpec<Float> = tween(animationDuration)

        when (direction) {
            Directions.LEFT -> performShake(shakeAnimationSpec, -strength.value)
            Directions.RIGHT -> performShake(shakeAnimationSpec, strength.value)
            Directions.LEFT_THEN_RIGHT -> performShakeSequence(shakeAnimationSpec, -strength.value, strength.value / 2)
            Directions.RIGHT_THEN_LEFT -> performShakeSequence(shakeAnimationSpec, strength.value, -strength.value / 2)
        }
    }

    private suspend fun performShake(shakeAnimationSpec: AnimationSpec<Float>, targetValue: Float) {
        repeat(3) {
            xPosition.animateTo(targetValue, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    private suspend fun performShakeSequence(
        shakeAnimationSpec: AnimationSpec<Float>,
        firstTargetValue: Float,
        secondTargetValue: Float,
    ) {
        repeat(3) {
            xPosition.animateTo(firstTargetValue, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
            xPosition.animateTo(secondTargetValue, shakeAnimationSpec)
            xPosition.animateTo(0f, shakeAnimationSpec)
        }
    }

    sealed class Strength(val value: Float) {
        data object Normal : Strength(17f)
        data object Strong : Strength(40f)
        data class Custom(val customValue: Float) : Strength(customValue)
    }

    enum class Directions {
        LEFT, RIGHT, LEFT_THEN_RIGHT, RIGHT_THEN_LEFT
    }
}

@Composable
fun rememberShakingState(
    strength: ShakingState.Strength = ShakingState.Strength.Normal,
    direction: ShakingState.Directions = ShakingState.Directions.LEFT,
): ShakingState {
    return remember { ShakingState(strength, direction) }
}

fun Modifier.shakable(
    state: ShakingState,
): Modifier {
    return graphicsLayer {
        translationX = state.xPosition.value
    }
}
