package com.sergiodev.financeControl.financialRecordConsolidated.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sergiodev.financeControl.R
import com.sergiodev.financeControl.core.enums.TransactionType
import com.sergiodev.financeControl.core.extension.toMoneyFormat
import com.sergiodev.financeControl.financialRecord.ui.model.FinancialRecordModel
import com.sergiodev.financeControl.ui.theme.BackApp
import com.sergiodev.financeControl.ui.theme.Green40
import com.sergiodev.financeControl.ui.theme.Grey40


@Composable
fun FinancialRecordConsolidatedScreen(listFinancialRecords: List<FinancialRecordModel>) {

    val debitValue = listFinancialRecords.filter { x -> x.nature == TransactionType.DEBIT }.sumOf { x -> x.amount }
    val creditValue = listFinancialRecords.filter { x -> x.nature == TransactionType.CREDIT }.sumOf { x -> x.amount }

    val total = debitValue - creditValue

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        ConsolidatedItem(stringResource(R.string.financial_record_consolidated_screen_txt_debit), debitValue, TransactionType.DEBIT)
        ConsolidatedItem(stringResource(R.string.financial_record_consolidated_screen_txt_credit), creditValue, TransactionType.CREDIT)
        HorizontalDivider(color = Grey40, thickness = 0.5.dp, modifier = Modifier.padding(top = 16.dp))
        ConsolidatedItem(stringResource(R.string.financial_record_consolidated_screen_txt_total), total, TransactionType.DEBIT)

    }
}


@Composable
fun ConsolidatedItem(name: String, value: Double, type: TransactionType) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {

        Text(name, fontWeight = FontWeight.Bold, fontSize = 16.sp, color = BackApp)
        Spacer(modifier = Modifier.weight(1f))

        Text(
            value.toMoneyFormat(type), fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = if (type == TransactionType.DEBIT) Green40 else Color.Red
        )

    }

}