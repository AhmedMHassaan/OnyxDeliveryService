package com.hassaanapps.onyxdeliveryservice.features.loginFeature.ui

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.features.splashScreenFeature.ui.OnyxServiceLogo
import com.hassaanapps.onyxdeliveryservice.ui.nav.ScreensDestinations
import com.hassaanapps.onyxdeliveryservice.ui.theme.MontserratFontFamily


@Composable
fun TopCircleImage(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_circle_top),
        contentDescription = "Logo",
        modifier = modifier
    )
}

@Composable
fun LanguageIcon(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(modifier = modifier, onClick = onClick) {
        Icon(
            painter = painterResource(id = R.drawable.ic_language),
            contentDescription = "Language icon",
            tint = Color.White
        )
    }
}


@Composable
fun UserIdTextField(
    userId: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    placeholderText: String,
) {
    LoginTextField(
        value = userId,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholderText = placeholderText,
        passwordVisible = null,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )

    /*OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                text = placeholderText,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MontserratFontFamily,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF1F5FB),
            focusedContainerColor = Color(0xFFF1F5FB),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent
        ),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )*/
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    passwordVisible: MutableState<Boolean>
) {
    LoginTextField(
        value = value,
        modifier = modifier,
        onValueChange = onValueChange,
        placeholderText = "Password",
        passwordVisible = passwordVisible,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onShowMoreClick: () -> Unit = {},
    onLanguageIconClicked: () -> Unit = {},
    navHostController: NavHostController

) {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val passwordVisible: MutableState<Boolean> = remember { mutableStateOf(false) }

    val context: Context = LocalContext.current


    with(loginViewModel) {

        if (loadingState.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
        }
        successLoginState.value?.let {
            OnLoginSuccess(it)
            navHostController.navigate(ScreensDestinations.Home.route) {
                popUpTo(ScreensDestinations.Login.route) {
                    inclusive = true
                }
            }
        }

        errorState.collectAsState(initial = null).value?.let {
            onErrorHappened(context, it)
        }

    }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            LoginTopHeader(onLanguageIconClicked)


            Spacer(modifier = Modifier.fillMaxHeight(.3f))


            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Welcome Back!",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MontserratFontFamily,
                color = Color(0xFF01475B)
            )

            Text(
                fontSize = 16.sp,
                text = "Log back into your account",
                fontFamily = MontserratFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            UserIdTextField(
                userId = userId,
                onValueChange = { userId = it },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                placeholderText = "User ID",
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .align(Alignment.CenterHorizontally),
                passwordVisible = passwordVisible,
            )


            Spacer(modifier = Modifier.height(8.dp))

            TextButton(
                onClick = onShowMoreClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    text = "Show More",
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            val keyboardController = LocalSoftwareKeyboardController.current
            Button(
                onClick = {
                    keyboardController?.hide()

                    loginViewModel.login(userId, password, "1")
                },
                modifier = Modifier
                    .fillMaxWidth(.9f)
                    .wrapContentHeight()
                    .align(Alignment.CenterHorizontally),
                enabled = loginViewModel.loadingState.value.not(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = "Log in",
                    fontFamily = MontserratFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }


            Spacer(modifier = Modifier.height(40.dp))

            Image(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                painter = painterResource(R.drawable.ic_delivery_truck),
                contentDescription = "Truck logo"
            )
        }
    }
}


fun onErrorHappened(context: Context, error: String) {
    Log.d("APP_TAG", " LoginScreen - OnErrorHappened: error is $error")
    Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
}

@Composable
private fun OnLoginSuccess(deliveryName: String) {
    Toast.makeText(LocalContext.current, "Login Success $deliveryName", Toast.LENGTH_SHORT).show()
}

@Composable
fun LoginTopHeader(onLanguageIconClicked: () -> Unit = {}) {

    Box(modifier = Modifier.fillMaxWidth(), Alignment.TopEnd) {

        TopCircleImage(
            modifier = Modifier
                .zIndex(1f)
        )

        LanguageIcon(
            onClick = onLanguageIconClicked,
            modifier = Modifier
                .zIndex(2f)
                .padding(top = 51.dp, end = 16.dp)
        )



        OnyxServiceLogo(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.BottomStart)
                .padding(top = 56.dp, start = 26.dp)
        )
    }


}

@Composable
fun LoginTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    passwordVisible: MutableState<Boolean>?,
    keyboardOptions: KeyboardOptions,

    ) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                text = placeholderText,
                fontWeight = FontWeight.SemiBold,
                fontFamily = MontserratFontFamily,
                textAlign = TextAlign.Center
            )
        },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(32.dp),
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        ),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color(0xFFF1F5FB),
            focusedContainerColor = Color(0xFFF1F5FB),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            disabledBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent
        ),
        visualTransformation = if (passwordVisible?.value == true) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            if (passwordVisible != null) {
                IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                    Icon(
                        imageVector = if (passwordVisible.value) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible.value) "Hide password" else "Show password"
                    )
                }
            }

        }
    )
}


