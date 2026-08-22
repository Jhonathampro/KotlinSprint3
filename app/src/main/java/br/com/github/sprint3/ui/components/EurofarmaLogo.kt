package br.com.github.sprint3.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow

@Composable
fun EurofarmaLogo(
    modifier: Modifier = Modifier,
    size: Dp = 160.dp,
    circleColor: Color = EuroBlue,
    accentColor: Color = EuroYellow
) {
    Canvas(modifier = modifier.size(size)) {
        val canvasWidth = this.size.width
        val canvasHeight = this.size.height
        val radius = canvasWidth / 2f
        val center = Offset(canvasWidth / 2f, canvasHeight / 2f)

        // Dark blue circle background
        drawCircle(
            color = circleColor,
            radius = radius,
            center = center
        )

        // Yellow dot (top of "i" / smiling face dot)
        val dotRadius = radius * 0.17f
        val dotCenter = Offset(canvasWidth * 0.44f, canvasHeight * 0.30f)
        drawCircle(
            color = accentColor,
            radius = dotRadius,
            center = dotCenter
        )

        // Yellow vertical bar ("i" body)
        val barWidth = canvasWidth * 0.17f
        val barHeight = canvasHeight * 0.38f
        val barLeft = canvasWidth * 0.355f
        val barTop = canvasHeight * 0.48f
        drawRoundRect(
            color = accentColor,
            topLeft = Offset(barLeft, barTop),
            size = Size(barWidth, barHeight),
            cornerRadius = CornerRadius(barWidth * 0.15f)
        )

        // Yellow right arcs (Eurofarma emblem shape)
        val strokeWidth = canvasWidth * 0.13f
        val arcMargin = canvasWidth * 0.10f
        val arcSize = Size(canvasWidth - (arcMargin * 2), canvasHeight - (arcMargin * 2))
        val arcTopLeft = Offset(arcMargin, arcMargin)

        // Top arc segment
        drawArc(
            color = accentColor,
            startAngle = -50f,
            sweepAngle = 50f,
            useCenter = false,
            topLeft = arcTopLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth)
        )

        // Bottom arc segment
        drawArc(
            color = accentColor,
            startAngle = 5f,
            sweepAngle = 50f,
            useCenter = false,
            topLeft = arcTopLeft,
            size = arcSize,
            style = Stroke(width = strokeWidth)
        )
    }
}
