package com.stacksmashers.greenbook;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 
 * this class calls all account activity within the application . 
 * it make sure all login activity 
 */

public class LoginActivity extends BaseActivity
{
	private EditText login_name;      // edit login name 
	private EditText login_pass;      
	private Button login_button;      // edit login button 
	private Cursor caeser;     // cursor 
	
	
	
	boolean name_bool = false, pass_bool = false;
	
	public LoginActivity()     //loginactivity 
	{
		// TODO Auto-generated constructor stub
	}

	/**called this method to start the activity.  
	 * @param savedInstanceState
	 * @return void 
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);    // create savedinstancestate 
		
		setContentView(R.layout.activity_login);  // setcontentview 
	

		
		login_name = (EditText)findViewById(R.id.login_name);    //login name 
		login_pass = (EditText)findViewById(R.id.login_password);
		
		login_button = (Button)findViewById(R.id.login_button);  // login button 
		login_button.setEnabled(false);
		
		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			login_name.setText(extras.getString("Login Email"));  //login email 
			name_bool = true;
		}
		
		
		login_name.addTextChangedListener(new TextWatcher()    // text change listener 
		
		{
			/**
			 * @param s
			 * @param start  
			 * @param before
			 * @param count 
			 * @return void 
			 * called this method to notify that, within s, 
			 * the count characters beginning at start have just replaced old text that had length before.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// TODO Auto-generated method stub
				
				if(login_name.getEditableText().toString().matches(".+@[a-z]+[.]com"))
					name_bool = true;
				else
					name_bool = false;
					
			}
			/**
			 * @param s
			 * @param start  
			 * @param before
			 * @param count 
			 * @return void 
			 * called this method to notify that, within s, 
			 * the count characters beginning at start start are about to be replaced by new text with length after.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * @param s 
			 * @return void 
			 * This method is called to notify you that, somewhere within s, 
			 * the text has been changed.
			 */
			@Override
			public void afterTextChanged(Editable s)
			{
			
				if(name_bool && pass_bool)          // if name and passwor matches 
					login_button.setEnabled(true);  // than its true 
				else
					login_button.setEnabled(false); // its false 
				
				
				// TODO Auto-generated method stub
				
			}
		});
		
		login_pass.addTextChangedListener(new TextWatcher()
		{
			/**
			 * @param s
			 * @param start  
			 * @param before
			 * @param count 
			 * @return void 
			 * called this method to notify that, within s, 
			 * the count characters beginning at start have just replaced old text that had length before.
			 */
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				// TODO Auto-generated method stub
				
				if(login_pass.getEditableText().toString().matches(".+"))
					pass_bool= true;
				else
					pass_bool = false;
					
			}
			
			/**
			 * @param s
			 * @param start  
			 * @param before
			 * @param count 
			 * @return void 
			 * called this method to notify that, within s, 
			 * the count characters beginning at start start are about to be replaced by new text with length after.
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after)
			{
				// TODO Auto-generated method stub
				
			}
			
			/**
			 * @param s 
			 * @return void 
			 * This method is called to notify you that, somewhere within s, 
			 * the text has been changed.
			 */
			@Override
			public void afterTextChanged(Editable s)
			{
			
				if(name_bool && pass_bool)
					login_button.setEnabled(true);
				else
					login_button.setEnabled(false);
				
				Log.i("tag", " " + name_bool);
				
				
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	
	/**
	 * 
	 * @param views
	 * @return void 
	 * this method to click on login activity and see it 
	 */
	public void onClickLoginActivityButton(View view)
	{
		String name = login_name.getEditableText().toString();
		String pass = login_pass.getEditableText().toString();
		
		caeser = sqldbase.query(DBHelper.USER_TABLE, new String[]{DBHelper.USERS_ID, DBHelper.USER_NAME, DBHelper.USER_PASS}, DBHelper.USER_NAME + " = '" + name + "' AND " + DBHelper.USER_PASS + " = '" + pass + "'", null, null, null, null);
				
				/*
				 * fields = sqldbase.query(DBHelper.COURSE_TABLE, new String[]
					{ DBHelper.COURSE_ID, DBHelper.COURSE_NAME,
							DBHelper.COURSE_PROF, DBHelper.AVERAGE_GRADE,
							DBHelper.IS_WEIGHTED, DBHelper.COURSE_HOURS }, DBHelper.COURSE_ID + " = "
						+ ID, null, null, null, null);

				 */
		
		if(caeser.getCount() != 0)
		{
			caeser.moveToFirst();
			Intent accountsIntent = new Intent(getApplicationContext(), AccountsActivity.class);  // get application cotext from accountsactivity class 
			if(name.equals("sudo@root.com"))    // if account type is specific 
			{
				accountsIntent.putExtra("Account Type", "Sudoer");  // return sudoer
				
		
			}
			else
				accountsIntent.putExtra("Account Type", "Non-Sudoer"); // reutrn nonsuder 
		
			
			accountsIntent.putExtra("Account User", name);
			startActivity(accountsIntent);  // start activity 
		
		}
		else
		{
			Toast.makeText(getApplicationContext(),"User not found", Toast.LENGTH_LONG).show();  // user not found if id and password wrong 
		}
		
	}


}
