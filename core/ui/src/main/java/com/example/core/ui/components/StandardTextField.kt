package com.example.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.core.ui.theme.DIGIWalletTheme

@Composable
fun StandardTextField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    modifier : Modifier = Modifier,
    isError: Boolean = false,
    errorMessage: String? = null,
    maxLines: Int = 1,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = if(isPassword) KeyboardType.Password else KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default
){
    val capitalization = when (keyboardType){
        KeyboardType.Email,
        KeyboardType.Password,
        KeyboardType.NumberPassword,
        KeyboardType.Uri, -> KeyboardCapitalization.None
        else -> KeyboardCapitalization.Sentences
    }

    val autoCorrect = keyboardType !in listOf(
        KeyboardType.Email,
        KeyboardType.Password,
        KeyboardType.NumberPassword,
        KeyboardType.Number,
        KeyboardType.Phone,
        KeyboardType.Uri
    )

    Column(
        modifier = modifier
    ){
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            label = {Text(hint)},
            isError = isError,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraSmall,
            visualTransformation = if(isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction,
                capitalization = capitalization,
                autoCorrect = autoCorrect
            ),
            keyboardActions = keyboardActions
        )
        if(isError && errorMessage != null){
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(
                    start = DIGIWalletTheme.spacing.xxs,
                    top = DIGIWalletTheme.spacing.xxs
                )
            )
        }
    }
}