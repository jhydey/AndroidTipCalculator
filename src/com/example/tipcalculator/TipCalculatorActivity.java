package com.example.tipcalculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculatorActivity extends Activity
{
	EditText amountText = null;
	TextView tipText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		
		this.amountText = (EditText)findViewById(R.id.editTextAmount);
		this.tipText = (TextView)findViewById(R.id.textViewTip);
		this.tipText.setText("");
		
		Button tenButton = (Button)findViewById(R.id.buttonTenPercent);
		tenButton.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v)
					{
						getAndSetTipAmount(.1f);
					}
				}
			);
		
		Button fifteenButton = (Button)findViewById(R.id.buttonFifteenPercent);
		fifteenButton.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v)
					{
						getAndSetTipAmount(.15f);
					}
				}
			);
		
		Button twentyButton = (Button)findViewById(R.id.buttonTwentyPercent);
		twentyButton.setOnClickListener(
				new OnClickListener() {
					
					@Override
					public void onClick(View v)
					{
						getAndSetTipAmount(.2f);
					}
				}
			);
	}

	protected void getAndSetTipAmount(float tipPercent)
	{
		BigDecimal cost = getMoneyAmount();

		if (cost == null)
		{
			return;
		}
		
		BigDecimal tipAmount = getTipAmount(cost, new BigDecimal(tipPercent));
		DecimalFormat formatter = new DecimalFormat("##.00"); 
		StringBuilder sb = new StringBuilder("Tip is $").append(formatter.format(tipAmount));
		this.tipText.setText(sb.toString());
	}

	protected BigDecimal getTipAmount(BigDecimal cost, BigDecimal tipPercent)
	{
		BigDecimal tipAmount = cost.multiply(tipPercent).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		return tipAmount;
	}

	protected BigDecimal getMoneyAmount()
	{
		BigDecimal cost = null;
		
		try
		{
			cost = new BigDecimal(this.amountText.getText().toString());
		}
		catch (NumberFormatException e)
		{
			Toast.makeText(getBaseContext(), "Please enter a number amount.", Toast.LENGTH_LONG).show();
			this.amountText.setText("");
		}
		
		return cost;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}
}
