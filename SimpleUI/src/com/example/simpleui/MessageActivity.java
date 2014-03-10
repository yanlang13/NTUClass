package com.example.simpleui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);

		// getIntent�N�O����MainActivity���A�ҥH�����Okey(��̳��OString name)
		String text = getIntent().getStringExtra("text");
		writeFile(text);

		textView = (TextView) findViewById(R.id.message);
		textView.setText(readFile());

	}

	// �g�@�Ӥ�r�ɡA�AŪ�X��(��y-���޻�)
	private void writeFile(String text) {
		// �j�}��ơA��K�T�{�C
		text += "\n";
		
		try {
			// mode�O���g�ɤ覡�C�C�C�s�ɦ�m ����Sroot�N�ݤ���C package-data-data-files
			FileOutputStream fos = openFileOutput("history.txt",
					Context.MODE_APPEND);
			// write���T�ءA�o��ϥΤ@��byte array (string to byte array)
			fos.write(text.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readFile() {
		// ����buffer�A�n�T�w�q�p��o�ӡC�@���while����S���Ȭ���C
		try {
			FileInputStream fis = openFileInput("history.txt");
			byte[] buffer = new byte[1024];
			fis.read(buffer);
			return new String(buffer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
