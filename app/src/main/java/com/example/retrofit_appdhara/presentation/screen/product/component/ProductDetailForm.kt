package com.example.retrofit_appdhara.presentation.screen.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.retrofit_appdhara.domain.model.Product
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@Composable
fun ProductDetailForm(
    title : String,
    product: Product? = null,
    onDismiss : () -> Unit,
    onSave : (Product) -> Unit
) {

    var titleText by remember { mutableStateOf(product?.title ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var category by remember { mutableStateOf(product?.category ?: "") }
    var image by remember { mutableStateOf(product?.image ?: "") }

    LaunchedEffect(product) {
        titleText = product?.title ?: ""
        price = product?.price?.toString() ?: ""
        description = product?.description ?: ""
        category = product?.category ?: ""
        image = product?.image ?: ""

    }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title)},
        text ={
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = titleText,
                    onValueChange = {titleText = it},
                    label = { Text("Title")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = price,
                    onValueChange = {price = it},
                    label = { Text("Price")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = {category = it},
                    label = { Text("=Category")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    label = { Text("Description")},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = image,
                    onValueChange = {image = it},
                    label = { Text("Image")},
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newProduct = Product(
                        id = product?.id ?: 0,
                        title = titleText,
                        price = price.toDoubleOrNull() ?: 0.0,
                        description = description,
                        category = category,
                        image = image
                    )

                    onSave(newProduct)
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton( onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )

}