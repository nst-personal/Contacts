package com.example.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contacts.data.dto.Contact
import com.example.contacts.ui.theme.ContactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ContactDetails(Contact(
                        name = "Name",
                        surname = "Surname",
                        familyName = "FamiltyName",
                        phone = "+1 111 111 11 11",
                        address = "City, country",
                        email = "mail@email.com",
                        isFavorite = true,
                        imageRes = R.drawable.ic_launcher_foreground
                    ))
                }
            }
        }
    }
}

@Composable
fun InfoRow(title: String, value: String) {
    Row{
        Column(
           Modifier.weight(0.5F)
               .padding(2.dp)
               .padding(2.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "${title}: ",
                style = TextStyle(
                    fontStyle = FontStyle.Italic
                )
            )
        }
        Column(
            Modifier.weight(0.5F)
                .padding(2.dp)
                .padding(2.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text("${value}")
        }
    }
}

@Composable
fun ContactColumn(contact: Contact) {
    Column {
        InfoRow(stringResource(R.string.phone), contact.phone)
        InfoRow(stringResource(R.string.address), contact.address)
        if (contact.email != null) {
            InfoRow(stringResource(R.string.email), contact.email)
        }
    }
}

@Composable
fun ContactImage(contact: Contact) {
    contact.imageRes?.let { painterResource(id = it) }?.let {
        Image(
            modifier = Modifier
                .padding(8.dp)
                .padding(8.dp),
            painter = it,
            contentDescription = contact.name,
        )
    }
}

@Composable
fun ContactInitialsImage(contact: Contact) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null,
        )
        Text(
            text = "${contact.name.take(1)} ${contact.familyName.take(1)}"
        )
    }
}


@Composable
fun ContactInitials(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            style = MaterialTheme.typography.h6,
            text = "${contact.name} ${contact.surname.orEmpty()}"
        )
        Row(
            modifier = Modifier.padding(end = 4.dp, bottom = 36.dp)
        ) {
            Text(
                style = MaterialTheme.typography.h5, text = contact.familyName
            )
            if (contact.isFavorite) Image(
                modifier = Modifier.padding(start = 16.dp),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Box {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .padding(8.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (contact.imageRes == null)
                        ContactInitialsImage(contact)
                    if (contact.imageRes != null)
                        ContactImage(contact)
                    ContactInitials(contact)
                    ContactColumn(contact)
                }
            }
        }
    }
}

@Composable
@Preview(showSystemUi = true)
@Preview(showSystemUi = false)
@Preview(name = "portrait", showSystemUi = true)
fun ContactDetailsPreview() {
    ContactDetails(
        contact = Contact(
            name = "Name",
            surname = "Surname",
            familyName = "FamiltyName",
            phone = "+1 111 111 11 11",
            address = "City, country",
            email = "mail@email.com",
            isFavorite = true,
        )
    )
}

@Composable
@Preview(showSystemUi = true)
@Preview(showSystemUi = false)
@Preview(name = "portrait", showSystemUi = true)
@Preview(showSystemUi = true)
@Preview(showSystemUi = false)
fun ContactDetailsNoImagePreview() {
    ContactDetails(
        contact = Contact(
            name = "Name",
            surname = "Surname",
            familyName = "FamiltyName",
            phone = "+1 111 111 11 11",
            address = "City, country ",
            isFavorite = false,
            imageRes = R.drawable.ic_launcher_foreground
        )
    )
}
