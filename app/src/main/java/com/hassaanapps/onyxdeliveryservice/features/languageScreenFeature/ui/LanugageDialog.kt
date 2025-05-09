package com.hassaanapps.onyxdeliveryservice.features.languageScreenFeature.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.hassaanapps.onyxdeliveryservice.R
import com.hassaanapps.onyxdeliveryservice.ui.theme.PrimaryColor


@Composable
@Preview(
    showBackground = true, apiLevel = 33,
)
fun LanguageSelectionScreen(
    oldSelectedLang: String = "",
    onDismiss: () -> Unit = {},
    onLanguageSelected: (String) -> Unit = {}
) {

    var selectedLanguage by remember { mutableStateOf(oldSelectedLang) }

    Dialog(onDismissRequest = onDismiss) {

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .background(color = Color(0xFFF4F4F4)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose Language",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LanguageOption(
                    languageName = "العربية",
                    languageLabel = "Arabic",
                    isSelected = selectedLanguage == "1",
                    onClick = { selectedLanguage = "1" },
                    modifier = Modifier.weight(1f),
                    languageFlag = R.drawable.ic_arabic_lang_flag
                )

                LanguageOption(
                    languageName = "English",
                    languageLabel = "English",
                    isSelected = selectedLanguage == "2",
                    onClick = { selectedLanguage = "2" },
                    modifier = Modifier.weight(1f),
                    languageFlag = R.drawable.ic_english_lang_flag
                )
            }


            Spacer(Modifier.height(15.dp))

            Button(
                modifier =
                Modifier.fillMaxWidth(0.9f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ),
                onClick = {
                    onLanguageSelected(selectedLanguage)
                    onDismiss()
                }) {
                Text("Apply")
            }

        }


    }

}




@Composable
fun LanguageOption(
    modifier: Modifier,
    languageName: String,
    languageLabel: String,
    isSelected: Boolean,
    @DrawableRes languageFlag: Int,
    onClick: () -> Unit
) {
    val borderColor = if (isSelected) Color(0xFF39A238) else Color.White
    val backgroundColor =
        if (isSelected) Color(0xFFCBFFCB) else Color.Transparent

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(16.dp))
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(languageFlag),
                contentDescription = "Arabic Language Flag",
                modifier = Modifier.size(25.dp) // optional size
            )

            Spacer(modifier = Modifier.width(12.dp)) // spacing between image and text

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = languageName,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = languageLabel,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }

    }

}
