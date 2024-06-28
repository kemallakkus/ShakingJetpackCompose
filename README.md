# Shaking Button Animation in Jetpack Compose

This project demonstrates how to create a dynamic button shaking animation using Jetpack Compose. The button shakes in different directions and strengths to enhance user interaction.

## Features

- Shake in various directions: `LEFT`, `RIGHT`, `LEFT_THEN_RIGHT`, `RIGHT_THEN_LEFT`
- Customize the strength of the shake: `Normal`, `Strong`, `Custom`

## Demo

Check out the video below to see the shaking button in action:

[shaking_black.webm](https://github.com/kemallakkus/ShakingJetpackCompose/assets/105845393/dc2de8ae-9888-47ed-a374-782769fedaf0)


## Getting Started

Follow these instructions to get a copy of the project up and running on your local machine.

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/kemallakkus/ShakingJetpackCompose
    ```
2. Open the project in Android Studio.
3. Build and run the project on an emulator or a physical device.

## Usage

### Customize Shaking Settings

You can customize the shaking settings by modifying the `rememberShakingState` function in the `MainActivity`:

```kotlin
val shakingState = rememberShakingState(
    strength = ShakingState.Strength.Custom(100f),
    direction = ShakingState.Directions.LEFT_THEN_RIGHT
)
```


### Strength Options:
`ShakingState.Strength.Normal:` Default strength (17f)
`ShakingState.Strength.Strong:` Stronger shake (40f)
`ShakingState.Strength.Custom(value):` Custom strength, where value is a float.

### Direction Options:
`ShakingState.Directions.LEFT:` Shake to the left
`ShakingState.Directions.RIGHT:` Shake to the right
`ShakingState.Directions.LEFT_THEN_RIGHT:` Shake left then right
`ShakingState.Directions.RIGHT_THEN_LEFT:` Shake right then left

## Example

Here is an example of how to apply the shaking effect to a button:

```kotlin
Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
    val shakingState = rememberShakingState(
        strength = ShakingState.Strength.Custom(100f),
        direction = ShakingState.Directions.LEFT_THEN_RIGHT
    )
    val scope = rememberCoroutineScope()

    TextButton(
        onClick = {
            scope.launch {
                shakingState.shake(animationDuration = 30)
            }
        },
        modifier = Modifier.shakable(shakingState)
    ) {
        Text(text = "Shakiiing !!", fontSize = 22.sp)
    }
}

```

