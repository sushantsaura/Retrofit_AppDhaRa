package com.example.retrofit_appdhara.presentation.screen.product.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.retrofit_appdhara.domain.model.Product


@Composable
fun ProductList(
    product : List<Product>,
    onEdit: (Product) -> Unit,
    onPatch: (Product) -> Unit,
    onDelete: (Product) -> Unit,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(product,key = {it.id}){ product ->
            ProductCard(
                product = product,
                onEdit = { onEdit(product)},
                onDelete = {onDelete(product)},
                onPatch = {onPatch(product)}
            )
        }
    }

}
@Composable
fun ProductCard(
    product: Product,
    onEdit: () -> Unit,
    onDelete : () -> Unit,
    onPatch : () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            AsyncImage(
                model = product.image,
                contentDescription = "Image of product",
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Fit
            )

            Column(Modifier.weight(1f)) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${product.price}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {

                IconButton(onClick = onEdit) {
                    Icon(Icons.Default.Edit, "Edit (PUT)", tint = MaterialTheme.colorScheme.primary)
                }
                IconButton(onClick = onPatch) {
                    Icon(Icons.Default.Add, "Patch", tint = MaterialTheme.colorScheme.secondary)
                }
                IconButton(onClick = onDelete) {
                    Icon(Icons.Default.Delete, "Delete", tint = MaterialTheme.colorScheme.error)
                }
            }
        }
    }

}