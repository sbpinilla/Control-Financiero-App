package com.sergiodev.financeControl.financialRecord.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
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
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergiodev.financeControl.R
import com.sergiodev.financeControl.core.enums.TransactionType
import com.sergiodev.financeControl.core.extension.formatWithThousandsSeparator
import com.sergiodev.financeControl.core.extension.noRippleClickable
import com.sergiodev.financeControl.core.extension.removeThousandsSeparator
import com.sergiodev.financeControl.core.extension.toFormattedString
import com.sergiodev.financeControl.core.extension.toMoneyFormat
import com.sergiodev.financeControl.financialRecord.ui.model.CheckKeysModel
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import com.sergiodev.financeControl.ui.theme.BackApp
import com.sergiodev.financeControl.ui.theme.Green40
import com.sergiodev.financeControl.ui.theme.Grey40
import com.sergiodev.financeControl.ui.theme.WhiteApp
import dev.souravdas.materialsegmentedbutton.SegmentedButton
import dev.souravdas.materialsegmentedbutton.SegmentedButtonItem
import java.util.Date


@Composable
fun FinancialRecordScreen(financialRecordViewModel: FinancialRecordViewModel) {

    var isShowModalAdd by rememberSaveable { mutableStateOf(false) }
    val listFinancialRecords: List<FinancialRecordModel> = financialRecordViewModel.financialRecords
    financialRecordViewModel.getFinancialRecord()
    Scaffold(floatingActionButton = {
        FABFinancialRecordScreen {
            isShowModalAdd = !isShowModalAdd
        }
    }, topBar = { TopBarFinancialRecordScreen() }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (listFinancialRecords.isEmpty())
                FinancialRecordListEmpty()
            else
                FinancialRecordList(listFinancialRecords = listFinancialRecords)

            AddModalBottomSheet(isShow = isShowModalAdd, onAddModalBottomSheetDismiss = {
                isShowModalAdd = !isShowModalAdd
            }, onAddModalBottomSheetAdd = {
                isShowModalAdd = !isShowModalAdd
                financialRecordViewModel.onFinancialRecordAdd(it)
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

    FloatingActionButton(
        onClick = {
            onFABClick()
        }, containerColor = Grey40, contentColor = WhiteApp
    ) {
        Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.financial_record_screen_title))
    }
}

@Composable
fun FinancialRecordList(listFinancialRecords: List<FinancialRecordModel>) {

    LazyColumn {

        itemsIndexed(listFinancialRecords) { index, financialRecord ->

            FinancialRecordItem(item = financialRecord)
            if (index < listFinancialRecords.lastIndex) HorizontalDivider(color = Grey40, thickness = 0.5.dp)
        }
    }
}

@Composable
fun FinancialRecordListEmpty() {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.sincontenido),
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
        )

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
            item.date.toFormattedString(), modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),

            fontWeight = FontWeight.Bold, fontSize = 16.sp, color = BackApp
        )

        Text(
            item.amount.toMoneyFormat(item.nature),
            modifier = Modifier.padding(horizontal = 18.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (item.nature == TransactionType.DEBIT) Green40 else Color.Red

        )
    }
}

@Composable
fun BodyFinancialRecordItem(item: FinancialRecordModel) {

    Text(
        item.description, modifier = Modifier.padding(horizontal = 16.dp), fontWeight = FontWeight.Normal, fontSize = 16.sp, color = BackApp

    )

}

@Composable
fun FooterFinancialRecordItem(item: FinancialRecordModel) {

    LazyRow {
        item {
            Spacer(modifier = Modifier.size(16.dp))
        }
        items(item.keys) { key ->
            AssistChip(label = { Text(key) }, colors = AssistChipDefaults.assistChipColors(containerColor = Grey40, labelColor = WhiteApp), onClick = {}, modifier = Modifier.padding(end = 8.dp)
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddModalBottomSheet(isShow: Boolean, onAddModalBottomSheetDismiss: () -> Unit, onAddModalBottomSheetAdd: (FinancialRecordModel) -> Unit) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    var transactionType: TransactionType by rememberSaveable { mutableStateOf(TransactionType.CREDIT) }
    var isShowDatePicker by rememberSaveable { mutableStateOf(false) }
    var edtDate: String by rememberSaveable { mutableStateOf("") }
    var dateFinancialRecord: Date by rememberSaveable { mutableStateOf(Date()) }
    var edtAmount: String by rememberSaveable { mutableStateOf("") }


    var edtDescription: String by rememberSaveable { mutableStateOf("") }

    val segmentedItems = listOf(
        SegmentedButtonItem(leadingIcon = {}, title = { Text(stringResource(R.string.financial_record_screen_txt_credit)) }, onClick = { transactionType = TransactionType.CREDIT }),
        SegmentedButtonItem(leadingIcon = {}, title = { Text(stringResource(R.string.financial_record_screen_txt_debit)) }, onClick = { transactionType = TransactionType.DEBIT })
    )

    var checkItems: List<CheckKeysModel> by rememberSaveable { mutableStateOf(CheckKeysModel.generateCheckKeysList()) }

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
                    stringResource(R.string.financial_record_screen_txt_add_movement), fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )

                HorizontalDivider(thickness = 0.5.dp)

                SegmentedButton(

                    items = segmentedItems, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                )

                OutlinedTextField(edtDate, label = { Text(stringResource(R.string.financial_record_screen_edt_date)) }, colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    //For Icons
                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                ),

                    onValueChange = { edtDate = it }, enabled = false, maxLines = 1, readOnly = true, modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
                        .noRippleClickable {
                            isShowDatePicker = !isShowDatePicker
                        })

                OutlinedTextField(
                    edtAmount.formatWithThousandsSeparator(),
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

                CheckKeysAddModalBottomSheet(items = checkItems, onCheckedItem = { checkItem ->

                    checkItems = checkItems.map { item ->

                        if (item.name == checkItem.name) item.copy(isCheck = !checkItem.isCheck)
                        else item

                    }

                })

                Button(onClick = {

                    onAddModalBottomSheetAdd(FinancialRecordModel(description = edtDescription,
                        nature = transactionType,
                        amount = edtAmount.removeThousandsSeparator().toDouble(),
                        keys = checkItems.filter { x -> x.isCheck }.map { x -> x.name },
                        date = dateFinancialRecord
                    )
                    )

                    transactionType = TransactionType.DEBIT
                    edtDate = ""
                    edtAmount = ""
                    edtDescription = ""
                    checkItems = CheckKeysModel.generateCheckKeysList()

                },
                    enabled = edtDate.isNotEmpty() && edtAmount.isNotEmpty() && edtDescription.isNotEmpty() && checkItems.count { x -> x.isCheck } >= 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)) {
                    Text(stringResource(R.string.financial_record_screen_btn_add))
                }

                Spacer(modifier = Modifier.height(100.dp))


            }

        }

        AppDatePickerDialog(isShowDatePicker, onAppDatePickerDismiss = {
            isShowDatePicker = false

        }, onAppDatePickerConfirm = {
            isShowDatePicker = false
            dateFinancialRecord = it
            edtDate = it.toFormattedString()
        })
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(showDialog: Boolean, onAppDatePickerDismiss: () -> Unit, onAppDatePickerConfirm: (Date) -> Unit) {

    if (showDialog) {

        val dateState = rememberDatePickerState()

        DatePickerDialog(onDismissRequest = { onAppDatePickerDismiss() }, confirmButton = {
            Button(onClick = {

                dateState.selectedDateMillis?.let { Date(it) }?.let { onAppDatePickerConfirm(it) }
            }) {
                Text(text = "OK")
            }
        }, dismissButton = {
            Button(onClick = { onAppDatePickerDismiss() }) {
                Text(text = "Cancel")
            }
        }) {
            DatePicker(
                state = dateState, showModeToggle = true
            )
        }
    }

}

@Composable
fun CheckKeysAddModalBottomSheet(items: List<CheckKeysModel>, onCheckedItem: (CheckKeysModel) -> Unit) {

    Column(modifier = Modifier.fillMaxWidth()) {

        for (i in items.indices step 3) {

            val firstElement = items[i]
            val secondElement = if (i + 1 < items.size) items[i + 1] else null
            val thirdElement = if (i + 1 < items.size) items[i + 2] else null

            AppCheckBoxTwoItem(item = firstElement, nexItem = secondElement, nexItem2 = thirdElement, onCheckedKey = {
                onCheckedItem(it)
            })

        }
    }


}

@Composable
fun AppCheckBoxTwoItem(item: CheckKeysModel, nexItem: CheckKeysModel?, nexItem2: CheckKeysModel?, onCheckedKey: (CheckKeysModel) -> Unit) {

    Row {

        Box(modifier = Modifier.weight(1f)) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Checkbox(
                    checked = item.isCheck,
                    onCheckedChange = {
                        onCheckedKey(item)
                    },
                )
                Text(item.name, fontSize = 14.sp, modifier = Modifier
                    .weight(1f)
                    .padding(top = 12.dp)
                    .clickable {
                        onCheckedKey(item)
                    })

            }
        }
        Box(modifier = Modifier.weight(1f)) {

            if (nexItem != null) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    Checkbox(checked = nexItem.isCheck, onCheckedChange = {
                        onCheckedKey(nexItem)
                    })
                    Text(nexItem.name, fontSize = 14.sp, modifier = Modifier
                        .weight(1f)
                        .padding(top = 12.dp)
                        .clickable {
                            onCheckedKey(nexItem)
                        })
                }
            }

        }

        Box(modifier = Modifier.weight(1f)) {

            if (nexItem2 != null) {

                Row(modifier = Modifier.fillMaxWidth()) {

                    Checkbox(checked = nexItem2.isCheck, onCheckedChange = {
                        onCheckedKey(nexItem2)
                    })
                    Text(nexItem2.name, fontSize = 14.sp, modifier = Modifier
                        .weight(1f)
                        .padding(top = 12.dp)
                        .clickable {
                            onCheckedKey(nexItem2)
                        })
                }
            }

        }


    }


}
