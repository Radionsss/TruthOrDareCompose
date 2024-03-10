package com.example.truthordarecompose.presentation.onboarding


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.truthordarecompose.R


data class Page(
    val title: String,
    val description: String,
    val image: Int,
)

val pages = listOf(
    Page(
        title = "Lorem Ipsum is simply dummy 1",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.raw.hello_animaton
    ),
    Page(
        title = "Lorem Ipsum is simply dummy 2",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.raw.second_animation
    ),
    Page(
        title = "Lorem Ipsum is simply dummy 3",
        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
        image = R.raw.hello_animaton
    )
)

@Composable
fun BoardingAnimation(modifier: Modifier, image: Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(image))
    val progresss = animateLottieCompositionAsState(composition = composition)
    val progress = LottieClipSpec.Progress(0f,0.4f)
    LottieAnimation(
        composition = composition,
        clipSpec = progress,
        iterations = 1,
        modifier = modifier
    )

}