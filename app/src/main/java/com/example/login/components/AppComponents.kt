package com.example.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.login.R

@Composable
fun NormalText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 20.sp,
            color = colorResource(id = R.color.colorText),
            fontWeight = FontWeight.SemiBold
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Spacer(value: Dp = 10.dp) {
    androidx.compose.foundation.layout.Spacer(modifier = Modifier.height(value))
}

@Composable
fun BoldText(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth(),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.colorText)
        ),
        textAlign = TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    icon: ImageVector,
    label: String,
    onValueChanged: (String) -> Unit
) {
    var textState by remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = {
            textState = it
            onValueChanged(it)
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        label = { Text(text = label) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = label, tint = Color.Black)
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 20.sp
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    icon: ImageVector,
    label: String,
    onValueChanged: (String) -> Unit
) {
    var textState by remember {
        mutableStateOf("")
    }
    var isVisible by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = textState,
        onValueChange = {
            textState = it
            onValueChanged(it)
        },
        label = {
            Text(
                text = label
            )
        },
        textStyle = TextStyle(
            fontSize = 20.sp,
            color = Color.Black
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color.Black
            )
        },
        trailingIcon = {
            val iconImg = if (isVisible) R.drawable.visibility else R.drawable.visible_off
            val description =
                if (isVisible) stringResource(id = R.string.hide_password) else stringResource(id = R.string.show_password)
            IconButton(onClick = {
                isVisible = !isVisible
            }) {
                Icon(
                    painter = painterResource(id = iconImg),
                    contentDescription = description,
                    tint = Color.Black
                )
            }
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(
            mask = '*'
        )
    )
}

@Composable
fun TermsAndConditions(
    onCheckBoxClicked: (Boolean) -> Unit,
    onTextClicked: (String) -> Unit
) {
    var isCheckBoxClicked by remember {
        mutableStateOf(false)
    }
    //creating annotated string
    val initialString = stringResource(id = R.string.terms_and_conditions)
    val privacyPolicy = stringResource(id = R.string.privacy_policy)
    val termsOfUse = stringResource(id = R.string.terms_of_use)
    val termsAndConditionsStr = buildAnnotatedString {
        append(initialString)
        append(" ")
        withStyle(style = SpanStyle(color = Color.Blue)) {
            run {
                pushStringAnnotation(tag = privacyPolicy, annotation = privacyPolicy)
            }
            append(privacyPolicy)
        }

        append(" and ")
        withStyle(style = SpanStyle(color = Color.Blue)) {
            pushStringAnnotation(tag = termsOfUse, annotation = termsOfUse)
            append(termsOfUse)
        }
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Checkbox(
            checked = isCheckBoxClicked, onCheckedChange = {
                isCheckBoxClicked = !isCheckBoxClicked
                onCheckBoxClicked(isCheckBoxClicked)
            })
        ClickableText(
            text = termsAndConditionsStr, onClick = { offSet ->
                termsAndConditionsStr.getStringAnnotations(offSet, offSet)
                    .firstOrNull()?.also { span ->
                        if (span.item == termsOfUse || span.item == privacyPolicy) {
                            onTextClicked(span.item)
                        }
                    }
            })
    }
}

@Composable
fun ButtonComposable(
    text: String,
    icon: ImageVector? = null,
    onButtonClicked: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        colorResource(id = R.color.colorSecondary),
                        colorResource(id = R.color.colorPrimary)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            ),
        onClick = {
            onButtonClicked.invoke()
        }) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.Black,
                modifier = Modifier.padding(end = 10.dp)
            )
        }
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

@Composable
fun DividerComposable() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            modifier = Modifier
                .width(1.dp)
                .background(colorResource(id = R.color.colorGray))
                .weight(1f)
        )
        Text(
            text = "Or",
            color = colorResource(id = R.color.colorText),
            modifier = Modifier.padding(10.dp)
        )
        Divider(
            modifier = Modifier
                .width(1.dp)
                .background(colorResource(id = R.color.colorGray))
                .weight(1f)
        )
    }
}

@Composable
fun ClickableText(
    displayText: String,
    clickableText: String,
    onClick: (String) -> Unit
) {
    val usedStr = buildAnnotatedString {
        append(displayText)
        withStyle(style = SpanStyle(color = Color.Blue)) {
            pushStringAnnotation(tag = clickableText, annotation = clickableText)
            append(" ")
            append(clickableText)
        }
    }

    ClickableText(
        modifier = Modifier.fillMaxWidth(),
        text = usedStr, onClick = { offSet ->
            usedStr.getStringAnnotations(offSet, offSet)
                .firstOrNull()?.also { span ->
                    if (span.item == clickableText) {
                        onClick(clickableText)
                    }
                }
        },
        style = TextStyle(
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    )
}

@Composable
fun ForgotPasswordComposable(
    text: String,
    onClick: () -> Unit
) {
    val annotatedStr = buildAnnotatedString {
        withStyle(style = SpanStyle(color = Color.Blue)) {
            append(text)
        }
    }
    ClickableText(
        text = annotatedStr,
        onClick = {
            onClick()
        },
        style = TextStyle(
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        ),
        modifier = Modifier.fillMaxWidth(),
    )
}