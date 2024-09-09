package com.sergiodev.financeControl.financialRecord.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergiodev.financeControl.R
import com.sergiodev.financeControl.core.enums.TransactionType
import com.sergiodev.financeControl.core.extension.noRippleClickable
import com.sergiodev.financeControl.core.extension.toFormattedString
import com.sergiodev.financeControl.core.extension.toMoneyFormat
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import com.sergiodev.financeControl.ui.theme.BackApp
import com.sergiodev.financeControl.ui.theme.Green40
import com.sergiodev.financeControl.ui.theme.Grey40
import com.sergiodev.financeControl.ui.theme.WhiteApp
import java.util.Date


@Composable
fun FinancialRecordScreen() {

    var isShowModalAdd by rememberSaveable { mutableStateOf(false) }
    
    Scaffold(
        floatingActionButton = {
            FABFinancialRecordScreen {
                isShowModalAdd = !isShowModalAdd
            }
        },
        topBar = { TopBarFinancialRecordScreen() }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            FinancialRecordList()
            AddModalBottomSheet(
                isShow = isShowModalAdd,
                onAddModalBottomSheetDismiss = {
                    isShowModalAdd = !isShowModalAdd
                },
                onAddModalBottomSheetAdd = {
                    isShowModalAdd = !isShowModalAdd
                })

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarFinancialRecordScreen() {

    TopAppBar(title = {
        Text(text = stringResource(R.string.financial_record_screen_title))
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Green40, titleContentColor = WhiteApp))
}


@Composable
fun FABFinancialRecordScreen(onFABClick: () -> Unit) {

    FloatingActionButton(onClick = {
        onFABClick()
    }, containerColor = Grey40) {
        Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.financial_record_screen_title))
    }
}

@Composable
fun FinancialRecordList() {

    val financialRecordList: List<FinancialRecordModel> = listOf(
        FinancialRecordModel(description = "Pago nomina mes de agosto", nature = TransactionType.DEBIT, amount = 4000000.0, keys = listOf("Nomina"), date = Date()),
        FinancialRecordModel(description = "Almuerzo 08/02/2024 con familia", nature = TransactionType.CREDIT, amount = 200000.0, keys = listOf("Casa", "Comida"), date = Date())
    )

    LazyColumn {

        itemsIndexed(financialRecordList) { index, financialRecord ->

            FinancialRecordItem(item = financialRecord)
            if (index < financialRecordList.lastIndex)
                HorizontalDivider(color = Grey40, thickness = 0.5.dp)
        }
    }
}

@Composable
fun FinancialRecordItem(item: FinancialRecordModel) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 8.dp)
    ) {
        HeaderFinancialRecordItem(item = item)
        BodyFinancialRecordItem(item = item)
        FooterFinancialRecordItem(item = item)
    }

}

@Composable
fun HeaderFinancialRecordItem(item: FinancialRecordModel) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            item.date.toFormattedString(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),

            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = BackApp
        )

        Text(
            item.amount.toMoneyFormat(item.nature), modifier = Modifier
                .padding(horizontal = 18.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (item.nature == TransactionType.DEBIT) Green40 else Color.Red

        )
    }
}

@Composable
fun BodyFinancialRecordItem(item: FinancialRecordModel) {

    Text(
        item.description, modifier = Modifier
            .padding(horizontal = 16.dp),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = BackApp

    )

}

@Composable
fun FooterFinancialRecordItem(item: FinancialRecordModel) {

    LazyRow {
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
        items(item.keys) { key ->
            AssistChip(
                label = { Text(key) },
                colors = AssistChipDefaults.assistChipColors(containerColor = Grey40, labelColor = WhiteApp),
                onClick = {},
                modifier = Modifier.padding(end = 8.dp)
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModalBottomSheet(isShow: Boolean, onAddModalBottomSheetDismiss: () -> Unit, onAddModalBottomSheetAdd: () -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var isShowDatePicker by rememberSaveable { mutableStateOf(false) }
    var edtDate: String by rememberSaveable { mutableStateOf("") }
    var edtAmount: String by rememberSaveable { mutableStateOf("") }
    var edtDescription: String by rememberSaveable { mutableStateOf("") }

    if (isShow) {
        ModalBottomSheet(onDismissRequest = {
            onAddModalBottomSheetDismiss()
        }, sheetState = sheetState) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    stringResource(R.string.financial_record_screen_txt_add_movement),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
                HorizontalDivider(thickness = 0.5.dp)

                OutlinedTextField(
                    edtDate,
                    label = { Text(stringResource(R.string.financial_record_screen_edt_date)) },
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        //For Icons
                        disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),

                    onValueChange = { edtDate = it },
                    enabled = false,
                    maxLines = 1,
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .noRippleClickable {
                            isShowDatePicker = !isShowDatePicker
                        }
                )

                OutlinedTextField(
                    edtAmount,
                    label = { Text(stringResource(R.string.financial_record_screen_edt_amount)) },
                    onValueChange = { edtAmount = it },
                    singleLine = true,
                    prefix = { Text("$") },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                OutlinedTextField(
                    edtDescription,
                    label = { Text(stringResource(R.string.financial_record_screen_edt_description)) },
                    onValueChange = { edtDescription = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                    singleLine = false,
                    maxLines = 2,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                Button(
                    onClick = {
                        onAddModalBottomSheetAdd()
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Agregar")
                }

                Spacer(modifier = Modifier.height(100.dp))


            }

        }

        AppDatePickerDialog(isShowDatePicker,
            onAppDatePickerDismiss = {
                isShowDatePicker = false

            }, onAppDatePickerConfirm = {
                isShowDatePicker = false
                edtDate = it.toFormattedString()
            })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(showDialog: Boolean, onAppDatePickerDismiss: () -> Unit, onAppDatePickerConfirm: (Date) -> Unit) {

    if (showDialog) {

        val dateState = rememberDatePickerState()

        DatePickerDialog(
            onDismissRequest = { onAppDatePickerDismiss() },
            confirmButton = {
                Button(
                    onClick = {

                        dateState.selectedDateMillis?.let { Date(it) }?.let { onAppDatePickerConfirm(it) }
                    }
                ) {
                    Text(text = "OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { onAppDatePickerDismiss() }
                ) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DatePicker(
                state = dateState,
                showModeToggle = true
            )
        }
    }

}
