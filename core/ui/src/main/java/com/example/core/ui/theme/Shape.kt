package com.example.core.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),   // chips, text fields, snackbars
    small      = RoundedCornerShape(8.dp),   // standard cards, list items
    medium     = RoundedCornerShape(12.dp),  // modal cards, elevated surfaces
    large      = RoundedCornerShape(16.dp),  // bottom sheets, large dialogs
    extraLarge = RoundedCornerShape(28.dp)   // side sheets, extra-large surfaces
)

// Convenience aliases for shapes outside the M3 scale
val ShapeFull      = CircleShape                                              // avatars, FABs
val ShapeTopMedium = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)   // bottom sheet inner containers
val ShapePill      = RoundedCornerShape(50.dp)                               // tags, badge containers

